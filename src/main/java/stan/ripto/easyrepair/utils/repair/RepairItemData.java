package stan.ripto.easyrepair.utils.repair;

import net.minecraft.world.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

public class RepairItemData {
    private final ItemStack repairItemStack;
    private final ToolStack tool;
    private final float repairPerItem;
    private final int shrinkAmount;
    private final int repairAmount;

    public RepairItemData(ItemStack repairItemStack, float repairPerItem, ToolStack tool, int currentDamage) {
        this.repairItemStack = repairItemStack;
        this.tool = tool;
        this.repairPerItem = getRepairPerItem(tool, repairPerItem);
        int needed = (int) Math.ceil(currentDamage / this.repairPerItem);
        int stackCount = repairItemStack.getCount();

        if (needed > stackCount) {
            this.shrinkAmount = stackCount;
            this.repairAmount = (int) Math.ceil(stackCount * this.repairPerItem);
        } else {
            this.shrinkAmount = needed;
            this.repairAmount = (int) Math.ceil(needed * this.repairPerItem);
        }
    }

    public static float getRepairPerItem(ToolStack tool, float repairPerItem) {
        for (ModifierEntry entry : tool.getModifierList()) {
            repairPerItem = entry.getHook(ModifierHooks.REPAIR_FACTOR).getRepairFactor(tool, entry, repairPerItem);
        }
        return repairPerItem;
    }

    public void repair() {
        if (repairPerItem > 0) {
            ToolDamageUtil.repair(this.tool, this.repairAmount);
            repairItemStack.shrink(shrinkAmount);
        }
    }

    public int getRepairAmount() {
        return this.repairAmount;
    }
}
