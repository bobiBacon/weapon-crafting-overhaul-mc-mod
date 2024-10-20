package net.bobbacon.weapon_crafting_overhaul.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DarkMithrilIngot extends Item {
    public DarkMithrilIngot(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }


}