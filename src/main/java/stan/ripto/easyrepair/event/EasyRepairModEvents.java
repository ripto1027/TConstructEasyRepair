package stan.ripto.easyrepair.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.screen.EasyRepairMenus;
import stan.ripto.easyrepair.screen.RepairItemPouchScreen;

public class EasyRepairModEvents {
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() ->
                MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH.get(), RepairItemPouchScreen::new));
    }

    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH);
        }
    }
}
