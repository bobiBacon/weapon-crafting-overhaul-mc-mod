package net.bobbacon.weapon_crafting_overhaul.liquid_metal;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface LiquidMetalable {
    public ItemStack tryDrainLiquidMetal(World world, BlockPos pos, PlayerEntity player, BlockState state);
    public void removeMetal(World world, BlockPos pos,PlayerEntity player, BlockState state);
}
