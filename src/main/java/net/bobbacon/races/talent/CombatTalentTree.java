package net.bobbacon.races.talent;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.registry.ModRegistries;
import net.bobbacon.races.registry.RegistryHelper;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class CombatTalentTree extends TalentTree{
    RegistryHelper<Talent> registryHelper= new RegistryHelper<>(ModRegistries.TALENTS);
    Talent Weapons1= registryHelper.register("weapons1",new Talent(20));
    Talent Weapons2= registryHelper.register("weapons2",new Talent(50));
    Talent Weapons3= registryHelper.register("weapons3",new Talent(100));

    @Override
    public List<Talent> getTalents() {
        ArrayList<Talent> list= new ArrayList<>();
//        list.add(Weapons1);
//        list.add(Weapons2);
//        list.add(Weapons3);

        return list;
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
