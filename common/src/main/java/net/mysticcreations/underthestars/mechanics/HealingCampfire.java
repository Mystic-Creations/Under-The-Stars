package net.mysticcreations.underthestars.mechanics;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class HealingCampfire {
    public static void onPlayerTick(Player player) {
        Level level = player.level();
        BlockPos playerPos = player.blockPosition();

        boolean nearCampfire = false;
        AABB area = new AABB(
            playerPos.offset(-6, -2, -6),
            playerPos.offset(7, 3, 7)
        );

        for (BlockPos pos : BlockPos.betweenClosed(
            (int) area.minX, (int) area.minY, (int) area.minZ,
            (int) area.maxX - 1, (int) area.maxY - 1, (int) area.maxZ - 1
        )) {
            BlockState state = level.getBlockState(pos);

            if (state.getBlock() instanceof CampfireBlock &&
                state.getValue(CampfireBlock.LIT)) {
                nearCampfire = true;
                break;
            }
        }
        if (nearCampfire) {
            player.addEffect(new MobEffectInstance(
                MobEffects.REGENERATION,
                100,
                0,
                false,
                false,
                false
            ));
        }
    }
}