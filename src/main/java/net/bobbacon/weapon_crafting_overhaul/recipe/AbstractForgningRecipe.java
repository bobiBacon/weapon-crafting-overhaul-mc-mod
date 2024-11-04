package net.bobbacon.weapon_crafting_overhaul.recipe;

import net.bobbacon.weapon_crafting_overhaul.recipe.types.ModRecipeTypes;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public abstract class AbstractForgningRecipe implements Recipe<Inventory> {
    public AbstractForgningRecipe() {

    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.FORGING;
    }
}
