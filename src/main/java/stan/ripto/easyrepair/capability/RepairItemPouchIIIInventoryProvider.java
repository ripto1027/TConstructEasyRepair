package stan.ripto.easyrepair.capability;

import stan.ripto.easyrepair.utils.PouchTier;

public class RepairItemPouchIIIInventoryProvider extends AbstractPouchInventoryProvider {
    public RepairItemPouchIIIInventoryProvider() {
        super(PouchTier.III.getSize());
    }
}
