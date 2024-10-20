package net.bobbacon.weapon_crafting_overhaul.item;

import net.bobbacon.weapon_crafting_overhaul.liquid_metal.LiquidMetal;
import net.minecraft.item.Item;

public class StoneBowl extends Item {
    public final LiquidMetal metal;
    public StoneBowl(LiquidMetal metal, Settings settings) {
        super(settings);
        this.metal=metal;

    }
}
