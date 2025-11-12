package stan.ripto.easyrepair.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import stan.ripto.easyrepair.menu.RepairItemPouchIIIMenu;
import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchIIIScreen extends AbstractPouchScreen<RepairItemPouchIIIMenu> {
    public RepairItemPouchIIIScreen(RepairItemPouchIIIMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, PouchTier.III);
    }
}
