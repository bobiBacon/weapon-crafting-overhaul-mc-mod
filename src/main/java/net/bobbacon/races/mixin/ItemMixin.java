package net.bobbacon.races.mixin;

import net.bobbacon.races.player.IItemMixin;
import net.bobbacon.races.player.IItemStackMixin;
import net.bobbacon.races.player.WeaponTypes;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(Item.class)
public class ItemMixin implements IItemMixin {
    @Unique
    WeaponTypes weaponType= WeaponTypes.NONE;

    @Unique
    @Override
    public WeaponTypes getWeaponType() {
        return weaponType;
    }

    @Override
    @Unique
    public void setWeaponType(WeaponTypes wp) {
        weaponType=wp;
    }

}
