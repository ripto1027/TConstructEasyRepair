package stan.ripto.easyrepair;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import stan.ripto.easyrepair.event.EasyReairHandler;
import stan.ripto.easyrepair.event.RepairItemHandler;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.screen.EasyRepairMenus;
import stan.ripto.easyrepair.screen.RepairItemPouchScreen;

@Mod(TConstructEasyRepair.MODID)
public class TConstructEasyRepair {
    public static final String MODID = "easyrepair";

    public TConstructEasyRepair(FMLJavaModLoadingContext context) {
        IEventBus modBus = context.getModEventBus();
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        EasyRepairItems.register(modBus);
        EasyRepairMenus.register(modBus);
        modBus.addListener(this::onClientSetup);
        modBus.addListener(this::onBuildCreativeModeTabContents);

        forgeBus.addListener(EasyReairHandler::onPlayerTick);
        forgeBus.addListener(RepairItemHandler::onServerStarting);
    }

    public void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() ->
                MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH.get(), RepairItemPouchScreen::new));
    }

    public void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH);
        }
    }
}
