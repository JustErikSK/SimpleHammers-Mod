package net.withrage.simplehammers.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;

public class ModItems {
    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", new HammerItem(ModToolMaterial.WOOD, new FabricItemSettings().maxCount(1).maxDamage(5))); // 108
    public static final Item STONE_HAMMER = registerItem("stone_hammer", new HammerItem(ModToolMaterial.STONE, new FabricItemSettings().maxCount(1).maxDamage(262)));
    public static final Item COPPER_HAMMER = registerItem("copper_hammer", new HammerItem(ModToolMaterial.COPPER, new FabricItemSettings().maxCount(1).maxDamage(380)));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", new HammerItem(ModToolMaterial.GOLD, new FabricItemSettings().maxCount(1).maxDamage(64)));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", new HammerItem(ModToolMaterial.IRON, new FabricItemSettings().maxCount(1).maxDamage(506)));
    public static final Item EMERALD_HAMMER = registerItem("emerald_hammer", new HammerItem(ModToolMaterial.EMERALD, new FabricItemSettings().maxCount(1).maxDamage(2084)));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new HammerItem(ModToolMaterial.DIAMOND, new FabricItemSettings().maxCount(1).maxDamage(3122)));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new HammerItem(ModToolMaterial.NETHERITE, new FabricItemSettings().maxCount(1).maxDamage(4062)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
    }
}
