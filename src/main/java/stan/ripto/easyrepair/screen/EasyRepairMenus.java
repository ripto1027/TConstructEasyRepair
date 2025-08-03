package stan.ripto.easyrepair.screen;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.easyrepair.TConstructEasyRepair;

public class EasyRepairMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TConstructEasyRepair.MODID);

    public static final RegistryObject<MenuType<RepairItemPouchMenu>> REPAIR_ITEM_POUCH =
            MENUS.register("repair_item_pouch", () -> IForgeMenuType.create(RepairItemPouchMenu::new));

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
