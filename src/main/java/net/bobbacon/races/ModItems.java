package net.bobbacon.races;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.item.weapon.StaffItem;

import static net.bobbacon.races.RacesModForMyServer.MOD_ID;

public class ModItems  {
    public static final Item ONE_HANDED_WAR_AXE= Registry.register(Registries.ITEM, new Identifier(MOD_ID,"one_handed_war_axe"),new AxeItem(ToolMaterials.IRON,5,-2.5f,new FabricItemSettings()));

    public static void init() {
    }
}
