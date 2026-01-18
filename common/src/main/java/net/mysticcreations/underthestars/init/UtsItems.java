package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BedItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.item.*;

public class UtsItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(UnderTheStars.MODID, Registries.ITEM);

    public static final RegistrySupplier<Item> WHITE_SLEEPING_BAG = bedBlock(UtsBlocks.WHITE_SLEEPING_BAG);

    public static final RegistrySupplier<Item> KELP_GELATIN = REGISTRY.register("kelp_gelatin", KelpGelatin::new);
    public static final RegistrySupplier<Item> MARSHMALLOW = REGISTRY.register("marshmallow", Marshmallow::new);
    public static final RegistrySupplier<Item> COOKED_MARSHMALLOW = REGISTRY.register("cooked_marshmallow", CookedMarshmallow::new);
    public static final RegistrySupplier<Item> MARSHMALLOW_ON_A_STICK = REGISTRY.register("marshmallow_on_a_stick", MarshmallowOnAStick::new);
    public static final RegistrySupplier<Item> COOKED_MARSHMALLOW_ON_A_STICK = REGISTRY.register("cooked_marshmallow_on_a_stick", CookedMarshmallowOnAStick::new);
    public static final RegistrySupplier<Item> CHOCOLATE_BAR = REGISTRY.register("chocolate_bar", ChocolateBar::new);
    public static final RegistrySupplier<Item> OPEN_CHOCOLATE_BAR = REGISTRY.register("open_chocolate_bar", ChocolateBar::new);
    public static final RegistrySupplier<Item> WRAPPED_CHOCOLATE_BAR = REGISTRY.register("wrapped_chocolate_bar", WrappedChocolateBar::new);
    public static final RegistrySupplier<Item> CHOCOLATE_PIECE = REGISTRY.register("chocolate_piece", ChocolatePiece::new);

    public static void register() {
        REGISTRY.register();
    }

    private static RegistrySupplier<Item> bedBlock(RegistrySupplier<BedBlock> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BedItem(block.get(), new Item.Properties()));
    }
    private static RegistrySupplier<Item> block(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static RegistrySupplier<Item> doubleBlock(RegistrySupplier<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new DoubleHighBlockItem(block.get(), new Item.Properties()));
    }
}
