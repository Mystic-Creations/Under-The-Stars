package net.mysticcreations.underthestars.fabric;

import net.fabricmc.api.ModInitializer;

import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.worldgen.regions.OldGrowthOakForest;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public final class UnderTheStarsFabric implements ModInitializer, TerraBlenderApi {
    @Override
    public void onInitialize() {
        UnderTheStars.init();
    }

    @Override
    public void onTerraBlenderInitialized() {
        Regions.register(new OldGrowthOakForest(UnderTheStars.asResource("overworld_region"), 1));
    }
}
