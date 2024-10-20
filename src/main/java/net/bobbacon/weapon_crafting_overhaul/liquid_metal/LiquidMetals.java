package net.bobbacon.weapon_crafting_overhaul.liquid_metal;

import net.bobbacon.weapon_crafting_overhaul.item.ModItems;
import net.bobbacon.weapon_crafting_overhaul.registry.ModRegistries;
import net.bobbacon.weapon_crafting_overhaul.registry.RegistryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public abstract class LiquidMetals {

    private static final RegistryHelper<LiquidMetal> registryHelper= new RegistryHelper<>(ModRegistries.LIQUID_METALS);


    public static final LiquidMetal COPPER = registryHelper.register("copper",new LiquidMetal(Items.COPPER_INGOT){
        @Override
        public Item getBowl() {
            return ModItems.COPPER_STONE_BOWL;
        }
    });
    public static final LiquidMetal TIN = registryHelper.register("tin",new LiquidMetal(ModItems.TIN_INGOT){
        @Override
        public Item getBowl() {
            return ModItems.TIN_STONE_BOWL;
        }
    });
    public static final LiquidMetal BRONZE = registryHelper.register("bronze",new LiquidMetal(ModItems.BRONZE_INGOT){
        @Override
        public Item getBowl() {
            return ModItems.BRONZE_STONE_BOWL;
        }
    });
    public static final LiquidMetal EMPTY= registryHelper.register("empty",new LiquidMetal(ItemStack.EMPTY.getItem()));

    public static void init(){

    }
}
