package net.mysticcreations.underthestars.mechanics;

import dev.architectury.event.EventResult;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.mysticcreations.underthestars.init.UtsEffects;

public class SugarRushInsomnia {
    public static EventResult onSleepAttempt(Player player, InteractionHand hand, BlockPos pos, Direction face) {
        Level level = player.level();
        BlockState state = level.getBlockState(pos);

        if (!(state.getBlock() instanceof BedBlock)) {
            return EventResult.pass();
        }

        if (player.hasEffect(UtsEffects.SUGAR_RUSH.get())) {
            //TODO: Make it appear
            if (!level.isClientSide) player.displayClientMessage(Component.translatable("message.underthestars.sugar_rush_insomnia"), true);
            return EventResult.interruptFalse();
        }

        return EventResult.pass();
    }
}
