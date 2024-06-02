package net.bobbacon.races.player;

import net.bobbacon.races.race.Race;
import net.bobbacon.races.race.Races;
import net.bobbacon.races.talent.Talent;
import net.bobbacon.races.talent.TalentTree;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Unique;

import java.util.ArrayList;
import java.util.List;

public interface IPlayerEntityMixin {
    public final ArrayList<Identifier> spells= new ArrayList<>();

    void learn(Identifier id);

    public Race getRace();
    List<TalentTree> getTalents();
}
