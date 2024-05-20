package net.bobbacon.races.player;

public interface IItemMixin {
    WeaponTypes weaponType= WeaponTypes.NONE;

    public WeaponTypes getWeaponType();
    public void setWeaponType(WeaponTypes wp);
}
