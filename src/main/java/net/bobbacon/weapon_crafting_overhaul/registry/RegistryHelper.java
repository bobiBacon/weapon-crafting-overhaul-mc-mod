package net.bobbacon.weapon_crafting_overhaul.registry;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class RegistryHelper<T> {
    Registry<T> registry;

    public RegistryHelper(Registry<T> registry) {
        this.registry=registry;
    }


    public T register(String name,T element){
        return Registry.register(registry, new Identifier(WeaponCraftingOverhaul.MOD_ID,name),element);
    }
}
