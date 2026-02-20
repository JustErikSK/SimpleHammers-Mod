package net.withrage.simplehammers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;
import net.withrage.simplehammers.config.SimpleHammersConfig;

public class ModItems {
    public static final Item WOODEN_HAMMER = registerHammer("wooden_hammer", ModToolMaterial.WOOD, 1, -2.8F, SimpleHammersConfig.woodenDurability, false);
    public static final Item STONE_HAMMER = registerHammer("stone_hammer", ModToolMaterial.STONE, 1, -2.8F, SimpleHammersConfig.stoneDurability, false);
    public static final Item COPPER_HAMMER = registerHammer("copper_hammer", ModToolMaterial.COPPER, 1, -2.8F, SimpleHammersConfig.copperDurability, false);
    public static final Item GOLDEN_HAMMER = registerHammer("golden_hammer", ModToolMaterial.GOLD, 1, -2.8F, SimpleHammersConfig.goldenDurability, false);
    public static final Item IRON_HAMMER = registerHammer("iron_hammer", ModToolMaterial.IRON, 2, -2.8F, SimpleHammersConfig.ironDurability, false);
    public static final Item EMERALD_HAMMER = registerHammer("emerald_hammer", ModToolMaterial.EMERALD, 3, -2.8F, SimpleHammersConfig.emeraldDurability, false);
    public static final Item DIAMOND_HAMMER = registerHammer("diamond_hammer", ModToolMaterial.DIAMOND, 3, -2.8F, SimpleHammersConfig.diamondDurability, false);
    public static final Item NETHERITE_HAMMER = registerHammer("netherite_hammer", ModToolMaterial.NETHERITE, 4, -2.8F, SimpleHammersConfig.netheriteDurability, true);

    public static final Item WOODEN_HANDLE = registerItem("wooden_handle", new Item(new Item.Settings()));
    public static final Item WOODEN_HAMMER_HEAD = registerItem("wooden_hammer_head", new Item(new Item.Settings()));
    public static final Item STONE_HAMMER_HEAD = registerItem("stone_hammer_head", new Item(new Item.Settings()));
    public static final Item COPPER_HAMMER_HEAD = registerItem("copper_hammer_head", new Item(new Item.Settings()));
    public static final Item GOLDEN_HAMMER_HEAD = registerItem("golden_hammer_head", new Item(new Item.Settings()));
    public static final Item IRON_HAMMER_HEAD = registerItem("iron_hammer_head", new Item(new Item.Settings()));
    public static final Item EMERALD_HAMMER_HEAD = registerItem("emerald_hammer_head", new Item(new Item.Settings()));
    public static final Item DIAMOND_HAMMER_HEAD = registerItem("diamond_hammer_head", new Item(new Item.Settings()));

    private static Item registerHammer(String name,
                                       ToolMaterial material,
                                       int attackDamage,
                                       float attackSpeed,
                                       int durability,
                                       boolean fireproof) {

        Item.Settings settings = new Item.Settings().maxCount(1);

        if (fireproof) settings.fireproof();

        return Registry.register(Registries.ITEM,
                new Identifier("simplehammers", name),
                new HammerItem(material, attackDamage, attackSpeed, durability, settings));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
    }
}
