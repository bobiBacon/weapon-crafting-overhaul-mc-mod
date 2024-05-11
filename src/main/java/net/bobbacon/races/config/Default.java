package net.bobbacon.races.config;

import net.bobbacon.races.item.ModItems;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.weapon.Weapon;

public class Default {
    public static final ItemConfig itemConfig;
    static {
        itemConfig= new ItemConfig();
        for (Weapon.Entry weapon: ModItems.entries) {
            itemConfig.weapons.put(weapon.name(), weapon.defaults());
        }
    }
}
