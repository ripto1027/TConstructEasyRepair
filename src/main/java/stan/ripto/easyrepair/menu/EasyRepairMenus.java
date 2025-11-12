package stan.ripto.easyrepair.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.easyrepair.TinkersEasyRepair;

public class EasyRepairMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TinkersEasyRepair.MOD_ID);

    public static final RegistryObject<MenuType<RepairItemPouchIMenu>> REPAIR_ITEM_POUCH_I = register("repair_item_pouch_i", RepairItemPouchIMenu::new);
    public static final RegistryObject<MenuType<RepairItemPouchIIMenu>> REPAIR_ITEM_POUCH_II = register("repair_item_pouch_ii", RepairItemPouchIIMenu::new);
    public static final RegistryObject<MenuType<RepairItemPouchIIIMenu>> REPAIR_ITEM_POUCH_III = register("repair_item_pouch_iii", RepairItemPouchIIIMenu::new);

    public static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> register(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus bus) {
        MENUS.register(bus);
    }
}
