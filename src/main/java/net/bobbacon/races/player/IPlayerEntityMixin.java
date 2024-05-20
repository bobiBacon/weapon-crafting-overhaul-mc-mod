package net.bobbacon.races.player;

public interface IPlayerEntityMixin {
    public LearnedSpells spells = new LearnedSpells();

    public LearnedSpells getSpells();
}
