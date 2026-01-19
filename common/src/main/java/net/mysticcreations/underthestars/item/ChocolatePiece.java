
package net.mysticcreations.underthestars.item;

import java.util.List;

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

public class ChocolatePiece extends Item {
	public ChocolatePiece() {
		super(new Item.Properties()
            .stacksTo(64)
            .rarity(Rarity.COMMON)
            .food((new FoodProperties.Builder()).nutrition(0).saturationMod(0.15f)
                .build()
            ));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 8;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
	}

    @Override
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


