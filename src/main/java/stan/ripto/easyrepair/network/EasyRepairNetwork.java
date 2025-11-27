package stan.ripto.easyrepair.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import stan.ripto.easyrepair.TinkersEasyRepair;

public class EasyRepairNetwork {
    public static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals
    );

    public static void register() {
        int id = -1;

        CHANNEL.registerMessage(
                ++id,
                PouchInventoryOpenPacket.class,
                PouchInventoryOpenPacket::encode,
                PouchInventoryOpenPacket::decode,
                PouchInventoryOpenPacket::handle
        );
    }
}
