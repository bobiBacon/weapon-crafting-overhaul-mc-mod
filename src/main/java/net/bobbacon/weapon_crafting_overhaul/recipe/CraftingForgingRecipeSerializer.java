package net.bobbacon.weapon_crafting_overhaul.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.util.Identifier;

public class CraftingForgingRecipeSerializer <T extends CraftingForgingRecipe> implements RecipeSerializer<T> {




    public  CraftingForgingRecipeSerializer(RecipeFactory<T> recipeFactory) {
    }



    @Override
    public T read(Identifier id, JsonObject json) {
        return null;
    }

    @Override
    public T read(Identifier id, PacketByteBuf buf) {
        return null;
    }

    @Override
    public void write(PacketByteBuf buf, T recipe) {

    }
    public interface RecipeFactory<T extends CraftingForgingRecipe> {
        //doit correspondre au constructeur de la recete
        T create(/*Identifier id, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime*/);
    }
}