package stan.ripto.easyrepair.util.repair;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import slimeknights.tconstruct.common.SoundUtils;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import stan.ripto.easyrepair.datagen.client.lang.TranslateKeys;
import stan.ripto.easyrepair.util.ServerPlayerGetter;

import java.util.*;

public class ToolRepairer {
    public static boolean tryRepair(ToolStack tool) {
        LocalPlayer localPlayer = Minecraft.getInstance().player;
        if (localPlayer == null) return false;

        ServerPlayer player = ServerPlayerGetter.getPlayer(localPlayer.getUUID());
        if (player == null) return false;

        Level lvl = player.level();
        if (!(lvl instanceof ServerLevel level)) return false;

        List<ItemStack> pouches = RepairHelper.findPouches(player);
        if (pouches.isEmpty()) return false;

        final List<ItemStack> repairItems = new ArrayList<>();
        for (ItemStack pouch : pouches) {
            pouch.getCapability(ForgeCapabilities.ITEM_HANDLER)
                    .ifPresent(inv -> repairItems.addAll(RepairHelper.getRepairItems(inv)));
        }

        if (repairItems.isEmpty()) {
            player.sendSystemMessage(Component.translatable(TranslateKeys.POUCH_EMPTY_MESSAGE));
            return false;
        }

        List<RepairItemData> repairItemData = RepairHelper.getRepairItemData(repairItems, level, tool);
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
