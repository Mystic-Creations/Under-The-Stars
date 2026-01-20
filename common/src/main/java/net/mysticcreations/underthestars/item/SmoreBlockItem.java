package net.mysticcreations.underthestars.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;

public class SmoreBlockItem extends BlockItem {
    public SmoreBlockItem(Block block) {
        super(block, new Properties()
            .stacksTo(64)
            .rarity(Rarity.COMMON)
            .food((new FoodProperties.Builder()).nutrition(8).saturationMod(15.0f)
                .build()
            ));
    }
}
