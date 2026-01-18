package net.mysticcreations.underthestars.fabric;

import net.fabricmc.api.ModInitializer;

import net.mysticcreations.underthestars.UnderTheStars;

public final class UnderTheStarsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        UnderTheStars.init();
    }
}
