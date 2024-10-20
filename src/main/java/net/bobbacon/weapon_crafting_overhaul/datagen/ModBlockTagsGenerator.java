package net.bobbacon.weapon_crafting_overhaul.datagen;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsGenerator extends FabricTagProvider.BlockTagProvider {
    public static final TagKey<Block> NEEDS_COPPER_TOOL = TagKey.of(Registries.BLOCK.getKey(), new Identifier(WeaponCraftingOverhaul.MOD_ID,"needs_copper_tool"));
    public ModBlockTagsGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(NEEDS_COPPER_TOOL)
                .add(ModBlocks.TIN_ORE)
                .add(ModBlocks.DEEP_TIN_ORE);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.DEEP_TIN_ORE)
                .add(ModBlocks.TIN_ORE);

    }
}
