package net.withrage.simplehammers;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.withrage.simplehammers.config.SimpleHammersConfig;
import net.withrage.simplehammers.item.HammerEvents;
import net.withrage.simplehammers.item.ModCreativeTabs;
import net.withrage.simplehammers.item.ModItems;
import org.slf4j.Logger;

@Mod(SimpleHammers.MOD_ID)
public class SimpleHammers {
    public static final String MOD_ID = "simplehammers";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleHammers() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SimpleHammersConfig.load();

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        HammerEvents.register();
    }
}