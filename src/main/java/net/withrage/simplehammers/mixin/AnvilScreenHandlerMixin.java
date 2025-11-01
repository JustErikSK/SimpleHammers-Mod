package net.withrage.simplehammers.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.withrage.simplehammers.item.custom.HammerRepairHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin {

    static {
        System.out.println("[SimpleHammers] AnvilScreenHandlerMixin class loaded!");
    }

    @Shadow private String newItemName;

    @Inject(method = "onTakeOutput", at = @At("HEAD"), cancellable = true)
    private void simplehammers$anvilRepair(
            PlayerEntity player,
            ItemStack vanillaOutput,
            CallbackInfo ci
    ) {
        System.out.println("[SimpleHammers] onTakeOutput BEGIN");

        AnvilScreenHandler self = (AnvilScreenHandler)(Object)this;

        Slot leftSlot = self.getSlot(0);
        Slot matSlot  = self.getSlot(1);
        Slot outSlot  = self.getSlot(2);

        ItemStack leftStack = leftSlot.getStack();
        ItemStack matStack  = matSlot.getStack();

        if (leftStack.isEmpty()) {
            return;
        }

        HammerRepairHelper.RepairRule rule = HammerRepairHelper.getRule(leftStack.getItem());
        if (rule == null) {
            return;
        }

        if (HammerRepairHelper.matchesRepairMaterial(rule, matStack)) {
            return;
        }

        ci.cancel();

        ItemStack repaired = new ItemStack(rule.repairedItem());

        if (leftStack.hasNbt()) {
            repaired.setNbt(leftStack.getNbt().copy());
        }

        repaired.setDamage(0);

        if (this.newItemName != null && !this.newItemName.isBlank()) {
            repaired.setCustomName(Text.literal(this.newItemName));
        }

        if (!player.getInventory().insertStack(repaired.copy())) {
            player.dropItem(repaired.copy(), false);
        }

        if (!player.isCreative()) {
            player.addExperienceLevels(-5);
        }

        ItemStack newMat = matStack.copy();
        newMat.decrement(1);
        matSlot.setStack(newMat.isEmpty() ? ItemStack.EMPTY : newMat);

        leftSlot.setStack(ItemStack.EMPTY);

        outSlot.setStack(ItemStack.EMPTY);
    }
}