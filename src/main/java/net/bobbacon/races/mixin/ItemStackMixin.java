package net.bobbacon.races.mixin;

import net.bobbacon.races.player.IItemMixin;
import net.bobbacon.races.player.IItemStackMixin;
import net.bobbacon.races.player.WeaponTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin implements IItemStackMixin {
    @Shadow public abstract Item getItem();

    @Override
    @Unique
    public WeaponTypes getWeaponType() {
        IItemMixin item=(IItemMixin) getItem();
        return item.getWeaponType();
    }
}
