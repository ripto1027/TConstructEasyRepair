package stan.ripto.easyrepair.utils.repair;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipe;
import slimeknights.tconstruct.library.tools.definition.module.material.MaterialRepairToolHook;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.part.IRepairKitItem;
import stan.ripto.easyrepair.item.EasyRepairItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepairHelper {
    public static int findPouch(Player player) {
        NonNullList<ItemStack> items = player.getInventory().items;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).is(EasyRepairItems.REPAIR_ITEM_POUCH.get())) {
                return i;
            }
        }
        return -1;
    }

    public static List<ItemStack> getRepairItems(IItemHandler handler) {
        List<ItemStack> list = new ArrayList<>();
        for (int i = handler.getSlots() - 1; i >= 0; i--) {
            if (!handler.getStackInSlot(i).isEmpty()) {
                list.add(handler.getStackInSlot(i));
            }
        }
        return list;
    }

    public static List<RepairItemData> getRepairItemData(List<ItemStack> stacks, Level level, ToolStack tool) {
        List<RepairItemData> repairItemData = new ArrayList<>();
        int repairAmount = tool.getDamage();
        for (ItemStack stack : stacks) {

            if (stack.getItem() instanceof IRepairKitItem kit) {
                MaterialId id = kit.getMaterial(stack).getId();

                if (MaterialRepairToolHook.canRepairWith(tool, id)) {
                    float base = MaterialRepairToolHook.repairAmount(tool, id);
                    float repairPerItem = base * kit.getRepairAmount() / MaterialRecipe.INGOTS_PER_REPAIR;
                    RepairItemData data = new RepairItemData(stack, repairPerItem, tool, repairAmount);
                    repairItemData.add(data);
                    repairAmount -= data.getRepairAmount();
                    if (repairAmount <= 0) break;
                }

            } else {
                Optional<MaterialRecipe> recipes =
                        level.getRecipeManager().getRecipeFor(TinkerRecipeTypes.MATERIAL.get(), () -> stack, level);
                if (recipes.isEmpty()) break;

                MaterialRecipe recipe = recipes.get();
                MaterialId id = recipe.getMaterial().getId();

                if (MaterialRepairToolHook.canRepairWith(tool, id)) {
                    float base = MaterialRepairToolHook.repairAmount(tool, id);
                    float repairPerItem = recipe.scaleRepair(base);
                    RepairItemData data = new RepairItemData(stack, repairPerItem, tool, repairAmount);
                    repairItemData.add(data);
                    repairAmount -= data.getRepairAmount();
                    if (repairAmount <= 0) break;
                }
            }
        }
        return repairItemData;
    }
}
