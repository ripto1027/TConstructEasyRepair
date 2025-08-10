package stan.ripto.easyrepair.datagen.client.item;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.item.EasyRepairItems;

public class EasyRepairItemModelProvider extends ItemModelProvider {
    public EasyRepairItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TConstructEasyRepair.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(EasyRepairItems.REPAIR_ITEM_POUCH.get());
    }
}
