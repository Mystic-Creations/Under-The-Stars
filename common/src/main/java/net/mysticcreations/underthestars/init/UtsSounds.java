package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.mysticcreations.underthestars.UnderTheStars;

public class UtsSounds {
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(UnderTheStars.MODID, Registries.SOUND_EVENT);

    public static final RegistrySupplier<SoundEvent> CHOCOLATE_UNWRAPPING = sound("chocolate_unwrapping");

    public static void register() {
        REGISTRY.register();
    }

    private static RegistrySupplier<SoundEvent> sound(String name) {
        return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(UnderTheStars.asResource(name)));
    }
}
