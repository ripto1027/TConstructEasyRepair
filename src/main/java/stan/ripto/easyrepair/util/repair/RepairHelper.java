package stan.ripto.easyrepair.util.repair;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.IItemHandler;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipe;
import slimeknights.tconstruct.library.tools.definition.module.material.MaterialRepairToolHook;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.part.IRepairKitItem;
import stan.ripto.easyrepair.item.EasyRepairItems;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepairHelper {
    private static final String CURIOS_MOD_ID = "curios";

    public static List<ItemStack> findPouches(Player player) {
        List<ItemStack> pouches = new ArrayList<>();

        if (ModList.get().isLoaded(CURIOS_MOD_ID)) {
            CuriosApi.getCuriosInventory(player).ifPresent(curioInv ->
                    pouches.addAll(curioInv.findCurios("pouch").stream().map(SlotResult::stack).toList()));
        }

        pouches.addAll(player.getInventory().items.stream().filter(RepairHelper::isPouch).toList());

        return pouches;
    }

    private static boolean isPouch(ItemStack stack) {
        return !stack.isEmpty() && (stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_I.get()) || stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_II.get()) || stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_III.get()));
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
                if (recipes.isEmpty()) continue;

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
