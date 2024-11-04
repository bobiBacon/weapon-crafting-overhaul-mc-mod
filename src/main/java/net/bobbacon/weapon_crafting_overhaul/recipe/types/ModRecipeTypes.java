package net.bobbacon.weapon_crafting_overhaul.recipe.types;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.recipe.BrickFurnaceCookingRecipe;
import net.bobbacon.weapon_crafting_overhaul.recipe.AbstractForgningRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class ModRecipeTypes {
    public static final RecipeType<BrickFurnaceCookingRecipe> BRICK_FURNACE_COOKING = Registry.register(Registries.RECIPE_TYPE, new Identifier(WeaponCraftingOverhaul.MOD_ID, "brick_furnace_cooking"), new RecipeType<BrickFurnaceCookingRecipe>() {
        @Override
        public String toString() {
            return "brick_furnace_cooking";
        }
    });
    public static final RecipeType<AbstractForgningRecipe> FORGING = Registry.register(Registries.RECIPE_TYPE, new Identifier(WeaponCraftingOverhaul.MOD_ID, "forging"), new RecipeType<AbstractForgningRecipe>() {
        @Override
        public String toString() {
            return "forging";
        }
    });

    public static void init(){

    }
}
