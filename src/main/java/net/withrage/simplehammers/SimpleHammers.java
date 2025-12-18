package net.withrage.simplehammers;

import net.fabricmc.api.ModInitializer;

import net.withrage.simplehammers.item.custom.HammerEvents;
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
        HammerEvents.register();
	}
}