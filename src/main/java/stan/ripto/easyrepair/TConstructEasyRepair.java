package stan.ripto.easyrepair;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import stan.ripto.easyrepair.event.EasyRepairModEvents;
import stan.ripto.easyrepair.event.RepairItemHandler;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.screen.EasyRepairMenus;

@Mod(TConstructEasyRepair.MOD_ID)
public class TConstructEasyRepair {
    public static final String MOD_ID = "easyrepair";

    public TConstructEasyRepair(FMLJavaModLoadingContext context) {
        IEventBus modBus = context.getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        EasyRepairItems.register(modBus);
        EasyRepairMenus.register(modBus);

        modBus.addListener(EasyRepairModEvents::onClientSetup);
        modBus.addListener(EasyRepairModEvents::onBuildCreativeModeTabContents);

        forgeBus.addListener(RepairItemHandler::onServerStarting);
    }
}
