package net.bobbacon.races.world.feature;

import net.bobbacon.races.RacesModForMyServer;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> ROCKS_PLACED_KEY = registerKey("rocks");
    public static final RegistryKey<PlacedFeature> TIN_ORE_PLACED_KEY = registerKey("tin_ore_placed");
//    public static PlacedFeature ROCKS_FEATURE_PLACED = new PlacedFeature(
//            RegistryEntry.of(
//                    ModConfiguredFeatures.ROCKS_FEATURE_CONFIGURED
//                    //  the SquarePlacementModifier makes the feature generate a cluster of pillars each time
//            ), List.of(SquarePlacementModifier.of())
//    );
//    public static void init(){
//        Registry.register(BuiltinRegistries., EXAMPLE_FEATURE_ID, EXAMPLE_FEATURE_CONFIGURED);
//    }
    public static void boostrap(Registerable<PlacedFeature> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

        register(context, TIN_ORE_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.TIN_ORE_KEY),
                ModOrePlacement.modifiersWithCount(12, // Veins per Chunk
                        HeightRangePlacementModifier.trapezoid(YOffset.fixed(-20), YOffset.fixed(80))));

        register(context, ROCKS_PLACED_KEY, configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ROCKS_KEY),
                SquarePlacementModifier.of(), CountPlacementModifier.of(5));
    }

    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(RacesModForMyServer.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
    public static void register(Registerable<PlacedFeature> featureRegisterable, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> feature, PlacementModifier... modifiers) {
        register(featureRegisterable, key, feature, List.of(modifiers));
    }
}
