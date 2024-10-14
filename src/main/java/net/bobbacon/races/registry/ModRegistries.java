package net.bobbacon.races.registry;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.liquid_metal.LiquidMetal;
import net.bobbacon.races.race.Race;
import net.bobbacon.races.talent.Talent;
import net.bobbacon.races.talent.TalentTree;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;

public class ModRegistries {
    public static final SimpleRegistry<Race> RACE = FabricRegistryBuilder.createSimple(Race.class, new Identifier(RacesModForMyServer.MOD_ID, "race_registry"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
    public static final SimpleRegistry<TalentTree> TALENT_TREES = FabricRegistryBuilder.createSimple(TalentTree.class,new Identifier(RacesModForMyServer.MOD_ID,"talent_tree_registry"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
    public static final SimpleRegistry<Talent> TALENTS = FabricRegistryBuilder.createSimple(Talent.class,new Identifier(RacesModForMyServer.MOD_ID,"talent_registry"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
    public static final SimpleRegistry<LiquidMetal> LIQUID_METALS = FabricRegistryBuilder.createSimple(LiquidMetal.class,new Identifier(RacesModForMyServer.MOD_ID,"liquid_metal_registry"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
}
