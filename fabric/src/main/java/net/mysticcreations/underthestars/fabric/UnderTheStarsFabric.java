package net.mysticcreations.underthestars.fabric;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.worldgen.UTSRegion;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public final class UnderTheStarsFabric implements ModInitializer, TerraBlenderApi {
    @Override
    public void onInitialize() {
        UnderTheStars.init();
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new UTSRegion(new ResourceLocation("under_the_stars:overworld_region"), 1));
    }
}
