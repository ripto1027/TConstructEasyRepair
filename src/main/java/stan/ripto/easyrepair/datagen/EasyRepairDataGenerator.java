package stan.ripto.easyrepair.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.datagen.client.item.EasyRepairItemModelProvider;
import stan.ripto.easyrepair.datagen.client.lang.EasyRepairLanguageProvider;
import stan.ripto.easyrepair.datagen.server.recipe.EasyRepairRecipeProvider;

@Mod.EventBusSubscriber(modid = TConstructEasyRepair.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EasyRepairDataGenerator {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        ExistingFileHelper helper = event.getExistingFileHelper();

        gen.addProvider(event.includeClient(), new EasyRepairItemModelProvider(output, helper));
        gen.addProvider(event.includeClient(), new EasyRepairLanguageProvider.ENUS(output));
        gen.addProvider(event.includeClient(), new EasyRepairLanguageProvider.JAJP(output));
        gen.addProvider(event.includeServer(), new EasyRepairRecipeProvider(output));
    }
}
