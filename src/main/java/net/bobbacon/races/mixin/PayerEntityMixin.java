package net.bobbacon.races.mixin;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.player.IItemStackMixin;
import net.bobbacon.races.player.IPlayerEntityMixin;
import net.bobbacon.races.player.LearnedSpells;
import net.bobbacon.races.player.WeaponTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.spell_engine.internals.SpellContainerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)

public abstract class PayerEntityMixin extends LivingEntityMixin implements IPlayerEntityMixin {



    @Inject(method = "tick", at = @At("TAIL"))
    private void injectMethod(CallbackInfo info) {
        for(Identifier id:getSpells().spells) {
            if (canAddSpell(this.getMainHandStack(),id)){
                SpellContainerHelper.addSpell(id, getMainHandStack());
                
            }
        }

    }

    @Unique
    public LearnedSpells spells= new LearnedSpells();

    @Unique
    @Override
    public LearnedSpells getSpells(){
        return spells;
    }

    @Unique
    public boolean canAddSpell(ItemStack stack, Identifier spellId){
        IItemStackMixin asMixin= (IItemStackMixin) (Object)stack;
        WeaponTypes wp= asMixin.getWeaponType();
        if (wp==WeaponTypes.NONE){
            RacesModForMyServer.LOGGER.info("not an weapon");
            return false;
        }
        if (wp== WeaponTypes.AXE){
            if (spellId.toString().equals("races:simple_spin")){
                return true;
            }
        }
        return false;
    }
}
