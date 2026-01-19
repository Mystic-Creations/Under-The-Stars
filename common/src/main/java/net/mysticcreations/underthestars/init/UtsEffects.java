package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.effect.SugarRush;

public class UtsEffects {
    public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(UnderTheStars.MODID, Registries.MOB_EFFECT);

    public static final RegistrySupplier<MobEffect> SUGAR_RUSH = REGISTRY.register("sugar_rush", SugarRush::new);

    public static void register() {
        REGISTRY.register();
    }
}
