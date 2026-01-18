
package net.mysticcreations.underthestars.item;

import dev.architectury.event.events.common.TickEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
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
import net.mysticcreations.underthestars.UnderTheStars;

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
        if (!(player instanceof ServerPlayer serverPlayer)) {
            return InteractionResult.PASS;
        }
        Level level = player.level();

        if (parrot.isTame()) {
            if (parrot.getHealth() < parrot.getMaxHealth()) {
                parrot.heal(2.0F);
                stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
        if (!level.isClientSide) {
            stack.shrink(1);
            if (level.random.nextInt(3) == 0) {
                parrot.tame(player);
                parrot.setOrderedToSit(false);
                parrot.level().broadcastEntityEvent(parrot, (byte) 7);

                if (UnderTheStars.hasAdvancement(serverPlayer, "exploration/parrot_tame_with_biscuit")) return InteractionResult.PASS;
                UnderTheStars.grantAdvancement(serverPlayer, "exploration/parrot_tame_with_biscuit");
            } else {
                parrot.level().broadcastEntityEvent(parrot, (byte) 6);
            }
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}

