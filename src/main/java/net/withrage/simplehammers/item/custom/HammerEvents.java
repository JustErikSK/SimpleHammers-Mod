package net.withrage.simplehammers.item.custom;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.network.ServerPlayerEntity;

public class HammerEvents {
    public static void register() {

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (!(player instanceof ServerPlayerEntity serverPlayer)) {
                return true;
            }

            HitResult hr = serverPlayer.raycast(5.0D, 0.0F, false);
            if (hr instanceof BlockHitResult bhr) {
                BlockPos hitPos = bhr.getBlockPos();
                if (hitPos.equals(pos)) {
                    Direction face = bhr.getSide();
                    HammerMiningContext.setLastHitFace(player, face);
                }
            }

            return true;
        });
    }
}