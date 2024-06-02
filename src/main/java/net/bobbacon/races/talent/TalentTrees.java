package net.bobbacon.races.talent;

import net.bobbacon.races.registry.ModRegistries;
import net.bobbacon.races.registry.RegistryHelper;

public class TalentTrees {
    private static final RegistryHelper<TalentTree> registryHelper= new RegistryHelper<>(ModRegistries.TALENT_TREES);
    public static final TalentTree COMBAT= registryHelper.register("combat_talent_tree",new CombatTalentTree());
    public static final TalentTree CRAFTING= registryHelper.register("crafting_talent_tree",new CraftingTalentTree());

    public static void init() {
    }
}
