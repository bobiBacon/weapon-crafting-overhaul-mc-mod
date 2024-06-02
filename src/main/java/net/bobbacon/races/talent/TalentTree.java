package net.bobbacon.races.talent;

import net.minecraft.resource.featuretoggle.ToggleableFeature;

import java.util.List;

public abstract class TalentTree implements ToggleableFeature {
    public abstract List<Talent> getTalents();

    public static void init(){
        CombatTalentTree.init();
        CraftingTalentTree.init();
    }
}
