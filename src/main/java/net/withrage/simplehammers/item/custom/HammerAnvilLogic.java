package net.withrage.simplehammers.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class HammerAnvilLogic {

    public static class Result {
        public final ItemStack output;
        public final int xpCost;
        public final int materialsToConsume;

        public Result(ItemStack output, int xpCost, int materialsToConsume) {
            this.output = output;
            this.xpCost = xpCost;
            this.materialsToConsume = materialsToConsume;
        }
    }

    public static Result tryRepairHammer(Inventory inputSlots, String newAnvilName) {
        ItemStack left  = inputSlots.getStack(0);
        ItemStack right = inputSlots.getStack(1);

        if (left.isEmpty()) {
            return null;
        }

        HammerRepairHelper.RepairRule rule = HammerRepairHelper.getRule(left.getItem());
        if (rule == null) {
            return null;
        }

        if (HammerRepairHelper.matchesRepairMaterial(rule, right)) {
            return null;
        }

        ItemStack repaired = new ItemStack(rule.repairedItem());

        if (left.hasNbt()) {
            repaired.setNbt(left.getNbt().copy());
        }

        repaired.setDamage(0);

        if (newAnvilName != null && !newAnvilName.isBlank()) {
            repaired.setCustomName(Text.literal(newAnvilName));
        }

        int xpCost = 5;
        int matsToConsume = 1;

        return new Result(repaired, xpCost, matsToConsume);
    }

    public static void applyRepair(PlayerEntity player, Inventory inputSlots, Result result) {
        if (!player.isCreative() && result.xpCost > 0) {
            player.addExperienceLevels(-result.xpCost);
        }

        inputSlots.setStack(0, ItemStack.EMPTY);

        ItemStack mat = inputSlots.getStack(1).copy();
        mat.decrement(result.materialsToConsume);
        if (mat.isEmpty()) {
            inputSlots.setStack(1, ItemStack.EMPTY);
        } else {
            inputSlots.setStack(1, mat);
        }
    }
}