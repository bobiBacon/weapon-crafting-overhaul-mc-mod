package net.bobbacon.weapon_crafting_overhaul.registry;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.liquid_metal.LiquidMetal;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;

public class ModRegistries {
    public static final SimpleRegistry<LiquidMetal> LIQUID_METALS = FabricRegistryBuilder.createSimple(LiquidMetal.class,new Identifier(WeaponCraftingOverhaul.MOD_ID,"liquid_metal_registry"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
}
