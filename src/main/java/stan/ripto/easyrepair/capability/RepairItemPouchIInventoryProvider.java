package stan.ripto.easyrepair.capability;

import stan.ripto.easyrepair.utils.PouchTier;

public class RepairItemPouchIInventoryProvider extends AbstractPouchInventoryProvider {
    public RepairItemPouchIInventoryProvider() {
        super(PouchTier.I.getSize());
    }
}
