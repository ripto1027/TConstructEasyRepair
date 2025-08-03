package stan.ripto.easyrepair.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import stan.ripto.easyrepair.event.RepairItemHandler;

public class RepairItemPouchMenu extends AbstractContainerMenu {
    private final ItemStack pouch;

    public RepairItemPouchMenu(int containerId, Inventory playerInventory, ItemStack pouch) {
        super(EasyRepairMenus.REPAIR_ITEM_POUCH.get(), containerId);
        this.pouch = pouch;
        IItemHandler pouchInventory = pouch.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElseThrow(() -> new IllegalStateException("Capability not found on pouch item."));

        for (int i = 0; i < 9; i++) {
            this.addSlot(new SlotItemHandler(pouchInventory, i, 8 + i * 18, 20) {
                @Override
                public boolean mayPlace(@NotNull ItemStack stack) {
                    return RepairItemHandler.isRepairItem(stack);
                }
            });
        }
        addPlayerInventory(playerInventory);
    }

    public RepairItemPouchMenu(int containerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(containerId, playerInventory, buf.readItem());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 51));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 109));
        }
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack1 = slot.getItem();
            stack = stack1.copy();
            if (index < 9) {
                if (!this.moveItemStackTo(stack1, 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack1, 0, 9, false)) {
                return ItemStack.EMPTY;
            }

            if (stack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return stack;
    }

    @Override
    public boolean stillValid(Player player) {
        return player.getInventory().findSlotMatchingItem(this.pouch) != -1;
    }
}
