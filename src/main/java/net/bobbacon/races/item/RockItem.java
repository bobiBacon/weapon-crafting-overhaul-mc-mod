package net.bobbacon.races.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RockItem extends BlockItem {
    public RockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return state.isOf(Blocks.COPPER_ORE);
    }
}
