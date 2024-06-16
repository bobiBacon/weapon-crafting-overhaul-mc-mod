package net.bobbacon.races.talent;

import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;

public class Talent implements ToggleableFeature {
    int cost;
    int level;

    public Talent(int cost) {
        this.cost = cost;
        level=0;
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return null;
    }

    public int getLevel() {
        return level;
    }
    public boolean isLearned(){
        return level>0;
    }
}
