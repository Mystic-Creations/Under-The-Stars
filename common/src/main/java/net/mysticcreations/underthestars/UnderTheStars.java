package net.mysticcreations.underthestars;

import net.minecraft.resources.ResourceLocation;
import net.mysticcreations.underthestars.init.UtsBlocks;
import net.mysticcreations.underthestars.init.UtsItems;
import net.mysticcreations.underthestars.init.UtsTabs;

public final class UnderTheStars {
    public static final String MODID = "underthestars";

    public static void init() {
        UtsBlocks.register();
        UtsItems.register();
        UtsTabs.register();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
