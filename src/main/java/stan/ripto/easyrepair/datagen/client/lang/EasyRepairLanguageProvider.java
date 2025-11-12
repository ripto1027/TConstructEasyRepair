package stan.ripto.easyrepair.datagen.client.lang;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.item.EasyRepairItems;

public class EasyRepairLanguageProvider {
    public static class ENUS extends LanguageProvider {
        public ENUS(PackOutput output) {
            super(output, TinkersEasyRepair.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH_I, "Repair Item Pouch I");
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH_II, "Repair Item Pouch II");
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH_III, "Repair Item Pouch III");
            add(TranslateKeys.POUCH_EMPTY_MESSAGE, "Repair failed. Please check that it contains the correct repair material for the tool you're using.");
            add(TranslateKeys.POUCH_CURIOS_SLOT_NAME, "Pouch");
            add(TranslateKeys.OPEN_POUCH_INVENTORY, "Open the inventory of the pouch");
            add(TranslateKeys.KEY_CATEGORY, "Tinkers' Easy Repair");
        }
    }

    public static class JAJP extends LanguageProvider {
        public JAJP(PackOutput output) {
            super(output, TinkersEasyRepair.MOD_ID, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH_I, "リペアアイテムポーチI");
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH_II, "リペアアイテムポーチII");
            addItem(EasyRepairItems.REPAIR_ITEM_POUCH_III, "リペアアイテムポーチIII");
            add(TranslateKeys.POUCH_EMPTY_MESSAGE, "修理に失敗しました。使用しているツールに対応した修理素材がポーチに入っているか確認してください。");
            add(TranslateKeys.POUCH_CURIOS_SLOT_NAME, "ポーチ");
            add(TranslateKeys.OPEN_POUCH_INVENTORY, "ポーチのインベントリを開く");
        }
    }
}
