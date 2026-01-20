package net.mysticcreations.underthestars.forge.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mysticcreations.underthestars.UnderTheStars;

@Mod.EventBusSubscriber(modid = UnderTheStars.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ForgeClient {
    @SubscribeEvent
    public static void registerAdditionalModels(ModelEvent.RegisterAdditional context) {
        context.register(UnderTheStars.asModelResource("in_hand/marshmallow_on_a_stick", "inventory"));
        context.register(UnderTheStars.asModelResource("in_hand/cooked_marshmallow_on_a_stick", "inventory"));
        context.register(UnderTheStars.asModelResource("in_hand/smore", "inventory"));
    }
}
