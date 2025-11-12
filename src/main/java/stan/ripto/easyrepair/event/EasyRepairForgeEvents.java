package stan.ripto.easyrepair.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.capability.RepairItemPouchIIIInventoryProvider;
import stan.ripto.easyrepair.capability.RepairItemPouchIIInventoryProvider;
import stan.ripto.easyrepair.capability.RepairItemPouchIInventoryProvider;
import stan.ripto.easyrepair.item.EasyRepairItems;
import stan.ripto.easyrepair.key.EasyRepairKeyMappings;
import stan.ripto.easyrepair.network.EasyRepairNetwork;
import stan.ripto.easyrepair.network.PouchInventoryOpenPacket;
import stan.ripto.easyrepair.util.repair.ToolRepairHandler;

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
                ToolRepairHandler.tryRepair(player, level, tool);
            }
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<ItemStack> event) {
        ItemStack stack = event.getObject();
        if (stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_I.get())) {
            event.addCapability(
                    ResourceLocation.fromNamespaceAndPath(TConstructEasyRepair.MOD_ID, "inventory"),
                    new RepairItemPouchIInventoryProvider()
            );

        } else if (stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_II.get())) {
            event.addCapability(
                    ResourceLocation.fromNamespaceAndPath(TConstructEasyRepair.MOD_ID, "inventory"),
                    new RepairItemPouchIIInventoryProvider()
            );

        } else if (stack.is(EasyRepairItems.REPAIR_ITEM_POUCH_III.get())) {
            event.addCapability(
                    ResourceLocation.fromNamespaceAndPath(TConstructEasyRepair.MOD_ID, "inventory"),
                    new RepairItemPouchIIIInventoryProvider()
            );
        }
    }

    @SuppressWarnings("InstantiationOfUtilityClass")
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase.equals(TickEvent.Phase.END)) {
            while (EasyRepairKeyMappings.OPEN_POUCH_INVENTORY.consumeClick()) {
                if (Minecraft.getInstance().screen instanceof InventoryScreen) return;
                EasyRepairNetwork.CHANNEL.sendToServer(new PouchInventoryOpenPacket());
            }
        }
    }
}
