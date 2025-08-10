package stan.ripto.easyrepair.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;

public class EasyRepairUtils {
    public static IItemHandler getPouchHandler(ItemStack pouch) {
        return pouch.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElseThrow(() -> new IllegalStateException("Capability not found on pouch item."));
    }
}
