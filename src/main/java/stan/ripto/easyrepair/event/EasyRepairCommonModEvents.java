package stan.ripto.easyrepair.event;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.datagen.client.item.EasyRepairItemModelProvider;
import stan.ripto.easyrepair.datagen.client.lang.EasyRepairLanguageProvider;
import stan.ripto.easyrepair.datagen.server.advancement.EasyRepairAdvancementProvider;
import stan.ripto.easyrepair.datagen.server.curios.EasyRepairCuriosDataProvider;
import stan.ripto.easyrepair.datagen.server.recipe.EasyRepairRecipeProvider;
import stan.ripto.easyrepair.network.EasyRepairNetwork;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TinkersEasyRepair.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EasyRepairCommonModEvents {
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

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        EasyRepairNetwork.register();
    }
}
