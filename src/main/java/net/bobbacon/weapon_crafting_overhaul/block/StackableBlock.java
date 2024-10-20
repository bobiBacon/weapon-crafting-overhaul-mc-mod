package net.bobbacon.weapon_crafting_overhaul.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class StackableBlock extends Block {
    public final int maxStack;
    public static final IntProperty STACK = IntProperty.of("stack",1, 4);;

    private final VoxelShape shape;

    public StackableBlock(Settings settings, int maxStack, VoxelShape shape) {
        super(settings);
        this.maxStack = maxStack;
        this.shape = shape;
        setDefaultState(getDefaultState().with(STACK, 1));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STACK);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        if (blockState.isOf(this)) {
            return blockState.with(STACK, Math.min(4, blockState.get(STACK) + 1));
        }
        return super.getPlacementState(ctx);
    }
    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        if (!context.shouldCancelInteraction() && context.getStack().isOf(this.asItem()) && state.get(STACK) < 4) {
            return true;
        }

        return super.canReplace(state, context);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState= world.getBlockState(new BlockPos(pos.getX(),pos.getY()-1,pos.getZ()));

        return blockState.isSolidBlock(world,pos);
    }
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

}
