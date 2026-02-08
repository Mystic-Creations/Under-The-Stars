package net.mysticcreations.underthestars.data.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.mysticcreations.underthestars.data.worldgen.config.FallenTreeConfig;

public abstract class CustomFeature<FC extends FeatureConfiguration> extends Feature<FC> {

    public CustomFeature(Codec<FC> codec) {
        super(codec);
    }
}

