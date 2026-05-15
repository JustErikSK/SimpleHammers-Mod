package net.withrage.simplehammers.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HammerEvents {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(new HammerEvents());
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;

        HitResult hitResult = player.pick(5.0D, 0.0F, false);

        if (hitResult instanceof BlockHitResult blockHitResult
                && hitResult.getType() == HitResult.Type.BLOCK) {

            BlockPos hitPos = blockHitResult.getBlockPos();

            if (hitPos.closerThan(event.getPos(), 1.5D)) {
                HammerMiningContext.setLastHitFace(player, blockHitResult.getDirection());
            }
        }
    }
}