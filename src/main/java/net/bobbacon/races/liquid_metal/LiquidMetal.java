package net.bobbacon.races.liquid_metal;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.item.ModItems;
import net.bobbacon.races.item.StoneBowl;
import net.bobbacon.races.registry.ModRegistries;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        RacesModForMyServer.LOGGER.info("getBowl");
        RacesModForMyServer.LOGGER.info(String.valueOf(metalIngot));
        return ModItems.STONE_BOWL;
    }

    public static LiquidMetal getByIngot(Item ingot){
        return ingotLiquidMetalMap.getOrDefault(ingot,LiquidMetals.EMPTY);
    }
}
