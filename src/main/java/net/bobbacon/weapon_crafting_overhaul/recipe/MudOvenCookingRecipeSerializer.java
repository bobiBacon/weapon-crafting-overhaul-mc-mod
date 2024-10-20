package net.bobbacon.weapon_crafting_overhaul.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

public class MudOvenCookingRecipeSerializer <T extends BrickFurnaceCookingRecipe> implements RecipeSerializer<T> {
    private final int cookingTime;
    private final RecipeFactory<T> recipeFactory;

    public MudOvenCookingRecipeSerializer(RecipeFactory<T> recipeFactory, int cookingTime) {
        this.cookingTime = cookingTime;
        this.recipeFactory = recipeFactory;
    }
    @Override
    public T read(Identifier id, JsonObject json) {
        String string = JsonHelper.getString(json, "group", "");
        CookingRecipeCategory cookingRecipeCategory = (CookingRecipeCategory)CookingRecipeCategory.CODEC
                .byId(JsonHelper.getString(json, "category", null), CookingRecipeCategory.MISC);
        JsonElement jsonElement = (JsonElement)(JsonHelper.hasArray(json, "ingredient")
                ? JsonHelper.getArray(json, "ingredient")
                : JsonHelper.getObject(json, "ingredient"));
        Ingredient ingredient = Ingredient.fromJson(jsonElement, false);
        String string2 = JsonHelper.getString(json, "result");
        Identifier identifier2 = new Identifier(string2);
        ItemStack itemStack = new ItemStack(
                (ItemConvertible) Registries.ITEM.getOrEmpty(identifier2).orElseThrow(() -> new IllegalStateException("Item: " + string2 + " does not exist"))
        );
        float f = JsonHelper.getFloat(json, "experience", 0.0F);
        int i = JsonHelper.getInt(json, "cookingtime", this.cookingTime);
        boolean b= JsonHelper.getBoolean(json,"needsbellows",false);
        return this.recipeFactory.create(id, string, cookingRecipeCategory, ingredient, itemStack, f, i,b);
    }

    @Override
    public T read(Identifier id, PacketByteBuf buf) {
        return null;
    }

    @Override
    public void write(PacketByteBuf buf, T recipe) {

    }

    public interface RecipeFactory<T extends BrickFurnaceCookingRecipe> {
        T create(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime, boolean needsBellows);
    }
}
