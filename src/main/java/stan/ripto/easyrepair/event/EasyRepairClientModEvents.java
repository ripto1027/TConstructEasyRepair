package stan.ripto.easyrepair.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.key.EasyRepairKeyMappings;
import stan.ripto.easyrepair.menu.EasyRepairMenus;
import stan.ripto.easyrepair.screen.RepairItemPouchIIIScreen;
import stan.ripto.easyrepair.screen.RepairItemPouchIIScreen;
import stan.ripto.easyrepair.screen.RepairItemPouchIScreen;

@Mod.EventBusSubscriber(
        modid = TinkersEasyRepair.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class EasyRepairClientModEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_I.get(), RepairItemPouchIScreen::new);
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_II.get(), RepairItemPouchIIScreen::new);
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_III.get(), RepairItemPouchIIIScreen::new);
        });
    }

    @SubscribeEvent
    public static void onRegisterKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(EasyRepairKeyMappings.OPEN_POUCH_INVENTORY);
    }
}
