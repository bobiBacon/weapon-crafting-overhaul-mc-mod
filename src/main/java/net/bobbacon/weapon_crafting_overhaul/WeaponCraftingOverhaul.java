package net.bobbacon.weapon_crafting_overhaul;

import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.bobbacon.weapon_crafting_overhaul.block.block_entity.ModBE;
import net.bobbacon.weapon_crafting_overhaul.item.ModItems;
import net.bobbacon.weapon_crafting_overhaul.liquid_metal.LiquidMetals;
import net.bobbacon.weapon_crafting_overhaul.recipe.ModRecipes;
import net.bobbacon.weapon_crafting_overhaul.recipe.types.ModRecipeTypes;
import net.bobbacon.weapon_crafting_overhaul.world.feature.ModFeatures;
import net.bobbacon.weapon_crafting_overhaul.world.gen.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeaponCraftingOverhaul implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("weapon_crafting_overhaul");
	public static final String MOD_ID = "weapon_crafting_overhaul";

//	private static final Identifier COPPER_ORE_LOOT_TABLE_ID = Blocks.COPPER_ORE.getLootTableId();



	@Override
	public void onInitialize() {


		LOGGER.info("Hello Fabric world!");



		ModItems.init();

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