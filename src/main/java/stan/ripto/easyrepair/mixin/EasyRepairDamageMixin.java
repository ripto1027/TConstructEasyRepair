package stan.ripto.easyrepair.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.UUID;

@Mixin(value = ToolDamageUtil.class, remap = false)
public abstract class EasyRepairDamageMixin {
    @Inject(
            method = "damage",
            at = @At("HEAD")
    )
    private static void onDamage(IToolStackView tool, int amount, LivingEntity entity, ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        if (!(entity instanceof ServerPlayer player)) return;

        CompoundTag nbt = stack.getOrCreateTag();

        UUID current = nbt.hasUUID("owner") ? nbt.getUUID("owner") : null;
        UUID holderId = player.getUUID();

        if (!holderId.equals(current)) {
            nbt.putUUID("owner", holderId);
        }
    }
}
