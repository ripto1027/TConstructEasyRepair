package stan.ripto.easyrepair.datagen.client.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.event.EasyReairHandler;
import stan.ripto.easyrepair.item.EasyRepairItems;

public class EasyRepairLanguageProvider {
    public static class ENUS extends LanguageProvider {
        public ENUS(PackOutput output) {
            super(output, TConstructEasyRepair.MODID, "en_us");
        }

        @Override
        protected void addTranslations() {
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH, "Repair Item Pouch");
            add(EasyReairHandler.POUCH_EMPTY_MESSAGE, "The pouch is empty. Refill repair materials.");
        }
    }

    public static class JAJP extends LanguageProvider {
        public JAJP(PackOutput output) {
            super(output, TConstructEasyRepair.MODID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH, "リペアアイテムポーチ");
            add(EasyReairHandler.POUCH_EMPTY_MESSAGE, "ポーチが空になりました。修理素材を補充してください。");
        }
    }
}
