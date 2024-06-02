package net.bobbacon.races.talent;

import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;

public class Talent implements ToggleableFeature {
    int cost;

    public Talent(int cost) {
        this.cost = cost;
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return null;
    }
}
