package net.bobbacon.races.liquid_metal;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface LiquidMetalable {
    public ItemStack tryDrainLiquidMetal(World world, BlockPos pos, PlayerEntity player, BlockState state);
    public void removeMetal(World world, BlockPos pos,PlayerEntity player, BlockState state);
}
