package net.withrage.simplehammers.item.custom;

import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class HammerItem extends MiningToolItem {


    public HammerItem(ToolMaterial material, Settings settings) {
        super(
                5.0f,
                -3.2f,
                material,
                BlockTags.PICKAXE_MINEABLE,
                settings
        );
    }
}
