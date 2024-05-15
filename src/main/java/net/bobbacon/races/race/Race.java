package net.bobbacon.races.race;

import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;

public class Race implements ToggleableFeature {
    private final FeatureSet requiredFeatures;
    private final String name;


    public Race(Settings settings) {
        requiredFeatures= settings.requiredFeatures;
        name= settings.name;
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return null;
    }

    @Override
    public String toString() {
        return name;
    }

    public static class Settings{
        String name= "name";
        FeatureSet requiredFeatures = FeatureFlags.VANILLA_FEATURES;

        public Settings name(String name){
            this.name=name;
            return this;
        }

    }


}
