package net.mysticcreations.underthestars.data.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.mysticcreations.underthestars.data.worldgen.config.FallenTreeConfiguration;

public abstract class Feature<FC extends FeatureConfiguration> {
    public static final Feature<FallenTreeConfiguration> FALLEN_TREE;
    private final MapCodec<ConfiguredFeature<FC, net.minecraft.world.level.levelgen.feature.Feature<FC>>> configuredCodec;

    public Feature(final Codec<FC> codec) {
        this.configuredCodec = codec.fieldOf("config").xmap((c) -> new ConfiguredFeature(this, c), ConfiguredFeature::config);
    }
}
