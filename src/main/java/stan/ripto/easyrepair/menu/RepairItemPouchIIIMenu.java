package stan.ripto.easyrepair.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import stan.ripto.easyrepair.utils.PouchTier;

public class RepairItemPouchIIIMenu extends AbstractPouchMenu {
    public RepairItemPouchIIIMenu(int containerId, Inventory playerInventory, ItemStack pouch) {
        super(EasyRepairMenus.REPAIR_ITEM_POUCH_III.get(), containerId, playerInventory, pouch, PouchTier.III);
    }

    public RepairItemPouchIIIMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, buf.readItem());
    }
}
