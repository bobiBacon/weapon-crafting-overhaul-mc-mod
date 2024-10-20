package net.bobbacon.weapon_crafting_overhaul;

import net.bobbacon.weapon_crafting_overhaul.datagen.*;
import net.bobbacon.weapon_crafting_overhaul.world.feature.ModConfiguredFeatures;
import net.bobbacon.weapon_crafting_overhaul.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class WeaponCraftingOverhaulDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {

		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModModelGenerator::new);
		pack.addProvider(ModBlockLootTableGenerator::new);
		pack.addProvider(ModWorldGenerator::new);
		pack.addProvider(ModBlockTagsGenerator::new);
		pack.addProvider(ModRecipeGenerator::new);



//		DataGenerator.Pack vanilla= fabricDataGenerator.createVanillaSubPack();
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::boostrap);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::boostrap);
	}
}
