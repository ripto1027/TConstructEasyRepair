package stan.ripto.easyrepair.capability;

import stan.ripto.easyrepair.utils.PouchTier;

public class RepairItemPouchIIInventoryProvider extends AbstractPouchInventoryProvider {
    public RepairItemPouchIIInventoryProvider() {
        super(PouchTier.II.getSize());
    }
}
