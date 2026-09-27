package net.withrage.simplehammers.item.custom;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.withrage.simplehammers.SimpleHammers;

public class ModItemGroups {

    public static final ResourceKey<CreativeModeTab> SIMPLE_HAMMERS_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(SimpleHammers.MOD_ID, "simplehammers")
    );

    public static final CreativeModeTab SIMPLE_HAMMERS = FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.IRON_HAMMER))
                    .title(Component.translatable("itemgroup.simplehammers"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.WOODEN_HAMMER);
                        output.accept(ModItems.STONE_HAMMER);
                        output.accept(ModItems.COPPER_HAMMER);
                        output.accept(ModItems.GOLDEN_HAMMER);
                        output.accept(ModItems.IRON_HAMMER);
                        output.accept(ModItems.EMERALD_HAMMER);
                        output.accept(ModItems.DIAMOND_HAMMER);
                        output.accept(ModItems.NETHERITE_HAMMER);
                        output.accept(ModItems.WOODEN_HANDLE);
                        output.accept(ModItems.WOODEN_HAMMER_HEAD);
                        output.accept(ModItems.STONE_HAMMER_HEAD);
                        output.accept(ModItems.COPPER_HAMMER_HEAD);
                        output.accept(ModItems.GOLDEN_HAMMER_HEAD);
                        output.accept(ModItems.IRON_HAMMER_HEAD);
                        output.accept(ModItems.EMERALD_HAMMER_HEAD);
                        output.accept(ModItems.DIAMOND_HAMMER_HEAD);
                    })
                    .build();

    public static void registerItemGroups() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SIMPLE_HAMMERS_KEY, SIMPLE_HAMMERS);
        SimpleHammers.LOGGER.info("Registering Item Groups for " + SimpleHammers.MOD_ID);
    }
}