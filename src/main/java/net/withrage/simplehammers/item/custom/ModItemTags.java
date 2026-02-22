package net.withrage.simplehammers.item.custom;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {
    public static final TagKey<Item> WOODEN_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "wooden_tool_materials"));
    public static final TagKey<Item> STONE_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "stone_tool_materials"));
    public static final TagKey<Item> COPPER_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "copper_tool_materials"));
    public static final TagKey<Item> GOLDEN_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "golden_tool_materials"));
    public static final TagKey<Item> IRON_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "iron_tool_materials"));
    public static final TagKey<Item> EMERALD_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "emerald_tool_materials"));
    public static final TagKey<Item> DIAMOND_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "diamond_tool_materials"));
    public static final TagKey<Item> NETHERITE_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "netherite_tool_materials"));
    private ModItemTags() {}
}