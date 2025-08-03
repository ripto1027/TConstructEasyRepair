package stan.ripto.easyrepair.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.capability.RepairItemPouchInventoryProvider;
import stan.ripto.easyrepair.item.EasyRepairItems;

@Mod.EventBusSubscriber(modid = TConstructEasyRepair.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OtherEvents {
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() == EasyRepairItems.REPAIR_ITEM_POUCH.get()) {
            event.addCapability(
                    ResourceLocation.fromNamespaceAndPath(TConstructEasyRepair.MODID, "inventory"),
                    new RepairItemPouchInventoryProvider()
            );
        }
    }
}
