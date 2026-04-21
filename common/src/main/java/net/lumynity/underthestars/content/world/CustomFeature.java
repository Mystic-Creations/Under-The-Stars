package net.lumynity.underthestars.content.world;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;

public abstract class CustomFeature<FC extends FeatureConfiguration> extends Feature<FC> {

    public CustomFeature(Codec<FC> codec) {
        super(codec);
    }
}

