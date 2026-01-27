package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.mysticcreations.underthestars.UnderTheStars;

public class UtsBiomes {
    public static ResourceKey<Biome> OLD_GROWTH_OAK_FOREST = ResourceKey.create(Registries.BIOME, UnderTheStars.asResource("old_growth_oak_forest"));
}
