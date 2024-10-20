package net.bobbacon.weapon_crafting_overhaul.forging;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.HashMap;

public enum Fuel {
    STICK(1),
    COAL(15),
    CHARCOAL(15),
    Empty(0);

    private final int power;
    private static final HashMap<Item,Fuel> map = new HashMap<>();
    static {
        map.put(Items.STICK,Fuel.STICK);
        map.put(Items.COAL,Fuel.COAL);
        map.put(Items.CHARCOAL,Fuel.CHARCOAL);
    }

    Fuel(int power){
        this.power = power;
    }

    public static Fuel getFuelFromItem(Item item){
        return map.getOrDefault(item,Empty);
    }

    public int getPower() {
        return power;
    }
}
