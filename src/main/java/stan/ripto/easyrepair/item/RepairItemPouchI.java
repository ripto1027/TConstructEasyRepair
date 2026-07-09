package stan.ripto.easyrepair.item;

import stan.ripto.easyrepair.menu.provider.RepairItemPouchIMenuProvider;
import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchI extends AbstractPouchItem<RepairItemPouchIMenuProvider> {
    public RepairItemPouchI() {
        super(new Properties().stacksTo(1), RepairItemPouchIMenuProvider::new, PouchTier.I.getSize());
    }
}
