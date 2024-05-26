package net.bobbacon.races;

import net.bobbacon.races.key_blinds.ModKeybindings;
import net.fabricmc.api.ClientModInitializer;

public class RacesModForMyServerClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ModKeybindings.init();
	}
}