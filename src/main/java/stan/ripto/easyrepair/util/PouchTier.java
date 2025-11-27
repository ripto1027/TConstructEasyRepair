package stan.ripto.easyrepair.util;

import net.minecraft.resources.ResourceLocation;
import stan.ripto.easyrepair.TinkersEasyRepair;

public enum PouchTier {
    I(9, "repair_item_pouch_i"),
    II(18, "repair_item_pouch_ii"),
    III(27, "repair_item_pouch_iii");

    private final int size;
    private final String name;

    PouchTier(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return this.size;
    }

    public int getRows() {
        return this.size / 9;
    }

    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "textures/gui/" + this.name + ".png");
    }

    public String getName() {
        return this.name;
    }
}
