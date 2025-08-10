package stan.ripto.easyrepair.utils.repair;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import slimeknights.tconstruct.common.SoundUtils;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import stan.ripto.easyrepair.utils.EasyRepairUtils;

import java.util.*;

public class ToolRepairHandler {
    public static final String POUCH_EMPTY_MESSAGE = "message.easyrepair.pouch_empty";

    public static void tryRepair(Player player, Level level, ToolStack tool) {
        int index = RepairHelper.findPouch(player);
        if (index == -1) return;

        ItemStack pouch = player.getInventory().getItem(index);
        IItemHandler handler = EasyRepairUtils.getPouchHandler(pouch);
        List<ItemStack> items = RepairHelper.getRepairItems(handler);
        if (items.isEmpty()) return;

        List<RepairItemData> repairItemData = RepairHelper.getRepairItemData(items, level, tool);
        if (repairItemData.isEmpty()) return;

        for (RepairItemData data : repairItemData) {
            data.repair();
        }

        SoundUtils.playSoundForAll(
                player,
                Sounds.SAW.getSound(),
                0.8f,
                0.8f + 0.4f * player.getRandom().nextFloat()
        );

        if (RepairHelper.getRepairItems(handler).isEmpty()) {
            player.sendSystemMessage(Component.translatable(POUCH_EMPTY_MESSAGE));
        }
    }
}
