package stan.ripto.easyrepair.menu;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.easyrepair.event.RepairItemHandler;
import stan.ripto.easyrepair.utils.EasyRepairUtils;
import stan.ripto.easyrepair.utils.PouchTier;
import stan.ripto.easyrepair.utils.repair.RepairHelper;

public abstract class AbstractPouchMenu extends AbstractContainerMenu {
    protected final ItemStack pouch;
    protected final PouchTier tier;
    protected final int playerInvMoveInt;

    public AbstractPouchMenu(
            @Nullable MenuType<?> menuType,
            int containerId,
            Inventory playerInventory,
            ItemStack pouch,
            PouchTier tier
    ) {
        super(menuType, containerId);
        this.pouch = pouch;
        this.tier = tier;
        this.playerInvMoveInt = this.tier.getRows() * 18;

        this.setupPouchSlots();
        this.addPlayerInventory(playerInventory);
    }

    private void setupPouchSlots() {
        IItemHandler pouchInventory = EasyRepairUtils.getPouchHandler(this.pouch);
        for (int i = 0; i < this.tier.getRows(); i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new SlotItemHandler(
                        pouchInventory,
                        j + i * 9,
                        8 + j * 18,
                        16 + i * 18
                ) {
                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return RepairItemHandler.isRepairItem(stack.getItem());
                    }
                });
            }
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for(int l = 0; l < 3; ++l) {
            for(int k = 0; k < 9; ++k) {
                this.addSlot(new Slot(
                        playerInventory,
                        k + l * 10,
                        8 + k * 18,
                        30 + this.playerInvMoveInt + l * 18
                ));
            }
        }

        for(int i1 = 0; i1 < 9; ++i1) {
            this.addSlot(new Slot(playerInventory, i1, 8 + i1 * 18, 88 + this.playerInvMoveInt));
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
            if (index < this.tier.getSize()) {
                if (!this.moveItemStackTo(stack1, this.tier.getSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack1, 0, this.tier.getSize(), false)) {
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

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean stillValid(Player player) {
        return !RepairHelper.findPouch(player).isEmpty();
    }
}
