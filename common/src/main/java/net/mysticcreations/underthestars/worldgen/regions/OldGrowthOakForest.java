package net.mysticcreations.underthestars.worldgen.regions;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.mysticcreations.underthestars.init.UtsBiomes;
import terrablender.api.ParameterUtils.*;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class OldGrowthOakForest extends Region {
    public OldGrowthOakForest(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        //Good biome spawning but makes giant oceans labeled as this biome generating next to land with the biome
        //Needs to be fixed
        new ParameterPointListBuilder()
            .temperature(Temperature.span(
                Temperature.NEUTRAL,
                Temperature.WARM
            ))
            .humidity(Humidity.span(
                Humidity.DRY,
                Humidity.NEUTRAL
            ))
            .continentalness(Continentalness.span(
                Continentalness.MID_INLAND,
                Continentalness.FAR_INLAND
            ))
            .erosion(Erosion.EROSION_2)
            .depth(Depth.SURFACE)
            .weirdness(Weirdness.span(
                Weirdness.MID_SLICE_NORMAL_ASCENDING,
                Weirdness.MID_SLICE_NORMAL_DESCENDING
            ))
            .build().forEach(point -> this.addBiome(mapper, point, UtsBiomes.OLD_GROWTH_OAK_FOREST));
    }
}
