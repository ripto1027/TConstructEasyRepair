package stan.ripto.easyrepair.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.item.EasyRepairItems;

import java.util.Objects;

public enum PouchTier {
    I(
            0,
            9,
            Items.AIR,
            EasyRepairItems.REPAIR_ITEM_POUCH_I
    ),
    II(
            1,
            18,
            Items.GOLD_INGOT,
            EasyRepairItems.REPAIR_ITEM_POUCH_II
    ),
    III(
            2,
            27,
            Items.DIAMOND,
            EasyRepairItems.REPAIR_ITEM_POUCH_III
    );

    private final int index;
    private final int size;
    private final Item around;
    private final RegistryObject<Item> pouchRegistry;

    PouchTier(int index, int size, Item around, RegistryObject<Item> pouchRegistry) {
        this.index = index;
        this.size = size;
        this.around = around;
        this.pouchRegistry = pouchRegistry;
    }

    public static PouchTier get(String name) {
        for (PouchTier tier : PouchTier.values()) {
            if (tier.getName().equals(name)) return tier;
        }
        return PouchTier.I;
    }

    public int getSize() {
        return this.size;
    }

    public int getRows() {
        return this.size / 9;
    }

    public ResourceLocation getTexture() {
        return ResourceLocation.fromNamespaceAndPath(
                TinkersEasyRepair.MOD_ID,
                "textures/gui/" + this.getName() + ".png"
        );
    }

    public String getName() {
        return Objects.requireNonNull(this.pouchRegistry.getId()).getPath();
    }

    public Ingredient getCenterIngredient() {
        if (this.index != 0) {
            return Ingredient.of(PouchTier.values()[this.index - 1].getPouch());
        }
        return Ingredient.EMPTY;
    }

    public Item getAround() {
        return this.around;
    }

    public Ingredient getAroundIngredient() {
        return Ingredient.of(this.around);
    }

    public Item getPouch() {
        return this.pouchRegistry.get();
    }
}
