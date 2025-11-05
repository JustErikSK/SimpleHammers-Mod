package net.withrage.simplehammers.items.custom;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {
    public static final TagKey<Item> EMERALD_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "emerald_tool_materials"));
    public static final TagKey<Item> COPPER_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simplehammers", "copper_tool_materials"));
    private ModItemTags() {}
}