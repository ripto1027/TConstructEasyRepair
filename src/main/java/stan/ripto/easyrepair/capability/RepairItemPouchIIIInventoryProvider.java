package stan.ripto.easyrepair.capability;

import stan.ripto.easyrepair.util.PouchTier;

public class RepairItemPouchIIIInventoryProvider extends AbstractPouchInventoryProvider {
    public RepairItemPouchIIIInventoryProvider() {
        super(PouchTier.III.getSize());
    }
}
