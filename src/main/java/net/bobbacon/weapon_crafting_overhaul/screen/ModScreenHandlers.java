package net.bobbacon.weapon_crafting_overhaul.screen;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<SmithingScreenHandler> FORGING_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(WeaponCraftingOverhaul.MOD_ID,"forging"),
                    new ExtendedScreenHandlerType<>(SmithingScreenHandler::new));
    public static void init(){

    }
}
