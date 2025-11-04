package net.withrage.simplehammers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;

public class ModItems {
    public static final Item WOODEN_HAMMER = registerHammer("wooden_hammer", ModToolMaterial.WOOD, 2, -2.8F, 108, false);
    public static final Item STONE_HAMMER = registerHammer("stone_hammer", ModToolMaterial.STONE, 3, -2.8F, 262, false);
    public static final Item COPPER_HAMMER = registerHammer("copper_hammer", ModToolMaterial.COPPER, 3, -2.8F, 380, false);
    public static final Item GOLDEN_HAMMER = registerHammer("golden_hammer", ModToolMaterial.GOLD, 2, -2.8F, 64, false);
    public static final Item IRON_HAMMER = registerHammer("iron_hammer", ModToolMaterial.IRON, 4, -2.8F, 506, false);
    public static final Item EMERALD_HAMMER = registerHammer("emerald_hammer", ModToolMaterial.EMERALD, 5, -2.8F, 2084, false);
    public static final Item DIAMOND_HAMMER = registerHammer("diamond_hammer", ModToolMaterial.DIAMOND, 5, -2.8F, 3122, false);
    public static final Item NETHERITE_HAMMER = registerHammer("netherite_hammer", ModToolMaterial.NETHERITE, 6, -2.8F, 4062, true);

    private static Item registerHammer(String name,
                                       ToolMaterial material,
                                       int attackDamage,
                                       float attackSpeed,
                                       int durability,
                                       boolean fireproof) {

        Item.Settings settings = new Item.Settings()
                .maxCount(1)
                .maxDamage(durability);

        if (fireproof) settings.fireproof();

        return Registry.register(Registries.ITEM,
                new Identifier("simplehammers", name),
                new HammerItem(material, attackDamage, attackSpeed, settings));
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
    }
}
