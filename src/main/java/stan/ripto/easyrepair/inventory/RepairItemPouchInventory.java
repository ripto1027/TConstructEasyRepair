package stan.ripto.easyrepair.inventory;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import stan.ripto.easyrepair.util.EasyRepairUtils;

public class RepairItemPouchInventory extends ItemStackHandler {
    public RepairItemPouchInventory(int size) {
        super(size);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return EasyRepairUtils.isRepairMaterial(stack.getItem());
    }
}
