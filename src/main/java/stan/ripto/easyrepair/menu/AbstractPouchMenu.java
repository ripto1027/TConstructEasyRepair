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
import stan.ripto.easyrepair.util.EasyRepairUtils;
import stan.ripto.easyrepair.util.PouchTier;

public abstract class AbstractPouchMenu extends AbstractContainerMenu {
    private static final int SLOT_COLUMN_COUNT = 9;
    private static final int PLAYER_INV_ROW_COUNT = 3;
    private static final int SLOT_LENGTH = 18;
    private static final int INVENTORY_X_BASE = 8;
    private static final int POUCH_SLOTS_Y_BASE = 16;
    private static final int PLAYER_INV_Y_BASE = 30;
    private static final int HOTBAR_Y_BASE = 88;

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
        this.playerInvMoveInt = this.tier.getRows() * SLOT_LENGTH;

        this.setupPouchSlots();
        this.addPlayerInventory(playerInventory);
    }

    private void setupPouchSlots() {
        IItemHandler pouchInventory = EasyRepairUtils.getPouchHandler(this.pouch);
        for (int r = 0; r < this.tier.getRows(); ++r) {
            for (int c = 0; c < SLOT_COLUMN_COUNT; ++c) {
                this.addSlot(new SlotItemHandler(
                        pouchInventory,
                        c + r * SLOT_COLUMN_COUNT,
                        INVENTORY_X_BASE + c * SLOT_LENGTH,
                        POUCH_SLOTS_Y_BASE + r * SLOT_LENGTH
                ) {
                    @Override
                    public boolean mayPlace(@NotNull ItemStack stack) {
                        return EasyRepairUtils.isRepairMaterial(stack.getItem());
                    }
                });
            }
        }
    }

    private void addPlayerInventory(Inventory playerInventory) {
        for(int r = 0; r < PLAYER_INV_ROW_COUNT; ++r) {
            for(int c = 0; c < SLOT_COLUMN_COUNT; ++c) {
                this.addSlot(new Slot(
                        playerInventory,
                        c + (r + 1) * SLOT_COLUMN_COUNT,
                        INVENTORY_X_BASE + c * SLOT_LENGTH,
                        PLAYER_INV_Y_BASE + this.playerInvMoveInt + r * SLOT_LENGTH
                ));
            }
        }

        for(int c = 0; c < SLOT_COLUMN_COUNT; ++c) {
            this.addSlot(new Slot(
                    playerInventory,
                    c,
                    INVENTORY_X_BASE + c * SLOT_LENGTH,
                    HOTBAR_Y_BASE + this.playerInvMoveInt
            ));
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

    @Override
    public boolean stillValid(Player player) {
        return player.getInventory().contains(this.pouch);
    }
}
