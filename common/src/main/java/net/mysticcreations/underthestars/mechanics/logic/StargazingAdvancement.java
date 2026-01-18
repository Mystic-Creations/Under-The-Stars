package net.mysticcreations.underthestars.mechanics.logic;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.mysticcreations.underthestars.UnderTheStars;

public class StargazingAdvancement {
    public static void onPlayerTick(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        Level level = serverPlayer.level();
        if (!level.isNight()) return;

        float pitch = serverPlayer.getXRot();
        if (pitch > -30.0F || pitch < -90.0F) return;

        if (UnderTheStars.hasAdvancement(serverPlayer, "exploration/stargazing")) return;
        UnderTheStars.grantAdvancement(serverPlayer, "exploration/stargazing");
    }
}
