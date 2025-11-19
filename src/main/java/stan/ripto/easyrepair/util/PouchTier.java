package stan.ripto.easyrepair.util;

import net.minecraft.resources.ResourceLocation;
import stan.ripto.easyrepair.TinkersEasyRepair;

public enum PouchTier {
    I(9, ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "textures/gui/repair_item_pouch_i_gui.png"), "repair_item_pouch_i"),
    II(18, ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "textures/gui/repair_item_pouch_ii_gui.png"), "repair_item_pouch_ii"),
    III(27, ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "textures/gui/repair_item_pouch_iii_gui.png"), "repair_item_pouch_iii");

    private final int size;
    private final ResourceLocation texture;
    private final String name;

    PouchTier(int size, ResourceLocation texture, String name) {
        this.size = size;
        this.texture = texture;
        this.name = name;
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

    public String getName() {
        return this.name;
    }
}
