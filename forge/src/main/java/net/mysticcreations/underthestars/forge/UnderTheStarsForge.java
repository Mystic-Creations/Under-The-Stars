package net.mysticcreations.underthestars.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.mysticcreations.underthestars.UnderTheStars;

@Mod(UnderTheStars.MOD_ID)
public final class UnderTheStarsForge {
    public UnderTheStarsForge() {
        // Submit our event bus to let Architectury API register our content on the right time.
        EventBuses.registerModEventBus(UnderTheStars.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());

        // Run our common setup.
        UnderTheStars.init();
    }
}
