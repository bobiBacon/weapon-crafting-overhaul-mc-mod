package net.bobbacon.races.registry;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.race.Race;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.util.Identifier;

public class ModRegistries {
    public static final SimpleRegistry<Race> RACE = FabricRegistryBuilder.createSimple(Race.class, new Identifier(RacesModForMyServer.MOD_ID, "race_registry"))
            .attribute(RegistryAttribute.SYNCED)
            .buildAndRegister();
}
