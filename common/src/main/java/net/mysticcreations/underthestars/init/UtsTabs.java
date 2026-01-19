package net.mysticcreations.underthestars.init;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.DeferredSupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.mysticcreations.underthestars.UnderTheStars;

public class UtsTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY =
        DeferredRegister.create(UnderTheStars.MODID, Registries.CREATIVE_MODE_TAB);

    public static final DeferredSupplier<CreativeModeTab> MOD_TAB = REGISTRY.register("underthestars", () ->
        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("item_group.underthestars.under_the_stars"))
            .icon(() -> new ItemStack(UtsItems.MARSHMALLOW.get()))
            .displayItems((parameters, tabData) -> {
                tabData.accept(UtsBlocks.WHITE_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.ORANGE_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.MAGENTA_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.LIGHT_BLUE_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.YELLOW_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.LIME_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.PINK_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.GRAY_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.LIGHT_GRAY_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.CYAN_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.PURPLE_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.BLUE_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.BROWN_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.GREEN_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.RED_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsBlocks.BLACK_SLEEPING_BAG.get().asItem());
                tabData.accept(UtsItems.KELP_GELATIN.get());
                tabData.accept(UtsItems.MARSHMALLOW.get());
                tabData.accept(UtsItems.COOKED_MARSHMALLOW.get());
                tabData.accept(UtsItems.MARSHMALLOW_ON_A_STICK.get());
                tabData.accept(UtsItems.COOKED_MARSHMALLOW_ON_A_STICK.get());
                tabData.accept(UtsItems.CHOCOLATE_BAR.get());
                tabData.accept(UtsItems.OPEN_CHOCOLATE_BAR.get());
                tabData.accept(UtsItems.WRAPPED_CHOCOLATE_BAR.get());
                tabData.accept(UtsItems.CHOCOLATE_PIECE.get());
                tabData.accept(UtsItems.BISCUIT.get());
                tabData.accept(UtsItems.SMORE.get().asItem());
            }).build());

    public static void register() {
        REGISTRY.register();
    }
}
