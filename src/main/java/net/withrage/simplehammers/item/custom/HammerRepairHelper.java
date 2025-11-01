package net.withrage.simplehammers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.Map;

public class HammerRepairHelper {

    public record RepairRule(Item repairedItem, Item repairMaterial, TagKey<Item> repairTag) {}

    private static final Map<Item, RepairRule> REPAIR_RULES = new HashMap<>();

    public static void register(Item brokenHammer, Item repairedHammer, Item repairMat) {
        REPAIR_RULES.put(brokenHammer, new RepairRule(repairedHammer, repairMat, null));
    }

    public static void register(Item brokenHammer, Item repairedHammer, TagKey<Item> repairTag) {
        REPAIR_RULES.put(brokenHammer, new RepairRule(repairedHammer, null, repairTag));
    }

    public static RepairRule getRule(Item brokenHammerItem) {
        return REPAIR_RULES.get(brokenHammerItem);
    }

    public static boolean matchesRepairMaterial(RepairRule rule, ItemStack inputStack) {
        if (rule == null || inputStack.isEmpty()) return false;

        if (rule.repairMaterial != null && inputStack.isOf(rule.repairMaterial)) {
            return true;
        }

        if (rule.repairTag != null && inputStack.isIn(rule.repairTag)) {
            return true;
        }

        return false;
    }
}
