package net.bobbacon.weapon_crafting_overhaul.mixin;

import net.minecraft.item.ToolMaterials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ToolMaterials.class)
public class ToolMaterialsMixin {



    @Mutable
    @Shadow @Final private int miningLevel;

    @Inject(method = "<init>",at= @At("TAIL"))
    void injectInit(String string, int i, int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier repairIngredient, CallbackInfo ci){
        if (miningLevel>0) miningLevel++;
        this.miningLevel= miningLevel;
    }

}
