package net.bobbacon.weapon_crafting_overhaul.recipe;

import net.bobbacon.weapon_crafting_overhaul.item.ModItems;
import net.bobbacon.weapon_crafting_overhaul.recipe.types.ModRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.util.Identifier;

public class BrickFurnaceCookingRecipe extends AbstractCookingRecipe {
    protected final boolean needsBellows;
    public BrickFurnaceCookingRecipe(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime, boolean needsBellows) {
        super(ModRecipeTypes.BRICK_FURNACE_COOKING_TYPE, id, group, category, input, output, experience, cookTime);
        this.needsBellows = needsBellows;
    }

    public boolean NeedsBellows() {
        return needsBellows;
    }

    @Override
    public ItemStack createIcon() {
        return ModItems.BRICK_FURNACE.getDefaultStack();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.BRICK_FURNACE_COOKING_SERIALIZER;
    }



}
