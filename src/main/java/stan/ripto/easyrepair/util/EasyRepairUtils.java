package stan.ripto.easyrepair.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import slimeknights.tconstruct.library.tools.part.IRepairKitItem;
import stan.ripto.easyrepair.event.EasyRepairServerStartingEvent;

public class EasyRepairUtils {
    public static IItemHandler getPouchHandler(ItemStack pouch) {
        return pouch.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElseThrow(() -> new IllegalStateException("Capability not found on pouch item."));
    }

    public static boolean isRepairMaterial(Item item) {
        return EasyRepairServerStartingEvent.getRepairItems().contains(item) || item instanceof IRepairKitItem;
    }
}
