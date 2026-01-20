package stan.ripto.easyrepair.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import javax.annotation.Nullable;
import java.util.UUID;

@Mixin(ItemStack.class)
public abstract class EasyRepairItemStackMixin {
    @Shadow
    @Nullable
    public abstract CompoundTag getTag();

    @Inject(
            method = "inventoryTick",
            at = @At("TAIL")
    )
    private void onInventoryTick(Level level, Entity entity, int inventorySlot, boolean isCurrentItem, CallbackInfo ci) {
        if (level.isClientSide()) return;
        if (!(entity instanceof ServerPlayer player)) return;

        CompoundTag nbt = this.getTag();
        if (nbt == null || !ToolStack.isInitialized(nbt)) return;

        if (nbt.hasUUID("owner")) {
            UUID current = nbt.getUUID("owner");
            UUID holderUuid = player.getUUID();

            if (!holderUuid.equals(current)) {
                nbt.putUUID("owner", holderUuid);
            }
        } else {
            nbt.putUUID("owner", player.getUUID());
        }
    }
}
