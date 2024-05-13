package net.bobbacon.races.race;

import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;

public class Race implements ToggleableFeature {
    private final FeatureSet requiredFeatures;


    public Race(Settings settings) {
        requiredFeatures= settings.requiredFeatures;
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return null;
    }

    public static class Settings{
        FeatureSet requiredFeatures = FeatureFlags.VANILLA_FEATURES;

    }

}
