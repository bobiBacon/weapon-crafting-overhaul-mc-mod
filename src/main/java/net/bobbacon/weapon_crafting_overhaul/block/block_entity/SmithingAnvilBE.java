package net.bobbacon.weapon_crafting_overhaul.block.block_entity;

import net.bobbacon.weapon_crafting_overhaul.screen.SmithingScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SmithingAnvilBE extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> invetory= DefaultedList.ofSize(3,ItemStack.EMPTY);
    private static final int HAMMER_SLOT = 0;
    private static final int TOOL_SLOT = 1;
    private static final int OUTPUT_SLOT = 2;
    protected final PropertyDelegate propertyDelegate;
    private boolean isCrafting = false;
    private int progress= 0;
    private static final int MAX_PROGRESS= 40;
    public SmithingAnvilBE(BlockPos pos, BlockState state) {
        super(ModBEs.SMITHING_ANVIL_BLOCK_ENTITY_TYPE, pos, state);

        propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> SmithingAnvilBE.this.progress;
                    case 1 -> SmithingAnvilBE.MAX_PROGRESS;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                if (index == 0) {
                    SmithingAnvilBE.this.progress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }
    

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, invetory);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt,invetory);
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        packetByteBuf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable("container.forge");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new SmithingScreenHandler(syncId,playerInventory,this,this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return invetory;
    }

    public static void tick(World world, BlockPos pos, BlockState state, SmithingAnvilBE blockEntity) {
        if (world.isClient){
            return;
        }
        if (blockEntity.isCurrentlyCrafting()){
            blockEntity.increaseProgress();
            if (blockEntity.hasCraftingFinished()){
                blockEntity.craft();
                blockEntity.resetProgress();
            }
        }
        blockEntity.markDirty();

    }

    private void resetProgress() {
        progress=0;
    }

    private void craft() {
    }

    private boolean hasCraftingFinished() {
        return progress>=MAX_PROGRESS;
    }

    private void increaseProgress() {
        progress++;
    }

    private boolean isCurrentlyCrafting() {
        return isCrafting;
    }
}
