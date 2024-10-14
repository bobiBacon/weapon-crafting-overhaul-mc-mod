package net.bobbacon.races.race;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.registry.ModRegistries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.bobbacon.races.RacesModForMyServer.MOD_ID;

public class Races {
    public static final Race DWARF= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"dwarf"),new Race(new Race.Settings()));
    public static final Race ELF= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"elf"),new Race(new Race.Settings()));
    public static final Race HUMAN= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"human"),new Race(new Race.Settings()));
    public static final Race RAT= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"rat"),new Race(new Race.Settings()));
    public static final Race ORC= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"orc"),new Race(new Race.Settings()));
    public static final Race DROW= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"drow"),new Race(new Race.Settings()));
    public static final Race SPIRIT= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"spirit"),new Race(new Race.Settings()));
    public static final Race HALF_ORC= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"half_orc"),new Race(new Race.Settings()));
    public static final Race HALF_DEMON= Registry.register(ModRegistries.RACE,new Identifier(MOD_ID,"half_demon"),new Race(new Race.Settings()));

    public static void init(){
        
    }
}
