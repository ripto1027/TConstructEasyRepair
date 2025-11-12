package stan.ripto.easyrepair.datagen.server.curios;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import stan.ripto.easyrepair.TConstructEasyRepair;
import top.theillusivec4.curios.api.CuriosDataProvider;

import java.util.concurrent.CompletableFuture;

public class EasyRepairCuriosDataProvider extends CuriosDataProvider {
    public EasyRepairCuriosDataProvider(PackOutput output, ExistingFileHelper fileHelper, CompletableFuture<HolderLookup.Provider> registries) {
        super(TConstructEasyRepair.MOD_ID, output, fileHelper, registries);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.createSlot("pouch").icon(ResourceLocation.parse("easyrepair:slot/repair_item_pouch_slot"));
        this.createEntities("entities").addPlayer().addSlots("pouch");
    }
}
