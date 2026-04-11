package net.withrage.simplehammers.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.withrage.simplehammers.config.SimpleHammersConfig;

import java.util.HashSet;
import java.util.Set;

public class HammerItem extends MiningToolItem {
    public HammerItem(ToolMaterial material, int attackDamage, float attackSpeed, int durability, Settings settings) {
        super(
                material,
                BlockTags.PICKAXE_MINEABLE,
                settings.attributeModifiers(MiningToolItem.createAttributeModifiers(material, attackDamage, attackSpeed))
                        .maxDamage(durability)
        );
    }

    private static boolean breakingExtraBlocks = false;

    private static Direction fallbackFace(PlayerEntity p) {
        float pitch = p.getPitch();
        if (pitch > 60f)  return Direction.DOWN;
        if (pitch < -60f) return Direction.UP;
        return p.getHorizontalFacing();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (world.isClient()) return;
        int dmg = stack.getDamage();
        if (dmg < 0) {
            stack.setDamage(0);
        }
    }

    @Override
    public boolean postMine(ItemStack stack,
                            World world,
                            BlockState state,
                            BlockPos pos,
                            LivingEntity miner) {

        boolean result = super.postMine(stack, world, state, pos, miner);

        if (breakingExtraBlocks) return result;
        if (world.isClient) return result;
        if (!(miner instanceof PlayerEntity player)) return result;

        if (!state.isIn(BlockTags.PICKAXE_MINEABLE)) return result;
        if (SimpleHammersConfig.sneakMines1x1 && player.isSneaking()) return result;

        Direction hitFace = HammerMiningContext.consumeLastHitFace(player);
        if (hitFace == null) hitFace = fallbackFace(player);

        breakingExtraBlocks = true;
        try {
            breakExtraBlocksAround(pos, world, player, stack, hitFace);
        } finally {
            breakingExtraBlocks = false;
        }

        return result;
    }

    private void breakExtraBlocksAround(BlockPos origin,
                                        World world,
                                        PlayerEntity player,
                                        ItemStack hammerStack,
                                        Direction hitFace) {

        Plane plane = getPlaneFromHitFace(hitFace);
        Set<BlockPos> targets = new HashSet<>();

        for (int ox = -1; ox <= 1; ox++) {
            for (int oy = -1; oy <= 1; oy++) {
                for (int oz = -1; oz <= 1; oz++) {
                    if (ox == 0 && oy == 0 && oz == 0) continue;
                    if (!plane.allowsOffset(ox, oy, oz)) continue;
                    targets.add(origin.add(ox, oy, oz));
                }
            }
        }
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;
        for (BlockPos targetPos : targets) {
            if (hammerStack.getDamage() >= hammerStack.getMaxDamage() - 1) break;
            BlockState targetState = world.getBlockState(targetPos);
            if (!canBreakExtraBlock(world, player, hammerStack, targetState, targetPos)) continue;
            serverPlayer.interactionManager.tryBreakBlock(targetPos);
        }
    }

    private boolean canBreakExtraBlock(World world,
                                       PlayerEntity player,
                                       ItemStack hammerStack,
                                       BlockState targetState,
                                       BlockPos targetPos) {
        if (targetState.isAir()) return false;
        if (targetState.getHardness(world, targetPos) < 0.0F) return false;
        if (!targetState.isIn(BlockTags.PICKAXE_MINEABLE)) return false;
        if (!hammerStack.isSuitableFor(targetState)) return false;
        if (!player.canHarvest(targetState)) return false;
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