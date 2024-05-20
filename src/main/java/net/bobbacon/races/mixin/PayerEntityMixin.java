package net.bobbacon.races.mixin;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.player.IItemStackMixin;
import net.bobbacon.races.player.IPlayerEntityMixin;
import net.bobbacon.races.player.WeaponTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.spell_engine.internals.SpellContainerHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(PlayerEntity.class)

public abstract class PayerEntityMixin extends LivingEntityMixin implements IPlayerEntityMixin {
    @Unique
    public final ArrayList<Identifier> spells= new ArrayList<>();




    @Inject(method = "tick", at = @At("TAIL"))
    private void injectTick(CallbackInfo info) {
        ItemStack stack=this.getMainHandStack();
        IItemStackMixin asMixin= (IItemStackMixin) (Object)stack;
        if (asMixin.getWeaponType()!=WeaponTypes.NONE){
            for(Identifier id:spells) {
                if (canAddSpell(stack,id)){
                    SpellContainerHelper.addSpell(id, getMainHandStack());

                }
            }
        }



    }

    @Inject(method = "initDataTracker",at = @At("TAIL"))
    private void injectInitDataTracker(CallbackInfo info){

    }






    @Unique
    public boolean canAddSpell(ItemStack stack, Identifier spellId){
        IItemStackMixin asMixin= (IItemStackMixin) (Object)stack;
        WeaponTypes wp= asMixin.getWeaponType();
        if (wp==WeaponTypes.NONE){
            RacesModForMyServer.LOGGER.info("not a weapon");
            return false;
        }
        if (wp== WeaponTypes.AXE){
            if (spellId.toString().equals("races:simple_spin")){
                return true;
            }
        }
        return false;
    }
    @Unique
    public void learn(Identifier id){
        spells.add(id);
    }
}
