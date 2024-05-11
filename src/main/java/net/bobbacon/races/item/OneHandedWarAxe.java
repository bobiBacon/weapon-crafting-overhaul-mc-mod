package net.bobbacon.races.item;

import net.minecraft.item.ToolMaterial;
import net.spell_engine.api.item.weapon.SpellWeaponItem;

public class OneHandedWarAxe extends SpellWeaponItem {

    public OneHandedWarAxe(ToolMaterial material, Settings settings) {
        super(material, settings);
    }

    public OneHandedWarAxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }
}
