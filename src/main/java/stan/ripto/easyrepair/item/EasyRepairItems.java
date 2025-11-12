package stan.ripto.easyrepair.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.easyrepair.TConstructEasyRepair;

public class EasyRepairItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TConstructEasyRepair.MOD_ID);

    public static final RegistryObject<Item> REPAIR_ITEM_POUCH_I = ITEMS.register("repair_item_pouch_i", RepairItemPouchI::new);
    public static final RegistryObject<Item> REPAIR_ITEM_POUCH_II = ITEMS.register("repair_item_pouch_ii", RepairItemPouchII::new);
    public static final RegistryObject<Item> REPAIR_ITEM_POUCH_III = ITEMS.register("repair_item_pouch_iii", RepairItemPouchIII::new);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
