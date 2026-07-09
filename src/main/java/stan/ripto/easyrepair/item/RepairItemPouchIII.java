package stan.ripto.easyrepair.item;

import stan.ripto.easyrepair.menu.provider.RepairItemPouchIIIMenuProvider;
import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchIII extends AbstractPouchItem<RepairItemPouchIIIMenuProvider> {
    public RepairItemPouchIII() {
        super(new Properties().stacksTo(1), RepairItemPouchIIIMenuProvider::new, PouchTier.III.getSize());
    }
}
