package net.mysticcreations.underthestars.data.worldgen.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

public class FallenTreeConfig implements FeatureConfiguration {
    public static final Codec<FallenTreeConfig> CODEC = RecordCodecBuilder.create((i) -> i.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter((c) -> c.trunkProvider), IntProvider.codec(0, 16).fieldOf("log_length").forGetter((t) -> t.logLength), TreeDecorator.CODEC.listOf().fieldOf("stump_decorators").forGetter((c) -> c.stumpDecorators), TreeDecorator.CODEC.listOf().fieldOf("log_decorators").forGetter((c) -> c.logDecorators)).apply(i, FallenTreeConfig::new));
    public final BlockStateProvider trunkProvider;
    public final IntProvider logLength;
    public final List<TreeDecorator> stumpDecorators;
    public final List<TreeDecorator> logDecorators;

    protected FallenTreeConfig(final BlockStateProvider trunkProvider, final IntProvider logLength, final List<TreeDecorator> stumpDecorators, final List<TreeDecorator> logDecorators) {
        this.trunkProvider = trunkProvider;
        this.logLength = logLength;
        this.stumpDecorators = stumpDecorators;
        this.logDecorators = logDecorators;
    }

    public static class FallenTreeConfigBuilder {
        private final BlockStateProvider trunkProvider;
        private final IntProvider logLength;
        private List<TreeDecorator> stumpDecorators = new ArrayList();
        private List<TreeDecorator> logDecorators = new ArrayList();

        public FallenTreeConfigBuilder(final BlockStateProvider trunkProvider, final IntProvider logLength) {
            this.trunkProvider = trunkProvider;
            this.logLength = logLength;
        }

        public FallenTreeConfigBuilder stumpDecorators(final List<TreeDecorator> stumpDecorators) {
            this.stumpDecorators = stumpDecorators;
            return this;
        }

        public FallenTreeConfigBuilder logDecorators(final List<TreeDecorator> logDecorators) {
            this.logDecorators = logDecorators;
            return this;
        }

        public FallenTreeConfig build() {
            return new FallenTreeConfig(this.trunkProvider, this.logLength, this.stumpDecorators, this.logDecorators);
        }
    }
}
