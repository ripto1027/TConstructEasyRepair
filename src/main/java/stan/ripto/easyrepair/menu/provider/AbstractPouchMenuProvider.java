package stan.ripto.easyrepair.menu.provider;

import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("NullableProblems")
public abstract class AbstractPouchMenuProvider implements MenuProvider {
    protected final ItemStack pouch;

    public AbstractPouchMenuProvider(ItemStack pouch) {
        this.pouch = pouch;
    }

    @Override
    public Component getDisplayName() {
        return this.pouch.getHoverName();
    }

    @Override
    public abstract @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player);
}
