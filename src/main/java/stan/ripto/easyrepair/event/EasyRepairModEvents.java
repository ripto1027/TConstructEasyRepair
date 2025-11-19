package stan.ripto.easyrepair.event;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.datagen.client.item.EasyRepairItemModelProvider;
import stan.ripto.easyrepair.datagen.client.lang.EasyRepairLanguageProvider;
import stan.ripto.easyrepair.datagen.server.advancement.EasyRepairAdvancementProvider;
import stan.ripto.easyrepair.datagen.server.curios.EasyRepairCuriosDataProvider;
import stan.ripto.easyrepair.datagen.server.recipe.EasyRepairRecipeProvider;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.key.EasyRepairKeyMappings;
import stan.ripto.easyrepair.menu.EasyRepairMenus;
import stan.ripto.easyrepair.network.EasyRepairNetwork;
import stan.ripto.easyrepair.screen.RepairItemPouchIIIScreen;
import stan.ripto.easyrepair.screen.RepairItemPouchIIScreen;
import stan.ripto.easyrepair.screen.RepairItemPouchIScreen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TinkersEasyRepair.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EasyRepairModEvents {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        gen.addProvider(event.includeClient(), new EasyRepairItemModelProvider(output, helper));
        gen.addProvider(event.includeClient(), new EasyRepairLanguageProvider.ENUS(output));
        gen.addProvider(event.includeClient(), new EasyRepairLanguageProvider.JAJP(output));
        gen.addProvider(event.includeServer(), new EasyRepairRecipeProvider(output));
        gen.addProvider(event.includeServer(), new EasyRepairCuriosDataProvider(output, helper, provider));
        gen.addProvider(event.includeServer(), new ForgeAdvancementProvider(output, provider, helper, List.of(new EasyRepairAdvancementProvider())));
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_I.get(), RepairItemPouchIScreen::new);
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_II.get(), RepairItemPouchIIScreen::new);
            MenuScreens.register(EasyRepairMenus.REPAIR_ITEM_POUCH_III.get(), RepairItemPouchIIIScreen::new);
        });
    }

    @SubscribeEvent
    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.TOOLS_AND_UTILITIES)) {
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH_I);
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH_II);
            event.accept(EasyRepairItems.REPAIR_ITEM_POUCH_III);
        }
    }

    @SubscribeEvent
    public static void onRegisterKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(EasyRepairKeyMappings.OPEN_POUCH_INVENTORY);
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        EasyRepairNetwork.register();
    }
}
