package stan.ripto.easyrepair.util.repair;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import slimeknights.tconstruct.common.SoundUtils;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import stan.ripto.easyrepair.datagen.client.lang.TranslateKeys;
import stan.ripto.easyrepair.util.ServerPlayerGetter;

import java.util.*;

public class ToolRepairHandler {
    public static boolean tryRepair(ToolStack toolStack) {
        ItemStack tool = toolStack.createStack();
        CompoundTag tag = tool.getOrCreateTag();
        if (!tag.hasUUID("owner")) return false;

        ServerPlayer player = ServerPlayerGetter.getPlayer(tag.getUUID("owner"));
        if (player == null) return false;

        List<ItemStack> pouches = RepairHelper.findPouches(player);
        if (pouches.isEmpty()) return false;

        List<ItemStack> repairItems = new ArrayList<>();
        for (ItemStack pouch : pouches) {
            pouch.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .ifPresent(inv -> repairItems.addAll(RepairHelper.getRepairItems(inv)));
        }

        if (repairItems.isEmpty()) {
            player.sendSystemMessage(Component.translatable(TranslateKeys.POUCH_EMPTY_MESSAGE));
            return false;
        }

        List<RepairItemData> repairItemData = RepairHelper.getRepairItemData(repairItems, player.serverLevel(), toolStack);
        if (repairItemData.isEmpty()) {
            player.sendSystemMessage(Component.translatable(TranslateKeys.POUCH_EMPTY_MESSAGE));
            return false;
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

        return true;
    }
}