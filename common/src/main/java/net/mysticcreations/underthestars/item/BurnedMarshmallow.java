
package net.mysticcreations.underthestars.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class BurnedMarshmallow extends Item {
	public BurnedMarshmallow() {
		super(new Properties()
            .stacksTo(64)
            .rarity(Rarity.COMMON)
            .food((new FoodProperties.Builder()).nutrition(0).saturationMod(0.5f)
				.build()
            ));
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 16;
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
	}
}
