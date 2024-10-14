package net.bobbacon.races.block;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.block.block_entity.BrickFurnaceBE;
import net.bobbacon.races.block.block_entity.ModBE;
import net.bobbacon.races.item.ModItems;
import net.bobbacon.races.liquid_metal.LiquidMetal;
import net.bobbacon.races.liquid_metal.LiquidMetalable;
import net.bobbacon.races.liquid_metal.LiquidMetals;
import net.bobbacon.races.recipe.BrickFurnaceCookingRecipe;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.CampfireBlockEntity;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.CampfireCookingRecipe;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class BrickFurnace extends BlockWithEntity implements LiquidMetalable {
    public static final BooleanProperty LIT = Properties.LIT;
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    protected static final VoxelShape SHAPE1 = Block.createCuboidShape(0.0, 0.0, 2.0, 16.0, 9.0, 16.0);
    protected static final VoxelShape SHAPE2 = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 14.0);
    protected static final VoxelShape SHAPE3 = Block.createCuboidShape(2.0, 0.0, 0.0, 16.0, 9.0, 16.0);
    protected static final VoxelShape SHAPE4 = Block.createCuboidShape(0.0, 0.0, 2.0, 14.0, 9.0, 16.0);


    public BrickFurnace(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BrickFurnaceBE(pos,state);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return BrickFurnace.checkType(type, ModBE.BRICK_FURNACE_BLOCK_ENTITY, BrickFurnaceBE::tick);
    }
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        RacesModForMyServer.LOGGER.info("onUse");
        ItemStack itemStack = player.getStackInHand(hand);
        BrickFurnaceBE brickFurnaceBlockEntity = (BrickFurnaceBE) Objects.requireNonNull(world.getBlockEntity(pos));
        Optional<BrickFurnaceCookingRecipe> optional=brickFurnaceBlockEntity.getRecipeFor(itemStack);
//        fuel
        if (brickFurnaceBlockEntity.addFuel(itemStack,world,pos,state)){
            return ActionResult.SUCCESS;
        }
//        recipe
        if (optional.isPresent()) {
            if (brickFurnaceBlockEntity.addItem(player.getAbilities().creativeMode ? itemStack.copy() : itemStack, optional.get().getCookTime(),world,pos,state)) {
                RacesModForMyServer.LOGGER.info("success");
                return ActionResult.SUCCESS;
            }
            RacesModForMyServer.LOGGER.info("consume");
            if (!state.get(LIT)){
                return failAction(world, pos, state);
            }
            else return ActionResult.CONSUME;
        }
//        drop
        if (!brickFurnaceBlockEntity.getItemBeingCooked().isEmpty()){
            double d = (double)pos.getX() + 0.5;
            double e = pos.getY();
            double f = (double)pos.getZ() + 0.5;
            world.playSound(d, e, f, SoundEvents.ENTITY_GLOW_ITEM_FRAME_REMOVE_ITEM, SoundCategory.BLOCKS, 1.0f, 1.0f, true);
            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            for (int l = 0; l < 6; l++) {
                double h = world.random.nextDouble() * 0.6 - 0.3;
                double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.40 : h;
                double j = world.random.nextDouble() * 6.0 / 16.0;
                double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.40 : h;
                world.addParticle(ParticleTypes.LAVA, d + i, e + j, f + k, 0.0, 0.0, 0.0);
            }
            if (itemStack.isOf(ModItems.STONE_BOWL)&&!state.get(LIT)&&!world.isClient()){
                RacesModForMyServer.LOGGER.info("l'item de la main est un bol en pierre");
                ItemStack bowl= tryDrainLiquidMetal(world,pos,player,state);
                if (!bowl.isEmpty()){
                    removeMetal(world, pos, player, state);
                    RacesModForMyServer.LOGGER.info("bowl est pas vide");
                    ItemStack itemStack2=ItemUsage.exchangeStack(itemStack, player, bowl);;
                    player.setStackInHand(hand, itemStack2);
                    return ActionResult.success(world.isClient());
                }
            }
            brickFurnaceBlockEntity.dropItem(player, world,pos, state);
            RacesModForMyServer.LOGGER.info("success 2");
            return ActionResult.success(world.isClient);
        }
        RacesModForMyServer.LOGGER.info("pass");
        return failAction(world, pos, state);
    }
    protected ActionResult failAction(World world, BlockPos pos, BlockState state){


            Random random= new Random();
            world.playSound(pos.getX()+0.5,pos.getY()+0.5,pos.getZ()+0.5, SoundEvents.BLOCK_CANDLE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f, true);

            for (int i = 0; i < 7; i++) {
                world.addParticle(ParticleTypes.SMOKE,pos.getX()+ random.nextFloat(),(double) pos.getY()+0.75,pos.getZ()+ random.nextFloat(),0,random.nextFloat()/10,0);

            }

        return ActionResult.CONSUME;
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite()).with(LIT,false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT,FACING);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (state.get(FACING)){
            case EAST -> {
                return SHAPE4;
            }
            case WEST -> {
                return SHAPE3;
            }
            case NORTH -> {
                return SHAPE1;
            }
            case SOUTH -> {
                return SHAPE2;
            }
            default -> {
                return SHAPE1;
            }
        }

    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, net.minecraft.util.math.random.Random random) {
        if (!state.get(LIT)) {
            return;
        }
        double d = (double)pos.getX() + 0.5;
        double e = pos.getY();
        double f = (double)pos.getZ() + 0.5;
        if (random.nextDouble() < 0.1) {
            world.playSound(d, e, f, SoundEvents.BLOCK_CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
        }
        Direction direction = state.get(FACING);
        Direction.Axis axis = direction.getAxis();
        double g = 0.52;
        double h = random.nextDouble() * 0.6 - 0.3;
        double i = axis == Direction.Axis.X ? (double)direction.getOffsetX() * 0.40 : h;
        double j = random.nextDouble() * 6.0 / 16.0;
        double k = axis == Direction.Axis.Z ? (double)direction.getOffsetZ() * 0.40 : h;
        world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.FLAME, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE,d,e,f,(random.nextFloat()-0.5)/30-0.02,0.05+(random.nextFloat()/100),(random.nextFloat()-0.5)/30);
    }

    @Override
    public float getAmbientOcclusionLightLevel(BlockState state, BlockView world, BlockPos pos) {
        return super.getAmbientOcclusionLightLevel(state, world, pos);
    }
    @Override
    public ItemStack tryDrainLiquidMetal(World world,BlockPos pos,PlayerEntity player,BlockState state) {

        BlockEntity blockEntity=world.getBlockEntity(pos);
        assert blockEntity != null;
        ItemStack ingot = ((BrickFurnaceBE)blockEntity).getItemBeingCooked();
        LiquidMetal metal=LiquidMetal.getByIngot(ingot.getItem());
        RacesModForMyServer.LOGGER.info(metal.toString());
        if (metal.equals(LiquidMetals.EMPTY)){
            RacesModForMyServer.LOGGER.info("metal est vide");
            return ItemStack.EMPTY;
        }
        removeMetal(world, pos,player,state);
        return metal.getBowl().getDefaultStack();
    }

    @Override
    public void removeMetal(World world,BlockPos pos,PlayerEntity player, BlockState state) {
        BrickFurnaceBE blockEntity= (BrickFurnaceBE) world.getBlockEntity(pos);
        assert blockEntity != null;
        blockEntity.removeItem(world, pos, state);
        if (!world.isClient){
            blockEntity.dropXp((ServerWorld) world, player.getPos(), 1, blockEntity.getXpFromItemBeingCooked());
        }

        blockEntity.removeItem(world,pos,state);
    }

}
