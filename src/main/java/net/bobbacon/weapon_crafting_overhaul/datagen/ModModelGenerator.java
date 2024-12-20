package net.bobbacon.weapon_crafting_overhaul.datagen;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.bobbacon.weapon_crafting_overhaul.block.StackableBlock;
import net.bobbacon.weapon_crafting_overhaul.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.GeckoLib;

import java.util.Arrays;
import java.util.Optional;

public class ModModelGenerator extends FabricModelProvider {
    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
//        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(ModBlocks.ROCKS)
//                .with(When.create().set(StackableBlock.STACK, 1),
//                        BlockStateVariant.create().put(VariantSettings.MODEL,new Identifier(RacesModForMyServer.MOD_ID,"rocks"))));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.ROCKS).coordinate(BlockStateVariantMap.create(StackableBlock.STACK).register(1, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(new Identifier(WeaponCraftingOverhaul.MOD_ID,"block/rocks")))).register(2, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(ModelIds.getBlockSubModelId(ModBlocks.ROCKS,"2")))).register(3, Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(ModelIds.getBlockSubModelId(ModBlocks.ROCKS,"3")))).register(4,  Arrays.asList(BlockStateModelGenerator.createModelVariantWithRandomHorizontalRotations(ModelIds.getBlockSubModelId(ModBlocks.ROCKS,"4"))))));

        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.MUD_OVEN).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()).coordinate(BlockStateModelGenerator.createBooleanModelMap(Properties.LIT,new Identifier(WeaponCraftingOverhaul.MOD_ID,"block/brick_furnace_lit"),new Identifier(WeaponCraftingOverhaul.MOD_ID,"block/brick_furnace_unlit"))));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.BELLOWS).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));
//        blockStateModelGenerator.blockStateCollector.accept(MultipartBlockStateSupplier.create(ModBlocks.brick_furnace)
//                .with(When.create().set(Properties.HORIZONTAL_FACING, Direction.NORTH).set(Properties.LIT,false),
//                        BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.X)));
//        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ROCKS);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TIN_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEP_TIN_ORE);
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.BASIC_SMITHING_ANVIL,BlockStateVariant.create().put(VariantSettings.MODEL, new Identifier(WeaponCraftingOverhaul.MOD_ID,"block/rustic_smithing_anvil"))).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates()));

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BRICK_FURNACE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRIL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DWARFS_ALLOY_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_TIN, Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.STEEL_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ELVEN_GOLD_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MITHRIL_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROCK,Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_PICKAXE,Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_BOWL, Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_STONE_BOWL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PICKAXE_MOLD,Models.GENERATED);
        itemModelGenerator.register(ModItems.AXE_MOLD,Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_PICKAXE_MOLD,Models.GENERATED);
        itemModelGenerator.register(ModItems.WAR_AXE_MOLD,Models.GENERATED);
        itemModelGenerator.register(ModItems.COPPER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.COPPER_AXE_MOLD,Models.GENERATED);
        itemModelGenerator.register(ModItems.FABRIC,Models.GENERATED);
        itemModelGenerator.register(ModItems.TIN_STONE_BOWL,Models.GENERATED);
        itemModelGenerator.register(ModItems.BRONZE_STONE_BOWL,Models.GENERATED);

//        itemModelGenerator.register(ModItems.BELLOWS,new Model(Optional.of(new Identifier(GeckoLib.MOD_ID,"block/box")), Optional.empty()));

    }
}
