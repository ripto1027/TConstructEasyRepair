package stan.ripto.easyrepair.utils.repair;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import slimeknights.tconstruct.common.SoundUtils;
import slimeknights.tconstruct.common.Sounds;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import stan.ripto.easyrepair.datagen.client.lang.TranslateKeys;
import stan.ripto.easyrepair.utils.EasyRepairUtils;

import java.util.*;

public class ToolRepairHandler {
    public static void tryRepair(Player player, Level level, ToolStack tool) {
        List<ItemStack> pouch = RepairHelper.findPouch(player);
        if (pouch.isEmpty()) return;

        List<ItemStack> rItems = new ArrayList<>();
        for (ItemStack stack : pouch) {
            IItemHandler handler = EasyRepairUtils.getPouchHandler(stack);
            rItems.addAll(RepairHelper.getRepairItems(handler));
        }

        if (rItems.isEmpty()) {
            player.sendSystemMessage(Component.translatable(TranslateKeys.POUCH_EMPTY_MESSAGE));
        }

        List<RepairItemData> repairItemData = RepairHelper.getRepairItemData(rItems, level, tool);
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
    }
}
