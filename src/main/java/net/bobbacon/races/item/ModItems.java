package net.bobbacon.races.item;

import net.bobbacon.races.block.ModBlocks;
import net.bobbacon.races.forging.ModToolMaterials;
import net.bobbacon.races.liquid_metal.LiquidMetals;
import net.bobbacon.races.registry.RegistryHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_engine.api.spell.ExternalSpellSchools;

import java.util.ArrayList;
import java.util.Map;

import static net.bobbacon.races.RacesModForMyServer.MOD_ID;

public class ModItems  {
    public static final ArrayList<Weapon.Entry> entries = new ArrayList<>();

    private static Weapon.Entry entry(String name, Weapon.CustomMaterial material,float damage,float attackSpeed, Item item, ItemConfig.Weapon defaults) {
        return entry(null, name, material,damage,attackSpeed, item, defaults);
    }

    private static Weapon.Entry entry(String requiredMod, String name, Weapon.CustomMaterial material,float damage,float attackSpeed, Item item, ItemConfig.Weapon defaults) {
        Weapon.Entry entry = new Weapon.Entry(MOD_ID, name, material, item, defaults, null);
        if (entry.isRequiredModInstalled()) {
            entries.add(entry);
        }
        return entry;
    }

//    public static final Item ONE_HANDED_WAR_AXE= Registry.register(Registries.ITEM, new Identifier(MOD_ID,"one_handed_war_axe"),new AxeItem(ToolMaterials.IRON,5,-2.5f,new FabricItemSettings()));
    public static final Weapon.Entry ONE_HANDED_WAR_AXE= entry("one_handed_war_axe", Weapon.CustomMaterial.matching(ToolMaterials.IRON,
        ()-> Ingredient.ofItems(Items.STICK)),7,-2.8f,new OneHandedWarAxe(ToolMaterials.IRON,7,-2.8f,new FabricItemSettings(), ExternalSpellSchools.PHYSICAL_MELEE),new ItemConfig.Weapon(7,-2.8f)).attribute(ItemConfig.Attribute.multiply(ExternalSpellSchools.PHYSICAL_MELEE.id, 20));

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
    public static final Item COPPER_STONE_BOWL= registryHelper.register("copper_stone_bowl",new StoneBowl(LiquidMetals.COPPER,bowl()));
    public static final Item TIN_STONE_BOWL= registryHelper.register("tin_stone_bowl", new StoneBowl(LiquidMetals.TIN,bowl()));
    public static final Item BRONZE_STONE_BOWL = registryHelper.register("bronze_stone_bowl", new StoneBowl(LiquidMetals.BRONZE,bowl()));

    public static final Item PICKAXE_MOLD = registryHelper.register("pickaxe_mold",new Item(new Item.Settings()));
    public static final Item AXE_MOLD = registryHelper.register("axe_mold",new Item(new Item.Settings()));
    public static final Item WAR_AXE_MOLD = registryHelper.register("war_axe_mold",new Item(new Item.Settings()));

    public static final Item COPPER_PICKAXE_MOLD= registryHelper.register("copper_pickaxe_mold", new Item(new Item.Settings()));
    public static final Item COPPER_AXE_MOLD = registryHelper.register("copper_axe_mold", new Item(new Item.Settings()));

    public static void init() {
    }
    public static void register(Map<String, ItemConfig.Weapon> configs) {
        Weapon.register(configs, entries, RegistryKey.of(Registries.ITEM_GROUP.getKey(),new Identifier(MOD_ID,"generic")));
    }
    private static Item.Settings bowl(){
        return new Item.Settings().recipeRemainder(STONE_BOWL);
    }
}
