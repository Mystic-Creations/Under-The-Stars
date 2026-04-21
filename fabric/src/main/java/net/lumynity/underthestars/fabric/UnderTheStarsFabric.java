package net.lumynity.underthestars.fabric;

import net.fabricmc.api.ModInitializer;

import net.lumynity.underthestars.UnderTheStars;
import net.lumynity.underthestars.content.world.biome.OldGrowthOakForest;
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
