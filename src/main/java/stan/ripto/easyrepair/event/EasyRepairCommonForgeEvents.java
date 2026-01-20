package stan.ripto.easyrepair.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.capability.RepairItemPouchIIIInventoryProvider;
import stan.ripto.easyrepair.capability.RepairItemPouchIIInventoryProvider;
import stan.ripto.easyrepair.capability.RepairItemPouchIInventoryProvider;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.util.ServerPlayerGetter;

@Mod.EventBusSubscriber(modid = TinkersEasyRepair.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EasyRepairCommonForgeEvents {
    private static final ResourceLocation INVENTORY =
            ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "inventory");

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        if (stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_I.get())) {
            event.addCapability(INVENTORY, new RepairItemPouchIInventoryProvider());

        } else if (stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_II.get())) {
            event.addCapability(INVENTORY, new RepairItemPouchIIInventoryProvider());

        } else if (stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_III.get())) {
            event.addCapability(INVENTORY, new RepairItemPouchIIIInventoryProvider());
        }
    }

    @SubscribeEvent
    public static void onServerStarted(ServerStartedEvent event) {
        ServerPlayerGetter.setServer(event.getServer());
    }
}