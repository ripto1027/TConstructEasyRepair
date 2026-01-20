package stan.ripto.easyrepair.mixin;

import net.minecraft.nbt.CompoundTag;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import slimeknights.tconstruct.library.tools.nbt.StatsNBT;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import stan.ripto.easyrepair.util.repair.ToolRepairHandler;

@Mixin(value = ToolStack.class, remap = false)
public abstract class EasyRepairToolStackMixin {
    @Shadow
    public abstract StatsNBT getStats();

    @Shadow
    protected abstract void setBrokenRaw(boolean broken);

    @Shadow
    private int damage;

    @Shadow
    @Final
    private CompoundTag nbt;

    @Shadow
    public abstract boolean isBroken();

    @Inject(method = "setDamage", at = @At("HEAD"), cancellable = true)
    private void setDamageOrRepair(int damage, CallbackInfo ci) {
        ci.cancel();
        int durability = this.getStats().getInt(ToolStats.DURABILITY);
        if (damage >= durability) {
            if (!this.isBroken()) {
                if (ToolRepairHandler.tryRepair((ToolStack)(Object) this)) {
                    return;
                }
            }
            damage = Math.max(0, durability);
            this.setBrokenRaw(true);
        } else {
            this.setBrokenRaw(false);
        }

        this.damage = damage;
        this.nbt.putInt("Damage", damage);
    }
}