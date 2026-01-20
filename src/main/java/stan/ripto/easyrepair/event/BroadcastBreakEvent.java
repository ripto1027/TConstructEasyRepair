package stan.ripto.easyrepair.event;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.Event;

public class BroadcastBreakEvent extends Event {
    private final ServerPlayer player;
    private final ServerLevel level;
    private final ItemStack stack;

    public BroadcastBreakEvent(ServerPlayer player, ServerLevel level, ItemStack stack) {
        this.player = player;
        this.level = level;
        this.stack = stack;
    }

    public ServerPlayer getPlayer() {
        return this.player;
    }

    public ServerLevel getLevel() {
        return this.level;
    }

    public ItemStack getStack() {
        return this.stack;
    }
}
