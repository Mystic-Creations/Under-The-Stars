package net.mysticcreations.underthestars;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.resources.ResourceLocation;
import net.mysticcreations.underthestars.init.UtsBlocks;
import net.mysticcreations.underthestars.init.UtsItems;
import net.mysticcreations.underthestars.init.UtsTabs;
import net.mysticcreations.underthestars.mechanics.HealingCampfire;

public final class UnderTheStars {
    public static final String MODID = "underthestars";

    public static void init() {
        UtsBlocks.register();
        UtsItems.register();
        UtsTabs.register();
        registerEvents();
    }

    public static void registerEvents() {
        TickEvent.PLAYER_POST.register(HealingCampfire::onPlayerTick);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
