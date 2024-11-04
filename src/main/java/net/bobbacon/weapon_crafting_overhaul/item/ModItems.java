package net.bobbacon.weapon_crafting_overhaul.item;

import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.bobbacon.weapon_crafting_overhaul.forging.ModToolMaterials;
import net.bobbacon.weapon_crafting_overhaul.liquid_metal.LiquidMetals;
import net.bobbacon.weapon_crafting_overhaul.registry.RegistryHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul.MOD_ID;

public class ModItems  {
    static RegistryHelper<Item> registryHelper= new RegistryHelper<Item>(Registries.ITEM);
    public static final Item MITHRIL_INGOT= registryHelper.register("mithril_ingot",new Item(new FabricItemSettings()));
    public static final Item DWARFS_ALLOY_INGOT= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"dwarfs_alloy_ingot"),new Item(new FabricItemSettings()));
    public static final Item RAW_TIN= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"raw_tin"),new Item(new FabricItemSettings()));
    public static final Item TIN_INGOT= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"tin_ingot"),new Item(new FabricItemSettings()));
    public static final Item BRONZE_INGOT= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"bronze_ingot"),new Item(new FabricItemSettings()));
    public static final Item STEEL_INGOT= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"steel_ingot"),new Item(new FabricItemSettings()));
    public static final Item ELVEN_GOLD_INGOT= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"elven_gold_ingot"),new Item(new FabricItemSettings()));
    public static final Item DARK_INGOT= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"dark_mithril_ingot"),new DarkMithrilIngot(new Item.Settings()));
    public static final Item MITHRIL_NUGGET= Registry.register(Registries.ITEM,new Identifier(MOD_ID,"mithril_nugget"),new Item(new FabricItemSettings()));
    public static final Item ROCK= registryHelper.register("rock", new RockItem(ModBlocks.ROCKS,new FabricItemSettings()));
    public static final Item TIN_ORE_BLOCK= registryHelper.register("tin_ore", new BlockItem(ModBlocks.TIN_ORE, new FabricItemSettings()));
    public static final Item DEEP_TIN_ORE_BLOCK= registryHelper.register("deepslate_tin_ore", new BlockItem(ModBlocks.DEEP_TIN_ORE, new FabricItemSettings()));
    public static final Item COPPER_PICKAXE= registryHelper.register("copper_pickaxe",new PickaxeItem(ModToolMaterials.COPPER,1,-2.8f,new Item.Settings()));
    public static final Item COPPER_AXE= registryHelper.register("copper_axe",new AxeItem(ModToolMaterials.COPPER, 6.0F, -3.2F, new Item.Settings()));
    public static final Item BRICK_FURNACE = registryHelper.register("brick_furnace",new BlockItem(ModBlocks.MUD_OVEN,new FabricItemSettings()));
    public static final Item STONE_BOWL= registryHelper.register("stone_bowl",new StoneBowl(LiquidMetals.EMPTY,new Item.Settings()));
    public static final Item COPPER_STONE_BOWL= registryHelper.register("copper_stone_bowl",new StoneBowl(LiquidMetals.COPPER, bowlSettings()));
    public static final Item TIN_STONE_BOWL= registryHelper.register("tin_stone_bowl", new StoneBowl(LiquidMetals.TIN, bowlSettings()));
    public static final Item BRONZE_STONE_BOWL = registryHelper.register("bronze_stone_bowl", new StoneBowl(LiquidMetals.BRONZE, bowlSettings()));
    public static final Item PICKAXE_MOLD = registryHelper.register("pickaxe_mold",new Item(new Item.Settings()));
    public static final Item AXE_MOLD = registryHelper.register("axe_mold",new Item(new Item.Settings()));
    public static final Item WAR_AXE_MOLD = registryHelper.register("war_axe_mold",new Item(new Item.Settings()));
    public static final Item COPPER_PICKAXE_MOLD= registryHelper.register("copper_pickaxe_mold", new Item(new Item.Settings()));
    public static final Item COPPER_AXE_MOLD = registryHelper.register("copper_axe_mold", new Item(new Item.Settings()));
    public static final Item FABRIC = registryHelper.register("fabric", new Item(new Item.Settings()));
//    public static final Item BELLOWS = registryHelper.register("bellows", new BlockItem(ModBlocks.BELLOWS, new Item.Settings()));
    public static final Item BELLOWS = registryHelper.register("bellows", new BellowsBlockItem(ModBlocks.BELLOWS, new FabricItemSettings()));
    public static final Item BASIC_SMITHING_ANVIL = registryHelper.register("rustic_smithing_anvil", new BellowsBlockItem(ModBlocks.BASIC_SMITHING_ANVIL, new FabricItemSettings()));

    public static void init() {
    }

    private static Item.Settings bowlSettings(){
        return new Item.Settings().recipeRemainder(STONE_BOWL);
    }
}
