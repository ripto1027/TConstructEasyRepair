package stan.ripto.easyrepair.item;

import stan.ripto.easyrepair.menu.provider.RepairItemPouchIIMenuProvider;
import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchII extends AbstractPouchItem<RepairItemPouchIIMenuProvider> {
    public RepairItemPouchII() {
        super(new Properties().stacksTo(1), RepairItemPouchIIMenuProvider::new, PouchTier.II.getSize());
    }
}
