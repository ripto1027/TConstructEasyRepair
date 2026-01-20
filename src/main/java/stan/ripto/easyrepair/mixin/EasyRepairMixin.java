package stan.ripto.easyrepair.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import stan.ripto.easyrepair.event.BroadcastBreakEvent;

@Mixin(LivingEntity.class)
public abstract class EasyRepairMixin {
    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot pSlot);

    @Inject(
            method = "broadcastBreakEvent(Lnet/minecraft/world/entity/EquipmentSlot;)V",
            at = @At("TAIL")
    )
    private void callBroadcastBreak(EquipmentSlot slot, CallbackInfo ci) {
        if ((LivingEntity)(Object) this instanceof ServerPlayer player) {
            MinecraftForge.EVENT_BUS.post(new BroadcastBreakEvent(player, player.serverLevel(), this.getItemBySlot(slot)));
        }
    }
}