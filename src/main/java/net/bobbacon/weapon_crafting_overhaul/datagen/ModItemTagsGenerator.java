package net.bobbacon.weapon_crafting_overhaul.datagen;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsGenerator extends FabricTagProvider.ItemTagProvider {
    public static final TagKey<Item> MOLD = TagKey.of(Registries.ITEM.getKey(), new Identifier(WeaponCraftingOverhaul.MOD_ID,"mold"));
    public static final TagKey<Item> BOWL = TagKey.of(Registries.ITEM.getKey(), new Identifier(WeaponCraftingOverhaul.MOD_ID,"bowl"));


    public ModItemTagsGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(MOLD)
                .add(ModItems.AXE_MOLD,ModItems.PICKAXE_MOLD, ModItems.WAR_AXE_MOLD);
        getOrCreateTagBuilder(BOWL)
                .add(ModItems.COPPER_STONE_BOWL,ModItems.TIN_STONE_BOWL,ModItems.BRONZE_STONE_BOWL);
        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.COPPER_PICKAXE);
        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.COPPER_AXE);
    }
}
