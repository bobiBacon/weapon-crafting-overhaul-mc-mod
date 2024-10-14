package net.bobbacon.races;

import net.bobbacon.races.datagen.*;
import net.bobbacon.races.world.feature.ModConfiguredFeatures;
import net.bobbacon.races.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class RacesModForMyServerDataGenerator implements DataGeneratorEntrypoint {
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
