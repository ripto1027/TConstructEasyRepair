package stan.ripto.easyrepair.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchIMenu extends AbstractPouchMenu {
    public RepairItemPouchIMenu(int containerId, Inventory playerInventory, ItemStack pouch) {
        super(EasyRepairMenus.REPAIR_ITEM_POUCH_I.get(), containerId, playerInventory, pouch, PouchTier.I);
    }

    public RepairItemPouchIMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, buf.readItem());
    }
}
