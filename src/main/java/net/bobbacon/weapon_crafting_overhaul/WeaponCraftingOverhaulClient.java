package net.bobbacon.weapon_crafting_overhaul;

import net.bobbacon.weapon_crafting_overhaul.client.render.ModEntitiesRenderers;
import net.bobbacon.weapon_crafting_overhaul.screen.SmithingScreen;
import net.bobbacon.weapon_crafting_overhaul.screen.ModScreenHandlers;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class WeaponCraftingOverhaulClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModEntitiesRenderers.init();

		HandledScreens.register(ModScreenHandlers.FORGING_SCREEN_HANDLER, SmithingScreen::new);
	}
}