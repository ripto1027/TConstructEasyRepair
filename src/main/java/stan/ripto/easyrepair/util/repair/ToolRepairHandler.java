package stan.ripto.easyrepair.util.repair;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import slimeknights.tconstruct.common.SoundUtils;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import stan.ripto.easyrepair.datagen.client.lang.TranslateKeys;

import java.util.*;

public class ToolRepairHandler {
    public static void tryRepair(ServerPlayer player, ServerLevel level, ToolStack tool) {
        List<ItemStack> pouches = RepairHelper.findPouches(player);
        if (pouches.isEmpty()) return;

        List<ItemStack> repairItems = new ArrayList<>();
        for (ItemStack pouch : pouches) {
            pouch.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .ifPresent(inv -> repairItems.addAll(RepairHelper.getRepairItems(inv)));
        }

        if (repairItems.isEmpty()) {
            player.sendSystemMessage(Component.translatable(TranslateKeys.POUCH_EMPTY_MESSAGE));
            return;
        }

        List<RepairItemData> repairItemData = RepairHelper.getRepairItemData(repairItems, level, tool);
        if (repairItemData.isEmpty()) {
            player.sendSystemMessage(Component.translatable(TranslateKeys.POUCH_EMPTY_MESSAGE));
            return;
        }

        ItemStack stackCopy = ItemStack.EMPTY;
        for (RepairItemData data : repairItemData) {
            stackCopy = data.getRepairItemStack().copy();
            data.repair();
        }

        final Item repairItem = stackCopy.getItem();

        SoundUtils.playSoundForAll(
                player,
                Sounds.SAW.getSound(),
                0.8f,
                0.8f + 0.4f * player.getRandom().nextFloat()
        );

        if (repairItems.stream().noneMatch(stack -> stack.is(repairItem))) {
            player.sendSystemMessage(Component.translatable(TranslateKeys.REPAIR_MATERIAL_EMPTY_MESSAGE));
        }
    }
}
