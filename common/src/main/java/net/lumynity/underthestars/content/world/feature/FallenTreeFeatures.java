package net.lumynity.underthestars.content.world.feature;

import com.google.common.collect.ImmutableList;
import net.lumynity.underthestars.content.world.feature.config.FallenTreeConfig;
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
import net.lumynity.underthestars.content.world.CustomFeature;

import java.util.List;

public class FallenTreeFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_OAK_TREE = FeatureUtils.createKey("fallen_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_JUNGLE_TREE = FeatureUtils.createKey("fallen_jungle_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SPRUCE_TREE = FeatureUtils.createKey("fallen_spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_TREE = FeatureUtils.createKey("fallen_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_SUPER_BIRCH_TREE = FeatureUtils.createKey("fallen_super_birch_tree");

    private static FallenTreeConfig.FallenTreeConfigBuilder createFallenOak() {
        return createFallenTrees(Blocks.OAK_LOG, 4, 7).stumpDecorators(ImmutableList.of(TrunkVineDecorator.INSTANCE));
    }

    private static FallenTreeConfig.FallenTreeConfigBuilder createFallenBirch(final int maxHeight) {
        return createFallenTrees(Blocks.BIRCH_LOG, 5, maxHeight);
    }

    private static FallenTreeConfig.FallenTreeConfigBuilder createFallenJungle() {
        return createFallenTrees(Blocks.JUNGLE_LOG, 4, 11).stumpDecorators(ImmutableList.of(TrunkVineDecorator.INSTANCE));
    }

    private static FallenTreeConfig.FallenTreeConfigBuilder createFallenSpruce() {
        return createFallenTrees(Blocks.SPRUCE_LOG, 6, 10);
    }

    private static FallenTreeConfig.FallenTreeConfigBuilder createFallenTrees(final Block logBlock, final int minLength, final int maxLength) {
        return (new FallenTreeConfig.FallenTreeConfigBuilder(BlockStateProvider.simple(logBlock), UniformInt.of(minLength, maxLength))).logDecorators(ImmutableList.of(new AttachedToLogsDecorator(0.1F, new WeightedStateProvider(WeightedList.builder().add(Blocks.RED_MUSHROOM.defaultBlockState(), 2).add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)), List.of(Direction.UP))));
    }

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        FeatureUtils.register(context, FALLEN_OAK_TREE, CustomFeature.FALLEN_TREE, createFallenOak().build());
        FeatureUtils.register(context, FALLEN_BIRCH_TREE, CustomFeature.FALLEN_TREE, createFallenBirch(8).build());
        FeatureUtils.register(context, FALLEN_SUPER_BIRCH_TREE, CustomFeature.FALLEN_TREE, createFallenBirch(15).build());
        FeatureUtils.register(context, FALLEN_JUNGLE_TREE, CustomFeature.FALLEN_TREE, createFallenJungle().build());
        FeatureUtils.register(context, FALLEN_SPRUCE_TREE, CustomFeature.FALLEN_TREE, createFallenSpruce().build());
    }
}
