package net.withrage.simplehammers;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.withrage.simplehammers.config.SimpleHammersConfig;
import net.withrage.simplehammers.item.HammerEvents;
import net.withrage.simplehammers.item.ModCreativeTabs;
import net.withrage.simplehammers.item.ModItems;
import org.slf4j.Logger;

@Mod(SimpleHammers.MOD_ID)
public class SimpleHammers {
    public static final String MOD_ID = "simplehammers";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleHammers(IEventBus eventBus) {
        SimpleHammersConfig.load();

        ModItems.register(eventBus);
        ModCreativeTabs.register(eventBus);

        HammerEvents.register();
    }
}
