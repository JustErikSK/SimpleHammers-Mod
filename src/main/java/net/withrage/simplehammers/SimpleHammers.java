package net.withrage.simplehammers;

import net.fabricmc.api.ModInitializer;

import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.withrage.simplehammers.item.custom.HammerRepairHelper;
import net.withrage.simplehammers.item.custom.ModItemGroups;
import net.withrage.simplehammers.item.custom.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleHammers implements ModInitializer {
	public static final String MOD_ID = "simplehammers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();

        HammerRepairHelper.register(ModItems.BROKEN_WOODEN_HAMMER, ModItems.WOODEN_HAMMER, ItemTags.PLANKS);
        HammerRepairHelper.register(ModItems.BROKEN_STONE_HAMMER, ModItems.STONE_HAMMER, Items.COBBLESTONE);
        HammerRepairHelper.register(ModItems.BROKEN_COPPER_HAMMER, ModItems.COPPER_HAMMER, Items.COPPER_INGOT);
        HammerRepairHelper.register(ModItems.BROKEN_GOLDEN_HAMMER, ModItems.GOLDEN_HAMMER, Items.GOLD_INGOT);
        HammerRepairHelper.register(ModItems.BROKEN_IRON_HAMMER, ModItems.IRON_HAMMER, Items.IRON_INGOT);
        HammerRepairHelper.register(ModItems.BROKEN_EMERALD_HAMMER, ModItems.EMERALD_HAMMER, Items.EMERALD);
        HammerRepairHelper.register(ModItems.BROKEN_DIAMOND_HAMMER, ModItems.DIAMOND_HAMMER, Items.DIAMOND);
        HammerRepairHelper.register(ModItems.BROKEN_NETHERITE_HAMMER, ModItems.NETHERITE_HAMMER, Items.NETHERITE_INGOT);
	}
}