package net.withrage.simplehammers.item.custom;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {
    public static final TagKey<Item> WOODEN_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "wooden_tool_materials"));
    public static final TagKey<Item> STONE_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "stone_tool_materials"));
    public static final TagKey<Item> COPPER_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "copper_tool_materials"));
    public static final TagKey<Item> GOLDEN_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "golden_tool_materials"));
    public static final TagKey<Item> IRON_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "iron_tool_materials"));
    public static final TagKey<Item> EMERALD_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "emerald_tool_materials"));
    public static final TagKey<Item> DIAMOND_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "diamond_tool_materials"));
    public static final TagKey<Item> NETHERITE_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simplehammers", "netherite_tool_materials"));
    private ModItemTags() {}
}