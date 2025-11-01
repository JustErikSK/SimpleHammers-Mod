package net.withrage.simplehammers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.Map;

public class HammerRepairHelper {

    public record RepairRule(Item repairedItem, Item repairMaterialItem, TagKey<Item> repairMaterialTag) {
    }

    private static final Map<Item, RepairRule> RULES = new HashMap<>();

    public static void register(Item brokenHammer, Item repairedHammer, Item repairItem) {
        RULES.put(brokenHammer, new RepairRule(repairedHammer, repairItem, null));
    }

    public static void register(Item brokenHammer, Item repairedHammer, TagKey<Item> repairTag) {
        RULES.put(brokenHammer, new RepairRule(repairedHammer, null, repairTag));
    }

    public static RepairRule getRule(Item brokenHammerItem) {
        return RULES.get(brokenHammerItem);
    }

    public static boolean matchesRepairMaterial(RepairRule rule, ItemStack stack) {
        if (stack.isEmpty()) return true;

        if (rule.repairMaterialItem() != null) {
            return !stack.isOf(rule.repairMaterialItem());
        }

        if (rule.repairMaterialTag() != null) {
            return !stack.isIn(rule.repairMaterialTag());
        }

        return true;
    }
}