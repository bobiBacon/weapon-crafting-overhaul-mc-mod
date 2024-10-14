package net.bobbacon.races.block;

import net.bobbacon.races.RacesModForMyServer;
import net.bobbacon.races.registry.RegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {
    static RegistryHelper<Block> registryHelper= new RegistryHelper<>(Registries.BLOCK);
    public static final Block MUD_OVEN = registryHelper.register("brick_furnace", new BrickFurnace(AbstractBlock.Settings.create().ticksRandomly().strength(1.5f).sounds(BlockSoundGroup.GRAVEL ).luminance(Blocks.createLightLevelFromLitBlockState(5))));
    public static final Block ROCKS= registryHelper.register("rocks",
            new StackableBlock(FabricBlockSettings.create().breakInstantly().nonOpaque().notSolid().noCollision(),4,Block.createCuboidShape(1,0,1,15,2,15)));
    public static final Block TIN_ORE= registryHelper.register("tin_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE)));
    public static final Block DEEP_TIN_ORE= registryHelper.register("deepslate_tin_ore",
            new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.DEEPSLATE_IRON_ORE)));
    public static void init(){

        RacesModForMyServer.LOGGER.info("blocks");
    }
}
