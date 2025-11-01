package net.withrage.simplehammers.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BrokenHammerItem extends Item {
    public BrokenHammerItem(Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return 13;
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return 0xFF0000;
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return 0.0F;
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return false;
    }
}
