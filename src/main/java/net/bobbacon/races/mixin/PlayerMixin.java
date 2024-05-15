package net.bobbacon.races.mixin;


import net.bobbacon.races.IPlayerMixin;
import net.bobbacon.races.race.Race;
import net.bobbacon.races.race.Races;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin implements IPlayerMixin {
    @Unique
    private Race race= Races.HUMAN;

    @Unique
    public Race getRace(){
        return this.race;
    }
}
