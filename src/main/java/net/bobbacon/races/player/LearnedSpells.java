package net.bobbacon.races.player;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class LearnedSpells {
    public final ArrayList<Identifier> spells= new ArrayList<>();

    public void learn(Identifier id){
        spells.add(id);
    }
}
