package net.mysticcreations.underthestars.fabric.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.mysticcreations.underthestars.UnderTheStars;

public class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register((context) -> {
            context.addModels(UnderTheStars.asModelResource("in_hand/marshmallow_on_a_stick", "inventory"));
            context.addModels(UnderTheStars.asModelResource("in_hand/cooked_marshmallow_on_a_stick", "inventory"));
            context.addModels(UnderTheStars.asModelResource("in_hand/smore", "inventory"));
        });
    }
}