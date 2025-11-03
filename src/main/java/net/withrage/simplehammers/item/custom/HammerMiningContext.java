package net.withrage.simplehammers.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import java.util.WeakHashMap;
import java.util.Map;

public class HammerMiningContext {

    private static final Map<PlayerEntity, Direction> LAST_FACE = new WeakHashMap<>();

    public static void setLastHitFace(PlayerEntity player, Direction face) {
        LAST_FACE.put(player, face);
    }

    public static Direction consumeLastHitFace(PlayerEntity player) {
        return LAST_FACE.remove(player);
    }
}
