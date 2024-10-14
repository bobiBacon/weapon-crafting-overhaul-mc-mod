package net.bobbacon.races.recipe;

import net.bobbacon.races.RacesModForMyServer;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public abstract class ModRecipes {
    public static final RecipeSerializer<BrickFurnaceCookingRecipe> BRICK_FURNACE_COOKING_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(RacesModForMyServer.MOD_ID, "brick_furnace_cooking"), new CookingRecipeSerializer<>(BrickFurnaceCookingRecipe::new, 100));
    public static void init(){

    }
}
