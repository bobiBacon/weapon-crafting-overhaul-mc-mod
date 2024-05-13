package net.bobbacon.races.item;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.weapon.StaffItem;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_engine.api.spell.ExternalSpellSchools;
import net.spell_power.api.SpellSchool;
import net.spell_power.api.SpellSchools;

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
        ()-> Ingredient.ofItems(Items.STICK)),7,-2.8f,new OneHandedWarAxe(ToolMaterials.IRON,7,-2.8f,new FabricItemSettings(), ExternalSpellSchools.PHYSICAL_MELEE),new ItemConfig.Weapon(7,-2.8f));
    public static void init() {
    }
    public static void register(Map<String, ItemConfig.Weapon> configs) {
        Weapon.register(configs, entries, RegistryKey.of(Registries.ITEM_GROUP.getKey(),new Identifier(MOD_ID,"generic")));
    }
}
