package net.mysticcreations.underthestars.data.worldgen.features;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.mysticcreations.underthestars.data.worldgen.Feature;
import net.mysticcreations.underthestars.data.worldgen.config.FallenTreeConfiguration;

import java.util.List;

public class TreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK_TREE = FeatureUtils.createKey("fallen_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_JUNGLE_TREE = FeatureUtils.createKey("fallen_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE = FeatureUtils.createKey("fallen_spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = FeatureUtils.createKey("fallen_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SUPER_BIRCH_TREE = FeatureUtils.createKey("fallen_super_birch_tree");

    private static FallenTreeConfiguration.FallenTreeConfigurationBuilder createFallenOak() {
        return createFallenTrees(Blocks.OAK_LOG, 4, 7).stumpDecorators(ImmutableList.of(TrunkVineDecorator.INSTANCE));
    }
    private static FallenTreeConfiguration.FallenTreeConfigurationBuilder createFallenBirch(final int maxHeight) {
        return createFallenTrees(Blocks.BIRCH_LOG, 5, maxHeight);
    }
    private static FallenTreeConfiguration.FallenTreeConfigurationBuilder createFallenJungle() {
        return createFallenTrees(Blocks.JUNGLE_LOG, 4, 11).stumpDecorators(ImmutableList.of(TrunkVineDecorator.INSTANCE));
    }
    private static FallenTreeConfiguration.FallenTreeConfigurationBuilder createFallenSpruce() {
        return createFallenTrees(Blocks.SPRUCE_LOG, 6, 10);
    }
    private static FallenTreeConfiguration.FallenTreeConfigurationBuilder createFallenTrees(final Block logBlock, final int minLength, final int maxLength) {
        return (new FallenTreeConfiguration.FallenTreeConfigurationBuilder(BlockStateProvider.simple(logBlock), UniformInt.of(minLength, maxLength))).logDecorators(ImmutableList.of(new AttachedToLogsDecorator(0.1F, new WeightedStateProvider(WeightedList.builder().add(Blocks.RED_MUSHROOM.defaultBlockState(), 2).add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)), List.of(Direction.UP))));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, FALLEN_OAK_TREE, Feature.FALLEN_TREE, createFallenOak().build());
        FeatureUtils.register(context, FALLEN_BIRCH_TREE, Feature.FALLEN_TREE, createFallenBirch(8).build());
        FeatureUtils.register(context, FALLEN_SUPER_BIRCH_TREE, Feature.FALLEN_TREE, createFallenBirch(15).build());
        FeatureUtils.register(context, FALLEN_JUNGLE_TREE, Feature.FALLEN_TREE, createFallenJungle().build());
        FeatureUtils.register(context, FALLEN_SPRUCE_TREE, Feature.FALLEN_TREE, createFallenSpruce().build());
    }
}
