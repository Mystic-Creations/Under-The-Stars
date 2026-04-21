package net.lumynity.underthestars.forge;

import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.lumynity.underthestars.UnderTheStars;
import net.lumynity.underthestars.content.world.biome.OldGrowthOakForest;
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
        event.enqueueWork(() -> Regions.register(new OldGrowthOakForest(UnderTheStars.asResource("overworld_region"), 1)));
    }
}
