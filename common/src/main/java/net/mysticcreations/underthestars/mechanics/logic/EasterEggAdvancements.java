package net.mysticcreations.underthestars.mechanics.logic;

import net.minecraft.advancements.Advancement;
import net.minecraft.server.level.ServerPlayer;
import net.mysticcreations.underthestars.UnderTheStars;

public class EasterEggAdvancements {
    public static String THESHADEVT_UUID = "b58fcc72-74fa-461c-9f84-697ca2b7e409";
    public static String DEV_UUID = "19c3c783-9359-4311-98bf-79a6d361362d"; //Millie

    public static void onAdvancement(ServerPlayer player, Advancement advancement) {
        if (!(player.getStringUUID().equals(THESHADEVT_UUID) || player.getStringUUID().equals(DEV_UUID))) return;

        if (UnderTheStars.hasAdvancement(player, "cooking/burned_marshmallow_or_on_stick")
            && !UnderTheStars.hasAdvancement(player, "easter_egg/theshadevt")) //If she burned but doesn't have the Easter Egg adv yet
            UnderTheStars.wait(2, () -> UnderTheStars.grantAdvancement(player, "easter_egg/theshadevt"));
    }
}
