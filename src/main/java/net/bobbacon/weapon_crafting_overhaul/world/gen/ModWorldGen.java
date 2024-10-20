package net.bobbacon.weapon_crafting_overhaul.world.gen;

public class ModWorldGen {
    public static void WorldGen() {
        ModMiscGeneration.generateMisc();
        ModOreGeneration.generateOre();
    }
}
