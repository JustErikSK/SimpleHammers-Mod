package net.withrage.simplehammers.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simplehammers.SimpleHammers;

public class ModItems {
    public static final Item BROKEN_WOODEN_HAMMER = registerItem("broken_wooden_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BROKEN_STONE_HAMMER = registerItem("broken_stone_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BROKEN_COPPER_HAMMER = registerItem("broken_copper_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BROKEN_GOLDEN_HAMMER = registerItem("broken_golden_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BROKEN_IRON_HAMMER = registerItem("broken_iron_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BROKEN_EMERALD_HAMMER = registerItem("broken_emerald_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BROKEN_DIAMOND_HAMMER = registerItem("broken_diamond_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));
    public static final Item BROKEN_NETHERITE_HAMMER = registerItem("broken_netherite_hammer", new BrokenHammerItem(new FabricItemSettings().maxCount(1)));

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", new HammerItem(ModToolMaterial.WOOD, new ItemStack(BROKEN_WOODEN_HAMMER),new FabricItemSettings().maxCount(1).maxDamage(59)));
    public static final Item STONE_HAMMER = registerItem("stone_hammer", new HammerItem(ModToolMaterial.STONE, new ItemStack(BROKEN_STONE_HAMMER), new FabricItemSettings().maxCount(1).maxDamage(131)));
    public static final Item COPPER_HAMMER = registerItem("copper_hammer", new HammerItem(ModToolMaterial.COPPER, new ItemStack(BROKEN_COPPER_HAMMER), new FabricItemSettings().maxCount(1).maxDamage(200)));
    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", new HammerItem(ModToolMaterial.GOLD, new ItemStack(BROKEN_GOLDEN_HAMMER), new FabricItemSettings().maxCount(1).maxDamage(32)));
    public static final Item IRON_HAMMER = registerItem("iron_hammer", new HammerItem(ModToolMaterial.IRON, new ItemStack(BROKEN_IRON_HAMMER), new FabricItemSettings().maxCount(1).maxDamage(250)));
    public static final Item EMERALD_HAMMER = registerItem("emerald_hammer", new HammerItem(ModToolMaterial.EMERALD, new ItemStack(BROKEN_EMERALD_HAMMER), new FabricItemSettings().maxCount(1).maxDamage(1400)));
    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new HammerItem(ModToolMaterial.DIAMOND, new ItemStack(BROKEN_DIAMOND_HAMMER), new FabricItemSettings().maxCount(1).maxDamage(1561)));
    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new HammerItem(ModToolMaterial.NETHERITE, new ItemStack(BROKEN_NETHERITE_HAMMER), new FabricItemSettings().maxCount(1).maxDamage(2031)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleHammers.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleHammers.LOGGER.info("Registering Mod Items for " + SimpleHammers.MOD_ID);
    }
}
