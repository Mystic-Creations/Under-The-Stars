package net.mysticcreations.underthestars.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.mysticcreations.underthestars.UnderTheStars;
import net.mysticcreations.underthestars.item.CookedMarshmallowOnAStick;
import net.mysticcreations.underthestars.item.MarshmallowOnAStick;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(
        method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0
    )
    private BakedModel underthestars$swapMarshmallowModel(BakedModel original, ItemStack stack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        if (!(stack.getItem() instanceof MarshmallowOnAStick)) {
            return original;
        }
        ItemRenderer renderer = (ItemRenderer)(Object)this;

        if (context == ItemDisplayContext.GUI || context == ItemDisplayContext.GROUND || context == ItemDisplayContext.FIXED) {
            return renderer.getItemModelShaper().getModelManager().getModel(
                UnderTheStars.asModelResource("marshmallow_on_a_stick", "inventory")
            );
        }
        return renderer.getItemModelShaper().getModelManager().getModel(
            UnderTheStars.asModelResource("marshmallow_on_a_stick_handheld", "inventory")
        );
    }
    @ModifyVariable(
        method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0
    )
    private BakedModel underthestars$swapCookedMarshmallowModel(BakedModel original, ItemStack stack, ItemDisplayContext context, boolean leftHanded, PoseStack poseStack, MultiBufferSource buffer, int light, int overlay) {
        if (!(stack.getItem() instanceof CookedMarshmallowOnAStick)) {
            return original;
        }
        ItemRenderer renderer = (ItemRenderer)(Object)this;

        if (context == ItemDisplayContext.GUI || context == ItemDisplayContext.GROUND || context == ItemDisplayContext.FIXED) {
            return renderer.getItemModelShaper().getModelManager().getModel(
                UnderTheStars.asModelResource("cooked_marshmallow_on_a_stick", "inventory")
            );
        }
        return renderer.getItemModelShaper().getModelManager().getModel(
            UnderTheStars.asModelResource("cooked_marshmallow_on_a_stick_handheld", "inventory")
        );
    }
}

