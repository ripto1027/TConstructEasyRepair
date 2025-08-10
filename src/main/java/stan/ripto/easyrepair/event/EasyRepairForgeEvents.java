package stan.ripto.easyrepair.event;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.capability.RepairItemPouchInventoryProvider;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.utils.AfterBreakHandler;

@Mod.EventBusSubscriber(modid = TConstructEasyRepair.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EasyRepairForgeEvents {
    @SubscribeEvent
    public static void onPlayerDestroyItem(PlayerDestroyItemEvent event) {
        Player player = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide) {
            InteractionHand hand = event.getHand();
            ItemStack stack = hand == null ? player.getMainHandItem() : player.getItemInHand(hand);
            if (ToolDamageUtil.isBroken(stack)) {
                ToolStack tool = ToolStack.from(stack);
                AfterBreakHandler.tryRepair(player, level, tool);
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        if (event.getObject().getItem() == EasyRepairItems.REPAIR_ITEM_POUCH.get()) {
            event.addCapability(
                    ResourceLocation.fromNamespaceAndPath(TConstructEasyRepair.MOD_ID, "inventory"),
                    new RepairItemPouchInventoryProvider()
            );
        }
    }
}
