package net.withrage.simplehammers.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.simplehammers.SimpleHammers;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpleHammers.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SIMPLE_HAMMERS_TAB =
            CREATIVE_MODE_TABS.register("simple_hammers_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup.simplehammers"))
                    .icon(() -> new ItemStack(ModItems.IRON_HAMMER.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.WOODEN_HAMMER.get());
                        output.accept(ModItems.STONE_HAMMER.get());
                        output.accept(ModItems.COPPER_HAMMER.get());
                        output.accept(ModItems.GOLDEN_HAMMER.get());
                        output.accept(ModItems.IRON_HAMMER.get());
                        output.accept(ModItems.EMERALD_HAMMER.get());
                        output.accept(ModItems.DIAMOND_HAMMER.get());
                        output.accept(ModItems.NETHERITE_HAMMER.get());
                        output.accept(ModItems.WOODEN_HANDLE.get());
                        output.accept(ModItems.WOODEN_HAMMER_HEAD.get());
                        output.accept(ModItems.STONE_HAMMER_HEAD.get());
                        output.accept(ModItems.COPPER_HAMMER_HEAD.get());
                        output.accept(ModItems.GOLDEN_HAMMER_HEAD.get());
                        output.accept(ModItems.IRON_HAMMER_HEAD.get());
                        output.accept(ModItems.EMERALD_HAMMER_HEAD.get());
                        output.accept(ModItems.DIAMOND_HAMMER_HEAD.get());
                    })
                    .build());

    public static void register(BusGroup eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}