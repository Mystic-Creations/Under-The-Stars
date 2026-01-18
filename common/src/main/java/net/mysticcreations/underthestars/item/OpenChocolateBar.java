
package net.mysticcreations.underthestars.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class OpenChocolateBar extends Item {
	public OpenChocolateBar() {
		super(new Properties()
            .stacksTo(16)
            .rarity(Rarity.COMMON)
            .food((new FoodProperties.Builder()).nutrition(7).saturationMod(0.6f)
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
}


