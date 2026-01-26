package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.item.*;

public class UtsItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(UnderTheStars.MODID, Registries.ITEM);

    //Blocks
    public static final RegistrySupplier<Item> WHITE_SLEEPING_BAG = bedBlock(UtsBlocks.WHITE_SLEEPING_BAG);
    public static final RegistrySupplier<Item> LIGHT_GRAY_SLEEPING_BAG = bedBlock(UtsBlocks.LIGHT_GRAY_SLEEPING_BAG);
    public static final RegistrySupplier<Item> GRAY_SLEEPING_BAG = bedBlock(UtsBlocks.GRAY_SLEEPING_BAG);
    public static final RegistrySupplier<Item> BLACK_SLEEPING_BAG = bedBlock(UtsBlocks.BLACK_SLEEPING_BAG);
    public static final RegistrySupplier<Item> BROWN_SLEEPING_BAG = bedBlock(UtsBlocks.BROWN_SLEEPING_BAG);
    public static final RegistrySupplier<Item> RED_SLEEPING_BAG = bedBlock(UtsBlocks.RED_SLEEPING_BAG);
    public static final RegistrySupplier<Item> ORANGE_SLEEPING_BAG = bedBlock(UtsBlocks.ORANGE_SLEEPING_BAG);
    public static final RegistrySupplier<Item> YELLOW_SLEEPING_BAG = bedBlock(UtsBlocks.YELLOW_SLEEPING_BAG);
    public static final RegistrySupplier<Item> LIME_SLEEPING_BAG = bedBlock(UtsBlocks.LIME_SLEEPING_BAG);
    public static final RegistrySupplier<Item> GREEN_SLEEPING_BAG = bedBlock(UtsBlocks.GREEN_SLEEPING_BAG);
    public static final RegistrySupplier<Item> CYAN_SLEEPING_BAG = bedBlock(UtsBlocks.CYAN_SLEEPING_BAG);
    public static final RegistrySupplier<Item> LIGHT_BLUE_SLEEPING_BAG = bedBlock(UtsBlocks.LIGHT_BLUE_SLEEPING_BAG);
    public static final RegistrySupplier<Item> BLUE_SLEEPING_BAG = bedBlock(UtsBlocks.BLUE_SLEEPING_BAG);
    public static final RegistrySupplier<Item> PURPLE_SLEEPING_BAG = bedBlock(UtsBlocks.PURPLE_SLEEPING_BAG);
    public static final RegistrySupplier<Item> MAGENTA_SLEEPING_BAG = bedBlock(UtsBlocks.MAGENTA_SLEEPING_BAG);
    public static final RegistrySupplier<Item> PINK_SLEEPING_BAG = bedBlock(UtsBlocks.PINK_SLEEPING_BAG);
    //BlockItems
    public static final RegistrySupplier<Item> SMORE = REGISTRY.register("smore", () -> new SmoreBlockItem(UtsBlocks.SMORE.get()));

    //Items
    public static final RegistrySupplier<Item> KELP_GELATIN = REGISTRY.register("kelp_gelatin", KelpGelatin::new);
    public static final RegistrySupplier<Item> MARSHMALLOW = REGISTRY.register("marshmallow", Marshmallow::new);
    public static final RegistrySupplier<Item> COOKED_MARSHMALLOW = REGISTRY.register("cooked_marshmallow", CookedMarshmallow::new);
    public static final RegistrySupplier<Item> BURNED_MARSHMALLOW = REGISTRY.register("burned_marshmallow", BurnedMarshmallow::new);
    public static final RegistrySupplier<Item> MARSHMALLOW_ON_A_STICK = REGISTRY.register("marshmallow_on_a_stick", MarshmallowOnAStick::new);
    public static final RegistrySupplier<Item> COOKED_MARSHMALLOW_ON_A_STICK = REGISTRY.register("cooked_marshmallow_on_a_stick", CookedMarshmallowOnAStick::new);
    public static final RegistrySupplier<Item> BURNED_MARSHMALLOW_ON_A_STICK = REGISTRY.register("burned_marshmallow_on_a_stick", BurnedMarshmallowOnAStick::new);
    public static final RegistrySupplier<Item> CHOCOLATE_BAR = REGISTRY.register("chocolate_bar", ChocolateBar::new);
    public static final RegistrySupplier<Item> OPEN_CHOCOLATE_BAR = REGISTRY.register("open_chocolate_bar", OpenChocolateBar::new);
    public static final RegistrySupplier<Item> WRAPPED_CHOCOLATE_BAR = REGISTRY.register("wrapped_chocolate_bar", WrappedChocolateBar::new);
    public static final RegistrySupplier<Item> CHOCOLATE_PIECE = REGISTRY.register("chocolate_piece", ChocolatePiece::new);
    public static final RegistrySupplier<Item> BISCUIT = REGISTRY.register("biscuit", Biscuit::new);

    //Other
    public static final RegistrySupplier<Item> STARGAZING_ADV = REGISTRY.register("stargazing_icon", () -> new Item(new Item.Properties().stacksTo(1)));

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
