package stan.ripto.easyrepair.screen;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import stan.ripto.easyrepair.menu.RepairItemPouchIIMenu;
import stan.ripto.easyrepair.utils.PouchTier;

public class RepairItemPouchIIScreen extends AbstractPouchScreen<RepairItemPouchIIMenu> {
    public RepairItemPouchIIScreen(RepairItemPouchIIMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, PouchTier.II);
    }
}
