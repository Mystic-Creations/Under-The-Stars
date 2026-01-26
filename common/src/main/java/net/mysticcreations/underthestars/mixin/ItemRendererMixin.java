package net.mysticcreations.underthestars.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.init.UtsBlocks;
import net.mysticcreations.underthestars.item.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Unique
    private static final Map<Class<? extends Item>, String> ITEM_MODELS = Map.of(
        Marshmallow.class, "marshmallow",
        CookedMarshmallow.class, "cooked_marshmallow",
        BurnedMarshmallow.class, "burned_marshmallow",
        MarshmallowOnAStick.class, "marshmallow_on_a_stick",
        CookedMarshmallowOnAStick.class, "cooked_marshmallow_on_a_stick",
        BurnedMarshmallowOnAStick.class, "burned_marshmallow_on_a_stick"
    );

    @ModifyVariable(
        method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
        at = @At("HEAD"), argsOnly = true, ordinal = 0
    )
    private BakedModel underthestars$swapItemModels(BakedModel original, ItemStack itemStack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        ItemRenderer renderer = (ItemRenderer)(Object)this;
        Item heldItem = itemStack.getItem();

        // Marshmallows
        for (var entry : ITEM_MODELS.entrySet()) {
            if (entry.getKey().isInstance(heldItem)) {
                String item = entry.getValue();
                return getModel(renderer, isGuiContext(context) ? item : "in_hand/" + item);
            }
        }

        // Smore
        if (heldItem instanceof BlockItem blockItem && blockItem.getBlock() == UtsBlocks.SMORE.get()) {
            return getModel(renderer, isGuiContext(context) ? "smore" : "in_hand/smore");
        }

        return original;
    }

    private static boolean isGuiContext(ItemDisplayContext context) {
        return context == ItemDisplayContext.GUI
            || context == ItemDisplayContext.GROUND
            || context == ItemDisplayContext.FIXED;
    }
    private static BakedModel getModel(ItemRenderer renderer, String path) {
        return renderer.getItemModelShaper()
            .getModelManager()
            .getModel(UnderTheStars.asModelResource(path, "inventory"));
    }
}
