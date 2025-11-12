package stan.ripto.easyrepair.tab;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.item.EasyRepairItems;

public class EasyRepairTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TConstructEasyRepair.MOD_ID);

    public static void register(IEventBus bus) {
        TABS.register(
                "tconstruct_easy_repair_tab",
                () -> CreativeModeTab.builder()
                        .title(Component.literal("TConstruct Easy Repair"))
                        .icon(EasyRepairItems.REPAIR_ITEM_POUCH_I.get()::getDefaultInstance)
                        .displayItems((param, output) -> {
                            output.accept(EasyRepairItems.REPAIR_ITEM_POUCH_I.get());
                            output.accept(EasyRepairItems.REPAIR_ITEM_POUCH_II.get());
                            output.accept(EasyRepairItems.REPAIR_ITEM_POUCH_III.get());
                        })
                        .build()
        );

        TABS.register(bus);
    }
}
