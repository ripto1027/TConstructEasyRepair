package stan.ripto.easyrepair.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;
import stan.ripto.easyrepair.item.RepairItemPouchI;
import stan.ripto.easyrepair.item.RepairItemPouchII;
import stan.ripto.easyrepair.item.RepairItemPouchIII;
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

            Level level = player.level();

            List<ItemStack> pouches = RepairHelper.findPouch(player);
            if (pouches.isEmpty()) return;

            ItemStack pouchStack = pouches.get(0);
            if (pouchStack == null || pouchStack.isEmpty()) return;

            Item pouch = pouchStack.getItem();
            if (pouch.equals(Items.AIR)) return;

            if (pouch instanceof RepairItemPouchI pouchI) {
                pouchI.openMenu(level, player, pouchStack);
            } else if (pouch instanceof RepairItemPouchII pouchII) {
                pouchII.openMenu(level, player, pouchStack);
            } else if (pouch instanceof RepairItemPouchIII pouchIII) {
                pouchIII.openMenu(level, player, pouchStack);
            }
        });
    }
}
