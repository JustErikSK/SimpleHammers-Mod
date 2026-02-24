package net.withrage.simplehammers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;

import java.util.function.Function;

public class ModItems {
    public static final Item WOODEN_HAMMER = registerHammer("wooden_hammer", ModToolMaterial.WOOD, 1, -2.8f, false);
    public static final Item STONE_HAMMER = registerHammer("stone_hammer", ModToolMaterial.STONE, 1, -2.8f, false);
    public static final Item COPPER_HAMMER = registerHammer("copper_hammer", ModToolMaterial.COPPER, 1, -2.8f, false);
    public static final Item GOLDEN_HAMMER = registerHammer("golden_hammer", ModToolMaterial.GOLD, 1, -2.8f, false);
    public static final Item IRON_HAMMER = registerHammer("iron_hammer", ModToolMaterial.IRON, 2, -2.8f, false);
    public static final Item EMERALD_HAMMER = registerHammer("emerald_hammer", ModToolMaterial.EMERALD, 3, -2.8f, false);
    public static final Item DIAMOND_HAMMER = registerHammer("diamond_hammer", ModToolMaterial.DIAMOND, 3, -2.8f, false);
    public static final Item NETHERITE_HAMMER = registerHammer("netherite_hammer", ModToolMaterial.NETHERITE, 4, -2.8f, true);

    public static final Item WOODEN_HANDLE = registerItem("wooden_handle", Item::new);
    public static final Item WOODEN_HAMMER_HEAD = registerItem("wooden_hammer_head", Item::new);
    public static final Item STONE_HAMMER_HEAD = registerItem("stone_hammer_head", Item::new);
    public static final Item COPPER_HAMMER_HEAD = registerItem("copper_hammer_head", Item::new);
    public static final Item GOLDEN_HAMMER_HEAD = registerItem("golden_hammer_head", Item::new);
    public static final Item IRON_HAMMER_HEAD = registerItem("iron_hammer_head", Item::new);
    public static final Item EMERALD_HAMMER_HEAD = registerItem("emerald_hammer_head", Item::new);
    public static final Item DIAMOND_HAMMER_HEAD = registerItem("diamond_hammer_head", Item::new);

    public static final Item HAMMER_ADV_TROPHY = registerItem("all_hammers", Item::new);

    private static Item registerHammer(String name,
                                       ToolMaterial material,
                                       int attackDamage,
                                       float attackSpeed,
                                       boolean fireproof) {

        Identifier id = Identifier.of(SimpleHammers.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(Registries.ITEM.getKey(), id);

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

        Item.Settings settings = new Item.Settings()
                .registryKey(key)
                .maxCount(1)
                .enchantable(enchantability)
                .repairable(material.repairItems());

        if (fireproof) {
            settings.fireproof();
        }

        Item hammer = new HammerItem(material, attackDamage, attackSpeed, settings);

        return Registry.register(Registries.ITEM, key, hammer);
    }

    private static Item registerItem(String name, Function<Item.Settings, Item> factory) {
        Identifier id = Identifier.of(SimpleHammers.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(Registries.ITEM.getKey(), id);

        Item.Settings settings = new Item.Settings().registryKey(key);
        Item item = factory.apply(settings);

        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Simple Hammers items for {}", SimpleHammers.MOD_ID);
    }
}