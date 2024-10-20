package net.bobbacon.weapon_crafting_overhaul.mixin;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ToolItem.class)
public abstract class ToolItemMixin {
    @Shadow @Final private ToolMaterial material;

    @Shadow
    public ToolMaterial getMaterial(){
        return material;
    }
}
