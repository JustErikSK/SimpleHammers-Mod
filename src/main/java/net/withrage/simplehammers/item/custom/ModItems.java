package net.withrage.simplehammers.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;

public class ModItems {
    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", new Item(new FabricItemSettings()));
    public static final Item STONE_HAMMER = registerItem("stone_hammer", new Item(new FabricItemSettings()));
    public static final Item COPPER_HAMMER = registerItem("copper_hammer", new Item(new FabricItemSettings()));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", new Item(new FabricItemSettings()));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", new Item(new FabricItemSettings()));
    public static final Item EMERALD_HAMMER = registerItem("emerald_hammer", new Item(new FabricItemSettings()));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new Item(new FabricItemSettings()));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
    }
}
