package net.mysticcreations.underthestars;

import dev.architectury.event.events.common.InteractionEvent;
import dev.architectury.event.events.common.PlayerEvent;
import dev.architectury.event.events.common.TickEvent;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.mysticcreations.underthestars.init.*;
import net.mysticcreations.underthestars.mechanics.*;
import net.mysticcreations.underthestars.mechanics.logic.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ConcurrentLinkedQueue;

public final class UnderTheStars {
    public static final Logger LOGGER = LogManager.getLogger(UnderTheStars.class);
    private static final ConcurrentLinkedQueue<WorkItem> workQueue = new ConcurrentLinkedQueue<>();
    public static final String MODID = "underthestars";

    public static void init() {
        UtsBlocks.register();
        UtsItems.register();
        UtsTabs.register();
        UtsEffects.register();
        UtsSounds.register();
        registerEvents();
    }
    public static void registerEvents() {
        TickEvent.PLAYER_POST.register(HealingCampfire::onPlayerTick);
        TickEvent.PLAYER_POST.register(StargazingAdvancement::onPlayerTick);
        PlayerEvent.PLAYER_ADVANCEMENT.register(EasterEggAdvancements::onAdvancement);
        InteractionEvent.RIGHT_CLICK_BLOCK.register(SugarRushInsomnia::onSleepAttempt);
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }
    public static ModelResourceLocation asModelResource(String path, String variant) {
        return new ModelResourceLocation(MODID, path, variant);
    }
    public static boolean hasAdvancement(ServerPlayer player, String AdvancementID) {
        return player.getAdvancements().getOrStartProgress(
            player.server.getAdvancements().getAdvancement(asResource(AdvancementID))
        ).isDone();
    }
    public static void grantAdvancement(ServerPlayer player, String AdvancementID) {
        Advancement advancement = player.server.getAdvancements().getAdvancement(asResource(AdvancementID));
        AdvancementProgress progress = player.getAdvancements().getOrStartProgress(advancement);
        if (!progress.isDone()) {
            for (String criteria : progress.getRemainingCriteria())
                player.getAdvancements().award(advancement, criteria);
        }
    }

    private static class WorkItem {
        final Runnable task;
        int ticksRemaining;

        WorkItem(Runnable task, int delay) {
            this.task = task;
            this.ticksRemaining = delay;
        }
    }
    public static void wait(int tickDelay, Runnable action) {
        workQueue.add(new WorkItem(action, tickDelay));
    }
}
