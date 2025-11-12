package stan.ripto.easyrepair.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import stan.ripto.easyrepair.menu.RepairItemPouchIMenu;
import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchIScreen extends AbstractPouchScreen<RepairItemPouchIMenu> {
    public RepairItemPouchIScreen(RepairItemPouchIMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, PouchTier.I);
    }
}
