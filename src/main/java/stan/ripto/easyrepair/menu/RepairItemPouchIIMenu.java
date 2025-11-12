package stan.ripto.easyrepair.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchIIMenu extends AbstractPouchMenu {
    public RepairItemPouchIIMenu(int containerId, Inventory playerInventory, ItemStack pouch) {
        super(EasyRepairMenus.REPAIR_ITEM_POUCH_II.get(), containerId, playerInventory, pouch, PouchTier.II);
    }

    public RepairItemPouchIIMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, buf.readItem());
    }
}
