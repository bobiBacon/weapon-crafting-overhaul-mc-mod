package net.bobbacon.races;

import net.bobbacon.races.race.Race;
import net.bobbacon.races.race.Races;

public interface IPlayerMixin {
    Race race = Races.HUMAN;
    public Race getRace();
}
