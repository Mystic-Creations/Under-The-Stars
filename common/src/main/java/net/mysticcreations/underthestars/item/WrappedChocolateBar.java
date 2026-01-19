package net.mysticcreations.underthestars.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import net.mysticcreations.underthestars.init.UtsItems;

public class WrappedChocolateBar extends Item {
    public WrappedChocolateBar() {
        super(new Properties()
            .stacksTo(64)
            .rarity(Rarity.COMMON)
        );
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!player.isShiftKeyDown()) {
            return InteractionResultHolder.pass(stack);
        }
        if (!level.isClientSide) {
            ItemStack openBar = new ItemStack(UtsItems.OPEN_CHOCOLATE_BAR.get());

            if (stack.getCount() == 1) {
                player.setItemInHand(hand, openBar);
            } else {
                stack.shrink(1);
                player.setItemInHand(hand, openBar);
            }

            //TODO: Add custom unwrapping sound
            player.playSound(SoundEvents.BUNDLE_DROP_CONTENTS, 1.0F, 1.0F);
        }

        return InteractionResultHolder.sidedSuccess(player.getItemInHand(hand), level.isClientSide);
    }

}
