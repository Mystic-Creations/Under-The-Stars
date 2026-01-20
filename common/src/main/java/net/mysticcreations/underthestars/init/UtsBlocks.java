package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.block.SleepingBag;
import net.mysticcreations.underthestars.block.Smore;

public class UtsBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(UnderTheStars.MODID, Registries.BLOCK);

    public static final RegistrySupplier<BedBlock> WHITE_SLEEPING_BAG = REGISTRY.register("white_sleeping_bag", () -> new SleepingBag(DyeColor.WHITE));
    public static final RegistrySupplier<BedBlock> LIGHT_GRAY_SLEEPING_BAG = REGISTRY.register("light_gray_sleeping_bag", () -> new SleepingBag(DyeColor.LIGHT_GRAY));
    public static final RegistrySupplier<BedBlock> GRAY_SLEEPING_BAG = REGISTRY.register("gray_sleeping_bag", () -> new SleepingBag(DyeColor.GRAY));
    public static final RegistrySupplier<BedBlock> BLACK_SLEEPING_BAG = REGISTRY.register("black_sleeping_bag", () -> new SleepingBag(DyeColor.BLACK));
    public static final RegistrySupplier<BedBlock> BROWN_SLEEPING_BAG = REGISTRY.register("brown_sleeping_bag", () -> new SleepingBag(DyeColor.BROWN));
    public static final RegistrySupplier<BedBlock> RED_SLEEPING_BAG = REGISTRY.register("red_sleeping_bag", () -> new SleepingBag(DyeColor.RED));
    public static final RegistrySupplier<BedBlock> ORANGE_SLEEPING_BAG = REGISTRY.register("orange_sleeping_bag", () -> new SleepingBag(DyeColor.ORANGE));
    public static final RegistrySupplier<BedBlock> YELLOW_SLEEPING_BAG = REGISTRY.register("yellow_sleeping_bag", () -> new SleepingBag(DyeColor.YELLOW));
    public static final RegistrySupplier<BedBlock> LIME_SLEEPING_BAG = REGISTRY.register("lime_sleeping_bag", () -> new SleepingBag(DyeColor.LIME));
    public static final RegistrySupplier<BedBlock> GREEN_SLEEPING_BAG = REGISTRY.register("green_sleeping_bag", () -> new SleepingBag(DyeColor.GREEN));
    public static final RegistrySupplier<BedBlock> CYAN_SLEEPING_BAG = REGISTRY.register("cyan_sleeping_bag", () -> new SleepingBag(DyeColor.CYAN));
    public static final RegistrySupplier<BedBlock> LIGHT_BLUE_SLEEPING_BAG = REGISTRY.register("light_blue_sleeping_bag", () -> new SleepingBag(DyeColor.LIGHT_BLUE));
    public static final RegistrySupplier<BedBlock> BLUE_SLEEPING_BAG = REGISTRY.register("blue_sleeping_bag", () -> new SleepingBag(DyeColor.BLUE));
    public static final RegistrySupplier<BedBlock> PURPLE_SLEEPING_BAG = REGISTRY.register("purple_sleeping_bag", () -> new SleepingBag(DyeColor.PURPLE));
    public static final RegistrySupplier<BedBlock> MAGENTA_SLEEPING_BAG = REGISTRY.register("magenta_sleeping_bag", () -> new SleepingBag(DyeColor.MAGENTA));
    public static final RegistrySupplier<BedBlock> PINK_SLEEPING_BAG = REGISTRY.register("pink_sleeping_bag", () -> new SleepingBag(DyeColor.PINK));
    public static final RegistrySupplier<Block> SMORE = REGISTRY.register("smore", Smore::new);

    public static void register() {
        REGISTRY.register();
    }
}