package net.withrage.simplehammers.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HammerItem extends MiningToolItem {

    private final ItemStack brokenHammer;

    public HammerItem(ToolMaterial material, ItemStack brokenHammer, Settings settings) {
        super(
                5.0f,
                -3.2f,
                material,
                BlockTags.PICKAXE_MINEABLE,
                settings
        );

        this.brokenHammer = brokenHammer;
    }

    private void damageAndMaybeBreak(ItemStack stack,
                                     PlayerEntity player,
                                     Hand hand,
                                     int amount) {

        int current = stack.getDamage();
        int max = stack.getMaxDamage();
        int after = current + amount;

        if (after >= max) {
            ItemStack broken = brokenHammer.copy();

            if (stack.hasNbt()) {
                broken.setNbt(stack.getNbt().copy());
            }

            player.setStackInHand(hand, broken);
            player.sendToolBreakStatus(hand);

        } else {
            stack.setDamage(after);
        }
    }

    @Override
    public boolean postMine(ItemStack stack,
                            World world,
                            BlockState state,
                            BlockPos pos,
                            LivingEntity miner) {

        boolean result = super.postMine(stack, world, state, pos, miner);

        if (!world.isClient && miner instanceof PlayerEntity player) {
            if (state.getHardness(world, pos) != 0.0f) {
                Hand handUsed = player.getActiveHand() != null
                        ? player.getActiveHand()
                        : Hand.MAIN_HAND;
                damageAndMaybeBreak(stack, player, handUsed, 1);
            }
        }

        return result;
    }

    @Override
    public boolean postHit(ItemStack stack,
                           LivingEntity target,
                           LivingEntity attacker) {

        boolean result = super.postHit(stack, target, attacker);

        if (!attacker.getWorld().isClient && attacker instanceof PlayerEntity player) {
            Hand handUsed = player.getActiveHand() != null
                    ? player.getActiveHand()
                    : Hand.MAIN_HAND;
            damageAndMaybeBreak(stack, player, handUsed, 2);
        }

        return result;
    }
}
