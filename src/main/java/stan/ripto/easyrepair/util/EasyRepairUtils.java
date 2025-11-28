package stan.ripto.easyrepair.util;

import net.minecraft.world.item.Item;
import slimeknights.tconstruct.library.tools.part.IRepairKitItem;
import stan.ripto.easyrepair.event.EasyRepairServerStartingEvent;

public class EasyRepairUtils {
    public static boolean isRepairMaterial(Item item) {
        return EasyRepairServerStartingEvent.getRepairItems().contains(item) || item instanceof IRepairKitItem;
    }
}
