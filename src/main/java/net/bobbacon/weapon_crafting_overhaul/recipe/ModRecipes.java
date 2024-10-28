package net.bobbacon.weapon_crafting_overhaul.recipe;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class ModRecipes {
    public static final RecipeSerializer<BrickFurnaceCookingRecipe> BRICK_FURNACE_COOKING_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(WeaponCraftingOverhaul.MOD_ID, "brick_furnace_cooking"), new MudOvenCookingRecipeSerializer<>(BrickFurnaceCookingRecipe::new, 400));
    public static void init(){

    }
}
