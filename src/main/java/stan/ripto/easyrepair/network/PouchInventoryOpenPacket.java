package stan.ripto.easyrepair.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import stan.ripto.easyrepair.item.AbstractPouchItem;
import stan.ripto.easyrepair.util.repair.RepairHelper;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class PouchInventoryOpenPacket {
    public static void encode(PouchInventoryOpenPacket packet, FriendlyByteBuf buf) {}

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static PouchInventoryOpenPacket decode(FriendlyByteBuf buf) {
        return new PouchInventoryOpenPacket();
    }

    public static void handle(PouchInventoryOpenPacket packet, Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            ServerPlayer player = context.get().getSender();
            if (player == null) return;

            List<ItemStack> pouches = RepairHelper.findPouches(player);
            if (pouches.isEmpty()) return;

            ItemStack pouchStack = pouches.get(0);
            if (pouchStack.getItem() instanceof AbstractPouchItem<?> pouch) {
                pouch.openMenu(player.serverLevel(), player, pouchStack);
            }
        });
        context.get().setPacketHandled(true);
    }
}
