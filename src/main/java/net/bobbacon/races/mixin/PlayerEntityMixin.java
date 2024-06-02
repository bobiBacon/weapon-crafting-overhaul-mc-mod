package net.bobbacon.races.mixin;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.player.IItemStackMixin;
import net.bobbacon.races.player.IPlayerEntityMixin;
import net.bobbacon.races.player.WeaponTypes;
import net.bobbacon.races.race.Race;
import net.bobbacon.races.race.Races;
import net.bobbacon.races.talent.*;
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
import java.util.List;

@Mixin(PlayerEntity.class)

public abstract class PlayerEntityMixin extends LivingEntityMixin implements IPlayerEntityMixin {
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

    @Unique
    private Race race= Races.HUMAN;

    @Unique
    private ArrayList<TalentTree> talentTrees= new ArrayList<>(List.of(new TalentTree[]{TalentTrees.COMBAT, TalentTrees.CRAFTING}));

    @Override
    @Unique
    public List<TalentTree> getTalents() {
        return talentTrees;
    }

    @Unique
    public Race getRace(){
        return this.race;
    }
}
