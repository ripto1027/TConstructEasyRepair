package stan.ripto.easyrepair.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.UUID;

public class ServerPlayerGetter {
    private static MinecraftServer server;

    public static void setServer(MinecraftServer pServer) {
        server = pServer;
    }

    public static ServerPlayer getPlayer(UUID uuid) {
        return server.getPlayerList().getPlayer(uuid);
    }
}
