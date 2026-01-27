package net.mysticcreations.underthestars.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.worldgen.UTSRegion;
import terrablender.api.Regions;

@Mod(UnderTheStars.MODID)
public final class UnderTheStarsForge {

    public static IEventBus EVENT_BUS;

    public UnderTheStarsForge() {
        EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(UnderTheStars.MODID, EVENT_BUS);
        UnderTheStars.init();

        EVENT_BUS.addListener(this::commonSetup);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            Regions.register(new UTSRegion(new ResourceLocation("under_the_stars:overworld_region"), 1));
        });
    }
}
