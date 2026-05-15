package net.withrage.simplehammers.item;


import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.withrage.simplehammers.config.SimpleHammersConfig;

public class ModToolMaterial {
    public static final ToolMaterial WOOD =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_WOODEN_TOOL,
                    SimpleHammersConfig.woodenDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.WOODEN_TOOL_MATERIALS
            );

    public static final ToolMaterial STONE =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_STONE_TOOL,
                    SimpleHammersConfig.stoneDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.STONE_TOOL_MATERIALS
            );

    public static final ToolMaterial COPPER =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    SimpleHammersConfig.copperDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.COPPER_TOOL_MATERIALS
            );

    public static final ToolMaterial GOLD =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_GOLD_TOOL,
                    SimpleHammersConfig.goldenDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.GOLDEN_TOOL_MATERIALS
            );

    public static final ToolMaterial IRON =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    SimpleHammersConfig.ironDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.IRON_TOOL_MATERIALS
            );

    public static final ToolMaterial EMERALD =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                    SimpleHammersConfig.emeraldDurability,
                    8.0f,
                    3.0f,
                    15,
                    ModItemTags.EMERALD_TOOL_MATERIALS
            );

    public static final ToolMaterial DIAMOND =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                    SimpleHammersConfig.diamondDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.DIAMOND_TOOL_MATERIALS
            );

    public static final ToolMaterial NETHERITE =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                    SimpleHammersConfig.netheriteDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.NETHERITE_TOOL_MATERIALS
            );
}
