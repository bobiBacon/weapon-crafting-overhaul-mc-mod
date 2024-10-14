package net.bobbacon.races.datagen;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.block.ModBlocks;
import net.bobbacon.races.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModRecipeGenerator extends FabricRecipeProvider {
    Item[] craftsToRemove = {Items.STONE_AXE,Items.STONE_HOE,Items.STONE_SHOVEL,Items.STONE_PICKAXE,Items.STONE_SWORD,Items.WOODEN_PICKAXE,Items.WOODEN_AXE};

    public ModRecipeGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        removeCrafts(exporter, craftsToRemove);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.MUD_OVEN)
                .pattern(" D ")
                .pattern("D D")
                .pattern("DCD")
                .input('D', Items.DIRT).input('C', ItemTags.STONE_CRAFTING_MATERIALS)
                .criterion(FabricRecipeProvider.hasItem(Items.DIRT),
                        FabricRecipeProvider.conditionsFromItem(Items.DIRT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STONE_BOWL)
                .pattern("R R")
                .pattern(" R ")
                .input('R',ModItems.ROCK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.ROCK),
                        FabricRecipeProvider.conditionsFromItem(ModItems.ROCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS,Blocks.COBBLESTONE)
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .input('R',ModItems.ROCK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.ROCK),
                        FabricRecipeProvider.conditionsFromItem(ModItems.ROCK))
                .offerTo(exporter);
//        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.PICKAXE_MOLD)
//                .pattern(" W ")
//                .pattern("WSW")
//                .pattern(" W ")
//                .input('W',ItemTags.PLANKS).input('S',ItemTags.SAND)
//                .criterion("has_item_of_tags_sand",FabricRecipeProvider.conditionsFromTag(ItemTags.SAND))
//                .offerTo(exporter);
//        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.AXE_MOLD)
//                .pattern(" W ")
//                .pattern("WSW")
//                .pattern(" W ")
//                .input('W',ItemTags.PLANKS).input('S',ItemTags.SAND)
//                .criterion("has_item_of_tags_sand",FabricRecipeProvider.conditionsFromTag(ItemTags.SAND))
//                .offerTo(exporter);
//        ArrayList<Item> molds= new ArrayList<>();
        Item[] molds= {ModItems.AXE_MOLD,ModItems.PICKAXE_MOLD,ModItems.WAR_AXE_MOLD};
        for (Item mold : molds){
            ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,mold)
                    .pattern(" W ")
                    .pattern("WSW")
                    .pattern(" W ")
                    .input('W',ItemTags.PLANKS).input('S',ItemTags.SAND)
                    .criterion("has_item_of_tag_bowl",FabricRecipeProvider.conditionsFromTag(ModItemTagsGenerator.BOWL))
                    .offerTo(exporter);
        }
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.COPPER_PICKAXE_MOLD)
                .pattern("BBB")
                .pattern("   ")
                .pattern(" M ")
                .input('B',ModItems.COPPER_STONE_BOWL).input('M',ModItems.PICKAXE_MOLD)
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_STONE_BOWL),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_STONE_BOWL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.COPPER_AXE_MOLD)
                .pattern("BB")
                .pattern(" B")
                .pattern("M ")
                .input('B',ModItems.COPPER_STONE_BOWL).input('M',ModItems.AXE_MOLD)
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_STONE_BOWL),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_STONE_BOWL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,ModItems.COPPER_PICKAXE)
                .pattern("M")
                .pattern("S")
                .pattern("S")
                .input('M',ModItems.COPPER_PICKAXE_MOLD).input('S',Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_PICKAXE_MOLD),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_PICKAXE_MOLD))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS,ModItems.COPPER_AXE)
                .pattern("M")
                .pattern("S")
                .pattern("S")
                .input('M',ModItems.COPPER_AXE_MOLD).input('S',Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(ModItems.COPPER_AXE_MOLD),
                        FabricRecipeProvider.conditionsFromItem(ModItems.COPPER_AXE_MOLD))

                .offerTo(exporter);

        bowlToIngot(ModItems.COPPER_STONE_BOWL,Items.COPPER_INGOT,exporter);
        bowlToIngot(ModItems.TIN_STONE_BOWL,ModItems.TIN_INGOT,exporter);
        bowlToIngot(ModItems.BRONZE_STONE_BOWL,ModItems.BRONZE_INGOT,exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ModItems.BRONZE_INGOT,6)
                .input(ModItems.COPPER_STONE_BOWL,5).input(ModItems.TIN_STONE_BOWL)
                .criterion(FabricRecipeProvider.hasItem(ModItems.TIN_STONE_BOWL),FabricRecipeProvider.conditionsFromItem(ModItems.TIN_STONE_BOWL))
                .criterion(FabricRecipeProvider.hasItem(ModItems.TIN_INGOT),FabricRecipeProvider.conditionsFromItem(ModItems.TIN_INGOT))
                .offerTo(exporter);
    }
    private static void bowlToIngot(ItemConvertible bowl,ItemConvertible ingot,Consumer<RecipeJsonProvider> exporter){
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC,ingot)
                .input(bowl)
                .criterion(FabricRecipeProvider.hasItem(bowl),FabricRecipeProvider.conditionsFromItem(bowl))
                .offerTo(exporter,Registries.ITEM.getId(ingot.asItem()).getPath()+"_from_bowl");
    }
    public void removeCrafts(Consumer<RecipeJsonProvider> exporter, Item... medievalWeaponsCraftsToRemove) {
        for (Item item: medievalWeaponsCraftsToRemove){
            ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.AIR).input(Items.BARRIER)
                    .criterion(FabricRecipeProvider.hasItem(Items.AIR), FabricRecipeProvider.conditionsFromItem(Items.AIR))
                    .offerTo(exporter, Registries.ITEM.getId(item));
            RacesModForMyServer.LOGGER.warn(String.valueOf(Registries.ITEM.getId(item)));
        }
    }

    @Override
    protected Identifier getRecipeIdentifier(Identifier identifier) {
        String path= identifier.getPath();

        for (Item item: craftsToRemove){
            if (Registries.ITEM.getId(item).getPath().equals(path)){
                return new Identifier("minecraft", identifier.getPath());
            }
        }
        return super.getRecipeIdentifier(identifier);
    }
}
