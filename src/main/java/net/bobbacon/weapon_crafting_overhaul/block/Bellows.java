package net.bobbacon.weapon_crafting_overhaul.block;

import com.eliotlash.mclib.math.functions.classic.Mod;
import net.bobbacon.weapon_crafting_overhaul.block.block_entity.BellowsBE;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Bellows extends BlockWithEntity {
    public static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    protected static final VoxelShape SHAPE = Block.createCuboidShape(2,0,2,14,7,14);

    protected Bellows(Settings settings) {
        super(settings);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new BellowsBE(pos, state);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        BellowsBE bellowsBlockEntity = (BellowsBE)  Objects.requireNonNull(world.getBlockEntity(pos));
        if (bellowsBlockEntity.onUse()) {
            return ActionResult.SUCCESS;
        }
        return ActionResult.CONSUME;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos= ctx.getBlockPos();
        World world= ctx.getWorld();
        HashMap<Direction,BlockState> states= new HashMap<>();
        states.put(Direction.EAST,world.getBlockState(new BlockPos(pos.getX()+1,pos.getY(),pos.getZ())));
        states.put(Direction.WEST,world.getBlockState(new BlockPos(pos.getX()-1,pos.getY(),pos.getZ())));
        states.put(Direction.SOUTH,world.getBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ()+1)));
        states.put(Direction.NORTH,world.getBlockState(new BlockPos(pos.getX(),pos.getY(),pos.getZ()-1)));
        for (Map.Entry<Direction,BlockState> stateEntry:states.entrySet()){
            if (stateEntry.getValue().getBlock() != ModBlocks.MUD_OVEN){
                continue;
            }
//            if (stateEntry.getValue().get(BrickFurnace.FACING).getOpposite()==stateEntry.getKey()){
//                continue;
//            }
            
            return this.getDefaultState().with(FACING,stateEntry.getKey());
        }

        return this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing().getOpposite());
    }

}
