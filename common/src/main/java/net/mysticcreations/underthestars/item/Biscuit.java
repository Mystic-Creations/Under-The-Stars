
package net.mysticcreations.underthestars.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class Biscuit extends Item {
	public Biscuit() {
		super(new Properties()
            .stacksTo(64)
            .rarity(Rarity.COMMON)
            .food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.5f)
                .build()
            ));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 32;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
	}

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (!(target instanceof Parrot parrot)) {
            return InteractionResult.PASS;
        }
        Level level = player.level();

        if (parrot.isTame()) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide) {
            stack.shrink(1);
            if (level.random.nextInt(3) == 0) {
                parrot.tame(player);
                parrot.setOrderedToSit(false);
            } else {
                parrot.level().broadcastEntityEvent(parrot, (byte) 6);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}

