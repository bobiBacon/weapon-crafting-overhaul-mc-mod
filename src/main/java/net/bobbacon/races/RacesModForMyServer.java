package net.bobbacon.races;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.bobbacon.races.block.ModBlocks;
import net.bobbacon.races.block.block_entity.ModBE;
import net.bobbacon.races.config.Default;
import net.bobbacon.races.item.ModItems;
import net.bobbacon.races.liquid_metal.LiquidMetals;
import net.bobbacon.races.race.Race;
import net.bobbacon.races.race.Races;
import net.bobbacon.races.recipe.ModRecipes;
import net.bobbacon.races.recipe.types.ModRecipeTypes;
import net.bobbacon.races.talent.TalentTree;
import net.bobbacon.races.talent.TalentTrees;
import net.bobbacon.races.world.feature.ModFeatures;
import net.bobbacon.races.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
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

	private static final Identifier COPPER_ORE_LOOT_TABLE_ID = Blocks.COPPER_ORE.getLootTableId();


	public static ConfigManager<ItemConfig> itemConfig = new ConfigManager<ItemConfig>
			("items_v7", Default.itemConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	@Override
	public void onInitialize() {


		LOGGER.info("Hello Fabric world!");

		Races.init();

		TalentTree.init();
		TalentTrees.init();

		ModItems.init();
		ModItems.register(itemConfig.value.weapons);

		ModBlocks.init();
		ModBE.init();

		ModRecipeTypes.init();
		ModRecipes.init();

		ModFeatures.init();
		ModWorldGen.WorldGen();

		LiquidMetals.init();



//		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
//			// Let's only modify built-in loot tables and leave data pack loot tables untouched by checking the source.
//			// We also check that the loot table ID is equal to the ID we want.
//			if (source.isBuiltin() && COPPER_ORE_LOOT_TABLE_ID.equals(id)) {
//				// Our code will go here
//				LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(ModItems.ROCK))).with(ItemEntry.builder(Items.RAW_IRON));
//
//				tableBuilder.pool(poolBuilder);
//			}
//		});
	}
}