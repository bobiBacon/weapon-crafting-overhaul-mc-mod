package net.bobbacon.weapon_crafting_overhaul.datagen;

import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.bobbacon.weapon_crafting_overhaul.block.StackableBlock;
import net.bobbacon.weapon_crafting_overhaul.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.LootFunctionConsumingBuilder;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.block.Block;

import java.util.List;

public class ModBlockLootTableGenerator extends FabricBlockLootTableProvider {
    public ModBlockLootTableGenerator(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
//        candleDrops(ModBlocks.ROCKS);
//        addDrop(ModBlocks.ROCKS, new Function<Block, LootTable.Builder>() {
//            @Override
//            public LootTable.Builder apply(Block block) {
//                return
//            }
//        });
//        addDrop(Blocks.COPPER_ORE,  LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with().conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(ModItems.ROCK)))));

        addDrop(ModBlocks.ROCKS,LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).with((LootPoolEntry.Builder)this.applyExplosionDecay(ModBlocks.ROCKS, (LootFunctionConsumingBuilder)ItemEntry.builder(ModBlocks.ROCKS).apply(List.of(Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4)), stack -> SetCountLootFunction.builder(ConstantLootNumberProvider.create(stack.intValue())).conditionally(BlockStatePropertyLootCondition.builder(ModBlocks.ROCKS).properties(StatePredicate.Builder.create().exactMatch(StackableBlock.STACK, stack.intValue()))) )))));
//        dropsWithProperty(ModBlocks.ROCKS,StackableBlock.STACK, )
//        addDrop(Blocks.COPPER_ORE,LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1)).with((LootPoolEntry.Builder)this.applyExplosionDecay(Items.COPPER_ORE,(LootFunctionConsumingBuilder)ItemEntry.builder(Items.COPPER_ORE) )).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(ModItems.ROCK)))));

//        addDrop(Blocks.COPPER_ORE,LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().items(ModItems.ROCK))).with(ItemEntry.builder(Items.RAW_COPPER))));
        addDrop(ModBlocks.TIN_ORE, likeIronOre(ModBlocks.TIN_ORE, ModItems.RAW_TIN));
        addDrop(ModBlocks.DEEP_TIN_ORE, likeIronOre(ModBlocks.DEEP_TIN_ORE, ModItems.RAW_TIN));

        addDrop(ModBlocks.MUD_OVEN);
    }

    public LootTable.Builder likeIronOre(Block dropWithSilkTouch, Item drop) {
        return BlockLootTableGenerator.dropsWithSilkTouch(dropWithSilkTouch, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithSilkTouch,
                ((LeafEntry.Builder)
                        ItemEntry.builder(drop)
                                .apply(SetCountLootFunction
                                        .builder(ConstantLootNumberProvider.create(1))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
    public LootTable.Builder likeCopperOre(Block dropWithSilkTouch, Item drop) {
        return BlockLootTableGenerator.dropsWithSilkTouch(dropWithSilkTouch, (LootPoolEntry.Builder)this.applyExplosionDecay(dropWithSilkTouch,
                ((LeafEntry.Builder)
                        ItemEntry.builder(drop)
                                .apply(SetCountLootFunction
                                        .builder(UniformLootNumberProvider
                                                .create(2.0f, 5.0f))))
                        .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }

}
