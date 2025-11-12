package stan.ripto.easyrepair.menu.provider;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import stan.ripto.easyrepair.menu.RepairItemPouchIIIMenu;

public class RepairItemPouchIIIMenuProvider extends AbstractPouchMenuProvider {
    public RepairItemPouchIIIMenuProvider(ItemStack pouch) {
        super(pouch);
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new RepairItemPouchIIIMenu(containerId, playerInventory, this.pouch);
    }
}
