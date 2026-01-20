package stan.ripto.easyrepair.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.key.EasyRepairKeyMappings;
import stan.ripto.easyrepair.network.EasyRepairNetwork;
import stan.ripto.easyrepair.network.PouchInventoryOpenPacket;

@Mod.EventBusSubscriber(
        modid = TinkersEasyRepair.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = Dist.CLIENT
)
public class EasyRepairClientForgeEvents {
    @SuppressWarnings("InstantiationOfUtilityClass")
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            while (EasyRepairKeyMappings.OPEN_POUCH_INVENTORY.consumeClick()) {
                if (Minecraft.getInstance().screen instanceof InventoryScreen) return;
                EasyRepairNetwork.CHANNEL.sendToServer(new PouchInventoryOpenPacket());
            }
        }
    }
}
