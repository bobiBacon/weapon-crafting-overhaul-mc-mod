package net.bobbacon.races.world.feature;

import com.mojang.serialization.Codec;
import net.bobbacon.races.block.ModBlocks;
import net.bobbacon.races.block.StackableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class RocksFeature extends Feature<DefaultFeatureConfig> {
    public RocksFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        // the origin is the place where the game starts trying to place the feature
        BlockPos origin = context.getOrigin();
        // we won't use the random here, but we could if we wanted to
        Random random = context.getRandom();

        // don't worry about where these come from-- we'll implement these methods soon

        BlockState blockState = ModBlocks.ROCKS.getDefaultState().with(StackableBlock.STACK,random.nextInt(4)+1);
        // ensure the ID is okay

        // find the surface of the world
        BlockPos testPos = new BlockPos(origin.getX(),world.getHeight(),origin.getZ());

        for (int y= world.getHeight(); y > 0; y--) {
            testPos = testPos.down();
            // the tag name is dirt, but includes grass, mud, podzol, etc.
            if (world.getBlockState(testPos).isIn(BlockTags.DIRT) || world.getBlockState(testPos).isIn(BlockTags.SAND)|| world.getBlockState(testPos).isIn(BlockTags.STONE_ORE_REPLACEABLES)||world.getBlockState(testPos).isOf(Blocks.GRAVEL)) {
                if (world.getBlockState(testPos.up()).isOf(Blocks.AIR)) {
                    world.setBlockState(testPos.up(),blockState,0x10);

                    return true;
                }
            }
        }
        // the game couldn't find a place to put the pillar
        return false;
    }
}
