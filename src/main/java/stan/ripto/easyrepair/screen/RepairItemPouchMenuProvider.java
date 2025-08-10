package stan.ripto.easyrepair.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("ClassCanBeRecord")
public class RepairItemPouchMenuProvider implements MenuProvider {
    private final ItemStack pouch;

    public RepairItemPouchMenuProvider(ItemStack pouch) {
        this.pouch = pouch;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Component getDisplayName() {
        return this.pouch.getHoverName();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new RepairItemPouchMenu(containerId, playerInventory, this.pouch);
    }
}
