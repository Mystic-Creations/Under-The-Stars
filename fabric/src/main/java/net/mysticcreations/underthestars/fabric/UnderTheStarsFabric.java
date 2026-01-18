package net.mysticcreations.underthestars.fabric;

import net.fabricmc.api.ModInitializer;

import net.mysticcreations.underthestars.UnderTheStars;

public final class UnderTheStarsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        UnderTheStars.init();
    }
}
