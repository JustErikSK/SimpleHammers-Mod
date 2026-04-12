package net.withrage.simplehammers.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.withrage.simplehammers.config.SimpleHammersConfig;

import java.util.HashSet;
import java.util.Set;

public class HammerItem extends Item {

    public HammerItem(ToolMaterial material,
                      int attackDamage,
                      float attackSpeed,
                      Properties properties) {
        super(properties.pickaxe(material, attackDamage, attackSpeed));
    }

    private static boolean breakingExtraBlocks = false;

    private static Direction fallbackFace(Player player) {
        float pitch = player.getXRot();
        if (pitch > 60f)  return Direction.DOWN;
        if (pitch < -60f) return Direction.UP;
        return player.getDirection();
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        super.inventoryTick(stack, level, entity, slot);

        if (level.isClientSide()) return;
        if (!(entity instanceof Player)) return;
        int max = stack.getMaxDamage();
        if (max <= 0) return;
        int dmg = stack.getDamageValue();
        if (dmg < 0) {stack.setDamageValue(0);return;}
        if (dmg >= max) {stack.setDamageValue(max - 1);}
    }

    @Override
    public boolean mineBlock(ItemStack stack,
                            Level level,
                            BlockState state,
                            BlockPos pos,
                            LivingEntity miner) {

        boolean result = super.mineBlock(stack, level, state, pos, miner);

        if (breakingExtraBlocks) return result;
        if (level.isClientSide()) return result;
        if (!(miner instanceof Player player)) return result;

        if (!state.is(BlockTags.MINEABLE_WITH_PICKAXE)) return result;
        if (SimpleHammersConfig.sneakMines1x1 && player.isShiftKeyDown()) return result;

        Direction hitFace = HammerMiningContext.consumeLastHitFace(player);
        if (hitFace == null) hitFace = fallbackFace(player);

        breakingExtraBlocks = true;
        try {
            breakExtraBlocksAround(pos, level, player, stack, hitFace);
        } finally {
            breakingExtraBlocks = false;
        }

        return result;
    }

    private void breakExtraBlocksAround(BlockPos origin,
                                        Level level,
                                        Player player,
                                        ItemStack hammerStack,
                                        Direction hitFace) {

        Plane plane = getPlaneFromHitFace(hitFace);
        Set<BlockPos> targets = new HashSet<>();

        for (int ox = -1; ox <= 1; ox++) {
            for (int oy = -1; oy <= 1; oy++) {
                for (int oz = -1; oz <= 1; oz++) {
                    if (ox == 0 && oy == 0 && oz == 0) continue;
                    if (!plane.allowsOffset(ox, oy, oz)) continue;
                    targets.add(origin.offset(ox, oy, oz));
                }
            }
        }
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        for (BlockPos targetPos : targets) {
            if (hammerStack.getDamageValue() >= hammerStack.getMaxDamage() - 1) break;
            BlockState targetState = level.getBlockState(targetPos);
            if (!canBreakExtraBlock(level, player, hammerStack, targetState, targetPos)) continue;
            serverPlayer.gameMode.destroyBlock(targetPos);
        }
    }

    private boolean canBreakExtraBlock(Level level,
                                       Player player,
                                       ItemStack hammerStack,
                                       BlockState targetState,
                                       BlockPos targetPos) {
        if (targetState.isAir()) return false;
        if (targetState.getDestroySpeed(level, targetPos) < 0.0F) return false;
        if (!targetState.is(BlockTags.MINEABLE_WITH_PICKAXE)) return false;
        if (!hammerStack.isCorrectToolForDrops(targetState)) return false;
        if (!player.hasCorrectToolForDrops(targetState)) return false;
        return true;
    }

    private Plane getPlaneFromHitFace(Direction face) {
        if (face == null) {
            return Plane.HORIZONTAL;
        }
        switch (face) {
            case UP:
            case DOWN:
                return Plane.HORIZONTAL;
            case NORTH:
            case SOUTH:
                return Plane.VERTICAL_YX;
            case EAST:
            case WEST:
                return Plane.VERTICAL_YZ;
            default:
                return Plane.HORIZONTAL;
        }
    }

    private enum Plane {
        HORIZONTAL {
            @Override
            public boolean allowsOffset(int ox, int oy, int oz) {
                return oy == 0 && !(ox == 0 && oz == 0);
            }
        },

        VERTICAL_YX {
            @Override
            public boolean allowsOffset(int ox, int oy, int oz) {
                return oz == 0 && !(ox == 0 && oy == 0);
            }
        },

        VERTICAL_YZ {
            @Override
            public boolean allowsOffset(int ox, int oy, int oz) {
                return ox == 0 && !(oy == 0 && oz == 0);
            }
        };
        public abstract boolean allowsOffset(int ox, int oy, int oz);
    }
}