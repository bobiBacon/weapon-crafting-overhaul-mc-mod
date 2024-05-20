package net.bobbacon.races.player;

import net.minecraft.util.Identifier;

import java.util.ArrayList;

public interface IPlayerEntityMixin {
    public final ArrayList<Identifier> spells= new ArrayList<>();

    void learn(Identifier id);
}
