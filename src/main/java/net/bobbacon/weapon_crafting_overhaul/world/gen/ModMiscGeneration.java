package net.bobbacon.weapon_crafting_overhaul.world.gen;

import net.bobbacon.weapon_crafting_overhaul.world.feature.ModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class ModMiscGeneration {
    public static void generateMisc(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.VEGETAL_DECORATION, ModPlacedFeatures.ROCKS_PLACED_KEY);
    }
}
