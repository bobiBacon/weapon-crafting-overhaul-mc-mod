package net.bobbacon.weapon_crafting_overhaul.block.block_entity;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.block.BrickFurnace;
import net.bobbacon.weapon_crafting_overhaul.forging.Fuel;
import net.bobbacon.weapon_crafting_overhaul.recipe.BrickFurnaceCookingRecipe;
import net.bobbacon.weapon_crafting_overhaul.recipe.types.ModRecipeTypes;
import net.bobbacon.weapon_crafting_overhaul.sounds.ModSounds;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.Random;

public class BrickFurnaceBE extends BlockEntity {
    private DefaultedList<ItemStack> itemsBeingCooked= DefaultedList.ofSize(1,ItemStack.EMPTY);
    private float remainingCookingTime = 0;
    private boolean cooked= false;
    private boolean lit= false;
    private int fuelCookingTicks =0;
    protected int overChargeTicks = 0;
    private final int MAX_FUEL= 15;
    private final String FUEL_KEY= "fuel";
    private final String COOKED_KEY = "cooked";
    private final String COOKING_TIME_KEY = "remaining_time_of_cooking";
    private final String LIT_KEY= "lit";
    private final RecipeManager.MatchGetter<Inventory, BrickFurnaceCookingRecipe> matchGetter = RecipeManager.createCachedMatchGetter(ModRecipeTypes.BRICK_FURNACE_COOKING);
    public BrickFurnaceBE( BlockPos pos, BlockState state) {
        super(ModBEs.MUD_OVEN_BLOCK_ENTITY_TYPE, pos, state);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        Inventories.writeNbt(nbt, this.itemsBeingCooked, true);
        nbt.putInt(FUEL_KEY, fuelCookingTicks);
        nbt.putFloat(COOKING_TIME_KEY, remainingCookingTime);
        nbt.putBoolean(COOKED_KEY, cooked );
        nbt.putBoolean(LIT_KEY,lit);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        this.itemsBeingCooked.clear();
        Inventories.readNbt(nbt, this.itemsBeingCooked);
        cooked=nbt.getBoolean(COOKED_KEY);
        fuelCookingTicks = nbt.getInt(FUEL_KEY);
        remainingCookingTime = nbt.getInt(COOKING_TIME_KEY);
        lit= nbt.getBoolean(LIT_KEY);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
    public static void tick(World world, BlockPos pos, BlockState state, BrickFurnaceBE be) {
        if (!be.isLit()){
            return;
        }
        WeaponCraftingOverhaul.LOGGER.info("cooking... " + be.remainingCookingTime);
        WeaponCraftingOverhaul.LOGGER.info(String.valueOf(be.cooked));
        if (be.isOverCharged()){
            be.overChargeTicks--;
            if (be.itemNeedsBellows()){
                be.remainingCookingTime--;
            }else {
                be.remainingCookingTime-= 1.75F;
            }
            be.overChargeTicks--;
        }
        else {
            if (!be.itemNeedsBellows()) {
                be.remainingCookingTime--;
            } else {
                Random random=new Random();
                if (random.nextFloat()<0.5){
                    world.addParticle(ParticleTypes.SMOKE,pos.getX()+ random.nextFloat(),(double) pos.getY()+0.75,pos.getZ()+ random.nextFloat(),0,random.nextFloat()/10,0);
                }
            }

        }
        if (be.remainingCookingTime <= 0)
        {
            be.finishedCooking(state, world, pos);
        }
        be.markDirty();
    }
    public ItemStack getItemBeingCooked(){
        return itemsBeingCooked.get(0);
    }
    public void setItemBeingCooked(ItemStack stack){
        itemsBeingCooked.set(0,stack);
    }
    private void finishedCooking(BlockState state,World world, BlockPos pos){
        setItemBeingCooked(getRecipeFor(getItemBeingCooked()).get().getOutput(world.getRegistryManager()));
        fuelCookingTicks -= 5;
        cooked= true;
        tryLight(state,world,pos,false);
        WeaponCraftingOverhaul.LOGGER.info("cooked!");
    }
    public boolean addItem(ItemStack itemStack, int cookTime, World world, BlockPos pos,BlockState state) {
        if (getItemBeingCooked().isOf(ItemStack.EMPTY.getItem())){

            this.remainingCookingTime = cookTime;
            cooked=false;
            setItemBeingCooked(itemStack.split(1));
            tryLight(state,world,pos,true);

            updateListeners();
            return true;

        }
        return false;
    }
    public boolean addFuel(ItemStack fuelItem,World world, BlockPos pos,BlockState state,PlayerEntity player){
        Fuel fuel= Fuel.getFuelFromItem(fuelItem.getItem());
        if (fuel==Fuel.Empty){
            return false;
        }
        int power= fuel.getPower();
        WeaponCraftingOverhaul.LOGGER.info(String.valueOf(this.fuelCookingTicks));
        if (this.fuelCookingTicks + power>=20){
            WeaponCraftingOverhaul.LOGGER.info("over 20");
            return false;
        }
        this.fuelCookingTicks += power;
        if (!player.isCreative()) fuelItem.decrement(1);
        tryLight(state,world,pos,true);
        return true;
    }
    public boolean isOverCharged(){
        WeaponCraftingOverhaul.LOGGER.info(String.valueOf(overChargeTicks));
        return overChargeTicks>0;
    }
    public void overCharge(){
        assert world != null;
        world.playSoundAtBlockCenter(
                pos,
                ModSounds.BELLOWS,
                SoundCategory.BLOCKS,
                0.5F,
                1.5F,
                false
        );
        overChargeTicks= 100;
    }
    public void tryLight(BlockState state, World world, BlockPos pos, boolean shouldLight){
//        Optional<BrickFurnaceCookingRecipe> optional=matchGetter.getFirstMatch(new SimpleInventory(getItemBeingCooked()),this.world);
//        if (optional.isPresent()){
//            BrickFurnaceCookingRecipe recipe= optional.get();
//            boolean b= recipe.NeedsBellows()&&!isOverCharged();
//            shouldLight = shouldLight && fuelCookingTicks>=5 && ! getItemBeingCooked().isEmpty() && !b;
//        }
//        else {
//            shouldLight = shouldLight && fuelCookingTicks>=5 && ! getItemBeingCooked().isEmpty();
//        }
        shouldLight = shouldLight && fuelCookingTicks>=5 && ! getItemBeingCooked().isEmpty()&& !cooked /*&& !(itemNeedsBellows()&&!isOverCharged())*/;
        WeaponCraftingOverhaul.LOGGER.info("should light"+ shouldLight);
        if (!shouldLight){
            overChargeTicks=0;
        }
        world.setBlockState(pos, state.with(BrickFurnace.LIT, shouldLight), Block.NOTIFY_ALL);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(state));
        lit= shouldLight;
        if (shouldLight){
            cooked= false;
        }
        markDirty();
    }
    public boolean itemNeedsBellows(){
        Optional<BrickFurnaceCookingRecipe> optional=matchGetter.getFirstMatch(new SimpleInventory(getItemBeingCooked()),this.world);
        return optional.map(BrickFurnaceCookingRecipe::NeedsBellows).orElse(false);
    }
    public boolean isLit(){
        return lit && fuelCookingTicks >=5;
    }
    public Optional<BrickFurnaceCookingRecipe> getRecipeFor(ItemStack stack) {
        return this.matchGetter.getFirstMatch(new SimpleInventory(stack), this.world);
    }
    private void updateListeners() {
        this.markDirty();
        this.getWorld().updateListeners(this.getPos(), this.getCachedState(), this.getCachedState(), Block.NOTIFY_ALL);
    }

    public void dropItem(PlayerEntity player,World world,BlockPos pos, BlockState state){
        Direction direction = state.get(HorizontalFacingBlock.FACING);
        Direction direction2 = direction.getAxis() == Direction.Axis.Y ? player.getHorizontalFacing().getOpposite() : direction;
        ItemEntity itemEntity = new ItemEntity(world, (double)pos.getX() + 0.5 + (double)direction2.getOffsetX() * 0.65, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5 + (double)direction2.getOffsetZ() * 0.65, getItemBeingCooked());
        itemEntity.setVelocity(0.05 * (double)direction2.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * (double)direction2.getOffsetZ() + world.random.nextDouble() * 0.02);
        world.spawnEntity(itemEntity);
        removeItem(world, pos, state);
        if (!world.isClient){
            dropXp((ServerWorld) world,player.getPos(),1,getXpFromItemBeingCooked());

        }

        updateListeners();
    }
    public void removeItem(World world,BlockPos pos,BlockState state){
        setItemBeingCooked(ItemStack.EMPTY);
        tryLight(state,world,pos,false);
        updateListeners();
    }
    public float getXpFromItemBeingCooked(){
        Optional<BrickFurnaceCookingRecipe> optionalRecipe =getRecipeFor(getItemBeingCooked());
        return optionalRecipe.map(AbstractCookingRecipe::getExperience).orElse(0F);
    }

    public void dropXp(ServerWorld world, Vec3d pos, int multiplier, float experience) {
        int i = MathHelper.floor((float)multiplier * experience);
        float f = MathHelper.fractionalPart((float)multiplier * experience);
        if (f != 0.0f && Math.random() < (double)f) {
            ++i;
        }
        ExperienceOrbEntity.spawn(world, pos, i);
    }
}
