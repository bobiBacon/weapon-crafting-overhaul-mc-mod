package net.bobbacon.races.talent;

import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.ArrayList;
import java.util.List;

public class CraftingTalentTree extends TalentTree{
    @Override
    public List<Talent> getTalents() {
        return new ArrayList<>();
    }

    @Override
    public FeatureSet getRequiredFeatures() {
        return null;
    }

    @Override
    public boolean isEnabled(FeatureSet enabledFeatures) {
        return super.isEnabled(enabledFeatures);
    }
    public static void init() {
    }
}
