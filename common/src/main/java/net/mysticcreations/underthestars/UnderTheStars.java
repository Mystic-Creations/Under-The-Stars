package net.mysticcreations.underthestars;

import net.minecraft.resources.ResourceLocation;

public final class UnderTheStars {
    public static final String MODID = "underthestars";

    public static void init() {
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
}
