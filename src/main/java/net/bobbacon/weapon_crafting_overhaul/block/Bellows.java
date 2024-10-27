package net.bobbacon.weapon_crafting_overhaul.block;

import net.bobbacon.weapon_crafting_overhaul.block.block_entity.BellowsBE;
import net.bobbacon.weapon_crafting_overhaul.block.block_entity.BrickFurnaceBE;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Bellows extends BlockWithEntity {
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
        return ActionResult.PASS;
    }
}
