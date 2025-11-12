package stan.ripto.easyrepair.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.key.EasyRepairKeyMappings;
import stan.ripto.easyrepair.menu.EasyRepairMenus;
import stan.ripto.easyrepair.network.EasyRepairNetwork;
import stan.ripto.easyrepair.screen.RepairItemPouchIIIScreen;
import stan.ripto.easyrepair.screen.RepairItemPouchIIScreen;
import stan.ripto.easyrepair.screen.RepairItemPouchIScreen;

public class EasyRepairModEvents {
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_I.get(), RepairItemPouchIScreen::new);
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_II.get(), RepairItemPouchIIScreen::new);
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_III.get(), RepairItemPouchIIIScreen::new);
        });
    }

    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH_I);
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH_II);
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH_III);
        }
    }

    public static void onRegisterKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(EasyRepairKeyMappings.OPEN_POUCH_INVENTORY);
    }

    @SuppressWarnings("unused")
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        EasyRepairNetwork.register();
    }
}
