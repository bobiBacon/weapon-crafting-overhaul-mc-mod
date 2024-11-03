package net.bobbacon.weapon_crafting_overhaul.sounds;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.registry.RegistryHelper;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    private static final RegistryHelper<SoundEvent> registryHelper= new RegistryHelper<>(Registries.SOUND_EVENT);
    public static final SoundEvent BELLOWS= registryHelper.register("bellows",
            SoundEvent.of(new Identifier(WeaponCraftingOverhaul.MOD_ID,"bellows")));
    public static void init(){

    }
}
