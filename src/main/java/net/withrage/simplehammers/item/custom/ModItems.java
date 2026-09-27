package net.withrage.simplehammers.item.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.withrage.simplehammers.SimpleHammers;

import java.util.function.Function;

public class ModItems {
    public static final Item WOODEN_HAMMER = registerHammer("wooden_hammer", ModToolMaterial.WOOD, 1, -3.2F, false);
    public static final Item STONE_HAMMER = registerHammer("stone_hammer", ModToolMaterial.STONE, 1, -3.1F, false);
    public static final Item COPPER_HAMMER = registerHammer("copper_hammer", ModToolMaterial.COPPER, 1, -2.9F, false);
    public static final Item GOLDEN_HAMMER = registerHammer("golden_hammer", ModToolMaterial.GOLD, 1, -2.7F, false);
    public static final Item IRON_HAMMER = registerHammer("iron_hammer", ModToolMaterial.IRON, 2, -3.0F, false);
    public static final Item EMERALD_HAMMER = registerHammer("emerald_hammer", ModToolMaterial.EMERALD, 3, -2.5F, false);
    public static final Item DIAMOND_HAMMER = registerHammer("diamond_hammer", ModToolMaterial.DIAMOND, 3, -2.6F, false);
    public static final Item NETHERITE_HAMMER = registerHammer("netherite_hammer", ModToolMaterial.NETHERITE, 4, -2.4F, true);

    public static final Item WOODEN_HANDLE = registerItem("wooden_handle");
    public static final Item WOODEN_HAMMER_HEAD = registerItem("wooden_hammer_head");
    public static final Item STONE_HAMMER_HEAD = registerItem("stone_hammer_head");
    public static final Item COPPER_HAMMER_HEAD = registerItem("copper_hammer_head");
    public static final Item GOLDEN_HAMMER_HEAD = registerItem("golden_hammer_head");
    public static final Item IRON_HAMMER_HEAD = registerItem("iron_hammer_head");
    public static final Item EMERALD_HAMMER_HEAD = registerItem("emerald_hammer_head");
    public static final Item DIAMOND_HAMMER_HEAD = registerItem("diamond_hammer_head");

    public static final Item HAMMER_ADV_TROPHY = registerItem("all_hammers");

    private static Item registerHammer(String name,
                                       ToolMaterial material,
                                       int attackDamage,
                                       float attackSpeed,
                                       boolean fireproof) {

        int enchantability = switch (name) {
            case "wooden_hammer"    -> 15;
            case "stone_hammer"     -> 5;
            case "copper_hammer"    -> 12;
            case "golden_hammer"    -> 22;
            case "iron_hammer"      -> 14;
            case "emerald_hammer"   -> 18;
            case "diamond_hammer"   -> 11;
            case "netherite_hammer" -> 16;
            default -> 10;
        };

        Item.Properties properties = new Item.Properties()
                .stacksTo(1)
                .enchantable(enchantability)
                .repairable(material.repairItems());

        if (fireproof) {
            properties = properties.fireResistant();
        }

        return register(name, props -> new HammerItem(material, attackDamage, attackSpeed, props), properties);
    }

    private static Item registerItem(String name) {
        return register(name, Item::new, new Item.Properties());
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(SimpleHammers.MOD_ID, name)
        );

        T item = factory.apply(properties.setId(itemKey));
        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Simple Hammers items for {}", SimpleHammers.MOD_ID);
    }
}