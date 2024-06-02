package net.bobbacon.races;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.bobbacon.races.config.Default;
import net.bobbacon.races.item.ModItems;
import net.bobbacon.races.race.Race;
import net.bobbacon.races.race.Races;
import net.bobbacon.races.talent.TalentTree;
import net.bobbacon.races.talent.TalentTrees;
import net.fabricmc.api.ModInitializer;

import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.config.ServerConfigWrapper;
import net.tinyconfig.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RacesModForMyServer implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("races");
	public static final String MOD_ID = "races";


	public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
			("items_v7", Default.itemConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");

		Races.init();

		TalentTree.init();
		TalentTrees.init();

		ModItems.init();
		ModItems.register(itemConfig.value.weapons);
	}
}