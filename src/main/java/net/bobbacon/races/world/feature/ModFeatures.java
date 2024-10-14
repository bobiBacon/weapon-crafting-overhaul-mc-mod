package net.bobbacon.races.world.feature;

import net.bobbacon.races.RacesModForMyServer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public abstract class ModFeatures {
//    public static final RegistryKey<Feature<?>> ROCKS_KEY = registerKey("rocks");

    public static final RocksFeature ROCKS_FEATURE= register("rocks",new RocksFeature(DefaultFeatureConfig.CODEC));
    public static void init(){

    }
    public static RegistryKey<Feature<?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.FEATURE, new Identifier(RacesModForMyServer.MOD_ID, name));
    }

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }
}
