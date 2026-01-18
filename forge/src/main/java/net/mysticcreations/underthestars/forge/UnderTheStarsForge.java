package net.mysticcreations.underthestars.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.mysticcreations.underthestars.UnderTheStars;

@Mod(UnderTheStars.MODID)
public final class UnderTheStarsForge {
    public UnderTheStarsForge() {
        EventBuses.registerModEventBus(UnderTheStars.MODID, FMLJavaModLoadingContext.get().getModEventBus());
        UnderTheStars.init();
    }
}
