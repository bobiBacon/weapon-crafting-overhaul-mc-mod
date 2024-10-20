package net.bobbacon.weapon_crafting_overhaul.world.feature;

import net.bobbacon.weapon_crafting_overhaul.WeaponCraftingOverhaul;
import net.bobbacon.weapon_crafting_overhaul.block.ModBlocks;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.*;

import java.util.List;

public class ModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ROCKS_KEY = registerKey("rocks");
    public static final RegistryKey<ConfiguredFeature<?, ?>> TIN_ORE_KEY = registerKey("tin_ore");
//    public static final ConfiguredFeature<DefaultFeatureConfig, RocksFeature> ROCKS_FEATURE_CONFIGURED = new ConfiguredFeature<>(
//            ModFeatures.ROCKS_FEATURE,
//            new DefaultFeatureConfig()
//    );

    public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> overworldTinOres =
                List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.TIN_ORE.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEP_TIN_ORE.getDefaultState()));

        register(context, TIN_ORE_KEY, Feature.ORE, new OreFeatureConfig(overworldTinOres, 4));
        register(context,ROCKS_KEY,ModFeatures.ROCKS_FEATURE,new DefaultFeatureConfig());
    }
    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(WeaponCraftingOverhaul.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
