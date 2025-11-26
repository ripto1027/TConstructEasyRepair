package stan.ripto.easyrepair.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.Event;

public class BroadcastBreakEvent extends Event {
    private final Player player;
    private final Level level;
    private final ItemStack stack;

    public BroadcastBreakEvent(Player player, Level level, ItemStack stack) {
        this.player = player;
        this.level = level;
        this.stack = stack;
    }

    public Player getPlayer() {
        return this.player;
    }

    public Level getLevel() {
        return this.level;
    }

    public ItemStack getStack() {
        return this.stack;
    }
}
