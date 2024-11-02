package net.bobbacon.weapon_crafting_overhaul;

import net.bobbacon.weapon_crafting_overhaul.client.render.ModEntitiesRenderers;
import net.fabricmc.api.ClientModInitializer;

public class WeaponCraftingOverhaulClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModEntitiesRenderers.init();
	}
}