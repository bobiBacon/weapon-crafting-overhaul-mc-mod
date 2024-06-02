package net.bobbacon.races.registry;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.talent.Talent;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class RegistryHelper<T> {
    Registry<T> registry;

    public RegistryHelper(Registry<T> registry) {
        this.registry=registry;
    }


    public T register(String name,T element){
        return Registry.register(registry, new Identifier(RacesModForMyServer.MOD_ID,name),element);
    }
}
