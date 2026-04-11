package net.withrage.simplehammers.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;
import net.withrage.simplehammers.config.SimpleHammersConfig;

public class ModItems {
    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", new HammerItem(ModToolMaterial.WOOD, 1, -3.2F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.woodenDurability)));
    public static final Item STONE_HAMMER = registerItem("stone_hammer", new HammerItem(ModToolMaterial.STONE, 1, -3.1F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.stoneDurability)));
    public static final Item COPPER_HAMMER = registerItem("copper_hammer", new HammerItem(ModToolMaterial.COPPER, 1, -2.9F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.copperDurability)));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", new HammerItem(ModToolMaterial.GOLD, 1, -2.7F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.goldenDurability)));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", new HammerItem(ModToolMaterial.IRON, 2, -3.0F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.ironDurability)));
    public static final Item EMERALD_HAMMER = registerItem("emerald_hammer", new HammerItem(ModToolMaterial.EMERALD, 3, -2.5F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.emeraldDurability)));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new HammerItem(ModToolMaterial.DIAMOND, 3, -2.6F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.diamondDurability)));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new HammerItem(ModToolMaterial.NETHERITE, 4, -2.4F, new Item.Settings().maxCount(1).maxDamage(SimpleHammersConfig.netheriteDurability).fireproof()));

    public static final Item WOODEN_HANDLE = registerItem("wooden_handle", new Item(new FabricItemSettings()));
    public static final Item WOODEN_HAMMER_HEAD = registerItem("wooden_hammer_head", new Item(new FabricItemSettings()));
    public static final Item STONE_HAMMER_HEAD = registerItem("stone_hammer_head", new Item(new FabricItemSettings()));
    public static final Item COPPER_HAMMER_HEAD = registerItem("copper_hammer_head", new Item(new FabricItemSettings()));
    public static final Item GOLDEN_HAMMER_HEAD = registerItem("golden_hammer_head", new Item(new FabricItemSettings()));
    public static final Item IRON_HAMMER_HEAD = registerItem("iron_hammer_head", new Item(new FabricItemSettings()));
    public static final Item EMERALD_HAMMER_HEAD = registerItem("emerald_hammer_head", new Item(new FabricItemSettings()));
    public static final Item DIAMOND_HAMMER_HEAD = registerItem("diamond_hammer_head", new Item(new FabricItemSettings()));

    public static final Item HAMMER_ADV_TROPHY = registerItem("all_hammers", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
    }
}
