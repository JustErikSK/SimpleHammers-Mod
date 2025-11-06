package net.withrage.simplehammers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;

public class ModItems {
    public static final Item WOODEN_HAMMER   = registerHammer(
            "wooden_hammer",
            ModToolMaterial.WOOD,
            2,
            -2.8f,
            108,
            false
    );

    public static final Item STONE_HAMMER    = registerHammer(
            "stone_hammer",
            ModToolMaterial.STONE,
            3,
            -2.8f,
            262,
            false
    );

    public static final Item COPPER_HAMMER   = registerHammer(
            "copper_hammer",
            ModToolMaterial.COPPER,
            3,
            -2.8f,
            380,
            false
    );

    public static final Item GOLDEN_HAMMER   = registerHammer(
            "golden_hammer",
            ModToolMaterial.GOLD,
            2,
            -2.8f,
            64,
            false
    );

    public static final Item IRON_HAMMER     = registerHammer(
            "iron_hammer",
            ModToolMaterial.IRON,
            4,
            -2.8f,
            506,
            false
    );

    public static final Item EMERALD_HAMMER  = registerHammer(
            "emerald_hammer",
            ModToolMaterial.EMERALD,
            5,
            -2.8f,
            2084,
            false
    );

    public static final Item DIAMOND_HAMMER  = registerHammer(
            "diamond_hammer",
            ModToolMaterial.DIAMOND,
            5,
            -2.8f,
            3122,
            false
    );

    public static final Item NETHERITE_HAMMER = registerHammer(
            "netherite_hammer",
            ModToolMaterial.NETHERITE,
            6,
            -2.8f,
            4062,
            true
    );

    private static Item registerHammer(String name,
                                       ToolMaterial material,
                                       int attackDamage,
                                       float attackSpeed,
                                       int durability,
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
                .maxDamage(durability)
                .enchantable(enchantability)
                .repairable(material.repairItems());

        if (fireproof) {
            settings.fireproof();
        }

        Item hammer = new HammerItem(material, attackDamage, attackSpeed, settings);

        return Registry.register(Registries.ITEM, key, hammer);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Simple Hammers items for {}", SimpleHammers.MOD_ID);
    }
}