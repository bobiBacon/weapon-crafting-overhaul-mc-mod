package net.bobbacon.weapon_crafting_overhaul.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;

public class RockItem extends BlockItem {
    public RockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return state.isOf(Blocks.COPPER_ORE);
    }
}
