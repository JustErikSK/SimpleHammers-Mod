package net.withrage.simplehammers.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class HammerItem extends MiningToolItem {

    public HammerItem(ToolMaterial material,
                            int attackDamage,
                            float attackSpeed,
                            Settings settings) {
        super(attackDamage, attackSpeed, material, BlockTags.PICKAXE_MINEABLE, settings);
    }

    @Override
    public boolean postMine(ItemStack stack,
                            World world,
                            BlockState state,
                            BlockPos pos,
                            LivingEntity miner) {

        boolean result = super.postMine(stack, world, state, pos, miner);

        if (!world.isClient && miner instanceof PlayerEntity player) {
            Direction hitFace = HammerMiningContext.consumeLastHitFace(player);
            breakExtraBlocksAround(pos, world, player, stack, state, hitFace);
        }

        return result;
    }

    private void breakExtraBlocksAround(BlockPos origin,
                                        World world,
                                        PlayerEntity player,
                                        ItemStack hammerStack,
                                        BlockState originState,
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

        for (BlockPos targetPos : targets) {
            breakOneExtraBlock(world, player, hammerStack, origin, originState, targetPos);
        }
    }

    private void breakOneExtraBlock(World world,
                                    PlayerEntity player,
                                    ItemStack hammerStack,
                                    BlockPos originPos,
                                    BlockState originState,
                                    BlockPos targetPos) {

        if (!(world instanceof ServerWorld serverWorld)) {
            return;
        }

        BlockState targetState = world.getBlockState(targetPos);
        if (targetState.isAir()) return;

        float originHardness = originState.getHardness(world, originPos);
        float targetHardness = targetState.getHardness(world, targetPos);
        if (targetHardness < 0) return;

        if (originHardness >= 0 && targetHardness > originHardness + 0.5f) {
            return;
        }

        boolean creative = player.isCreative();

        if (!creative) {
            hammerStack.damage(1, player, (p) -> {
                p.sendToolBreakStatus(Hand.MAIN_HAND);
            });
        }

        world.breakBlock(targetPos, false, player);

        Block.dropStacks(
                targetState,
                serverWorld,
                targetPos,
                world.getBlockEntity(targetPos),
                player,
                hammerStack
        );

        world.setBlockState(
                targetPos,
                net.minecraft.block.Blocks.AIR.getDefaultState(),
                Block.NOTIFY_ALL
        );
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