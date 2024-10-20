package net.bobbacon.weapon_crafting_overhaul.liquid_metal;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.item.ModItems;
import net.minecraft.item.Item;

import java.util.HashMap;

public class LiquidMetal {
    private final Item metalIngot;
    private static final HashMap<Item,LiquidMetal> ingotLiquidMetalMap=new HashMap<Item, LiquidMetal>();
    public LiquidMetal(Item metalIngot){
        this.metalIngot=metalIngot;
        ingotLiquidMetalMap.put(metalIngot,this);
    }
//    public static class Settings {
//
//    }
    public Item getIngot(){
        return this.metalIngot;
    }

    public Item getBowl() {
        WeaponCraftingOverhaul.LOGGER.info("getBowl");
        WeaponCraftingOverhaul.LOGGER.info(String.valueOf(metalIngot));
        return ModItems.STONE_BOWL;
    }

    public static LiquidMetal getByIngot(Item ingot){
        return ingotLiquidMetalMap.getOrDefault(ingot,LiquidMetals.EMPTY);
    }
}
