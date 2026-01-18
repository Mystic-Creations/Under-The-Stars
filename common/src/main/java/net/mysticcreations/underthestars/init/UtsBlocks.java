package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.block.WhiteSleepingBag;

public class UtsBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(UnderTheStars.MODID, Registries.BLOCK);

    public static final RegistrySupplier<BedBlock> WHITE_SLEEPING_BAG = REGISTRY.register("white_sleeping_bag", WhiteSleepingBag::new);

    public static void register() {
        REGISTRY.register();
    }
}