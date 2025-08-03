package stan.ripto.easyrepair.event;

import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.items.IItemHandler;
import org.apache.commons.lang3.tuple.Triple;
import slimeknights.tconstruct.common.SoundUtils;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipe;
import slimeknights.tconstruct.library.tools.definition.module.material.MaterialRepairToolHook;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import slimeknights.tconstruct.library.tools.part.IRepairKitItem;
import stan.ripto.easyrepair.item.EasyRepairItems;

import java.util.*;

public class EasyReairHandler {
    public static final String POUCH_EMPTY_MESSAGE = "message.easyrepair.pouch_empty";

    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;
        Level level = player.level();
        if (!level.isClientSide) {
            ItemStack mainHandItem = player.getMainHandItem();
            if (ToolDamageUtil.isBroken(mainHandItem)) {
                ToolStack tool = ToolStack.from(mainHandItem);
                tryRepair(player, level, tool);
            }
        }
    }

    private static void tryRepair(Player player, Level level, ToolStack tool) {
        int index = findPouch(player);
        if (index == -1) return;

        ItemStack pouch = player.getInventory().getItem(index);
        IItemHandler handler = getHandler(pouch);
        List<ItemStack> items = getRepairItems(handler);
        if (items.isEmpty()) return;

        Queue<Triple<ItemStack, MaterialRecipe, MaterialId>> repairableItems = new ArrayDeque<>();
        for (ItemStack item : items) {
            Optional<MaterialRecipe> recipes = level.getRecipeManager().getRecipeFor(TinkerRecipeTypes.MATERIAL.get(), () -> item, level);
            if (recipes.isEmpty()) return;

            MaterialRecipe recipe = recipes.get();
            MaterialId id = recipe.getMaterial().getId();
            if (MaterialRepairToolHook.canRepairWith(tool, id)) {
                repairableItems.add(Triple.of(item, recipe, id));
            }
        }
        if (repairableItems.isEmpty()) return;

        while (tool.getDamage() != 0 && !repairableItems.isEmpty()) {
            Triple<ItemStack, MaterialRecipe, MaterialId> repairItemData = repairableItems.poll();
            ItemStack repairItem = repairItemData.getLeft();
            MaterialRecipe recipe = repairItemData.getMiddle();
            MaterialId id = repairItemData.getRight();

            float base = MaterialRepairToolHook.repairAmount(tool, id);
            float repairPerItem = repairItem.getItem() instanceof IRepairKitItem kit ? base * kit.getRepairAmount() / 3f : recipe.scaleRepair(base);
            if (repairPerItem <= 0) return;

            for (ModifierEntry entry : tool.getModifierList()) {
                repairPerItem = entry.getHook(ModifierHooks.REPAIR_FACTOR).getRepairFactor(tool, entry, repairPerItem);
                if (repairPerItem <= 0) return;
            }

            int needed = (int) Math.ceil(tool.getDamage() / repairPerItem);
            int repairItemCount = repairItem.getCount();

            if (needed > repairItemCount) {
                ToolDamageUtil.repair(tool, (int) Math.ceil(repairItemCount * repairPerItem));
                repairItem.shrink(repairItemCount);
            } else {
                ToolDamageUtil.repair(tool, tool.getDamage());
                repairItem.shrink(needed);
            }
        }

        SoundUtils.playSoundForAll(player, Sounds.SAW.getSound(), 0.8f, 0.8f + 0.4f * player.getRandom().nextFloat());

        if (getRepairItems(handler).isEmpty()) {
            player.sendSystemMessage(Component.translatable(POUCH_EMPTY_MESSAGE));
        }
    }

    private static int findPouch(Player player) {
        NonNullList<ItemStack> items = player.getInventory().items;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).is(EasyRepairItems.REPAIR_ITEM_POUCH.get())) {
                return i;
            }
        }
        return -1;
    }

    private static IItemHandler getHandler(ItemStack pouch) {
        return pouch.getCapability(ForgeCapabilities.ITEM_HANDLER)
                .orElseThrow(() -> new IllegalStateException("Capability not found on pouch item."));
    }

    private static List<ItemStack> getRepairItems(IItemHandler handler) {
        List<ItemStack> list = new ArrayList<>();
        for (int i = handler.getSlots() - 1; i >= 0; i--) {
            if (!handler.getStackInSlot(i).isEmpty()) {
                list.add(handler.getStackInSlot(i));
            }
        }
        return list.isEmpty() ? Collections.emptyList() : list;
    }
}
