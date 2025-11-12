package stan.ripto.easyrepair.util;

import net.minecraft.resources.ResourceLocation;
import stan.ripto.easyrepair.TinkersEasyRepair;

public enum PouchTier {
    I(9, ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "textures/gui/repair_item_pouch_i_gui.png")),
    II(18, ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "textures/gui/repair_item_pouch_ii_gui.png")),
    III(27, ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "textures/gui/repair_item_pouch_iii_gui.png"));

    private final int size;
    private final ResourceLocation texture;

    PouchTier(int size, ResourceLocation texture) {
        this.size = size;
        this.texture = texture;
    }

    public int getSize() {
        return this.size;
    }

    public int getRows() {
        return this.size / 9;
    }

    public ResourceLocation getTexture() {
        return this.texture;
    }
}
