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

    private void turnIntoBroken(PlayerEntity player, Hand hand, ItemStack stack) {
        if (stack.getDamage() >= stack.getMaxDamage()) {
            ItemStack broken = brokenHammer.copy();

            if (stack.hasNbt()) {
                assert stack.getNbt() != null;
                broken.setNbt(stack.getNbt().copy());
            }

            player.setStackInHand(hand, broken);
        }
    }

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, PlayerEntity player) {
        boolean result = super.postMine(stack, world, state, pos, player);

        if (!world.isClient && state.getHardness(world, pos) != 0.0F) {
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));
            turnIntoBroken(player, player.getActiveHand(), stack);
        }

        return result;
    }

    public boolean postHit(ItemStack stack, LivingEntity target, PlayerEntity attacker) {
        boolean result = super.postHit(stack, target, attacker);

        if (!attacker.getWorld().isClient) {
            stack.damage(2, attacker, p -> p.sendToolBreakStatus(attacker.getActiveHand()));
            turnIntoBroken(attacker, attacker.getActiveHand(), stack);
        }

        return result;
    }
}
