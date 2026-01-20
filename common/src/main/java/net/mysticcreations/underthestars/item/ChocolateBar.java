
package net.mysticcreations.underthestars.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class ChocolateBar extends Item {
	public ChocolateBar() {
		super(new Properties()
            .stacksTo(16)
            .rarity(Rarity.COMMON)
            .food((new FoodProperties.Builder()).nutrition(6).saturationMod(1.2f)
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

    @Override //Fix it on all chocolate stuff
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        Level level = player.level();

        if (!(target instanceof Wolf wolf)) {
            return InteractionResult.PASS;
        }
        if (!level.isClientSide) {
            wolf.addEffect(new MobEffectInstance(
                MobEffects.POISON,
                150,
                0
            ));
            stack.shrink(1);
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}


