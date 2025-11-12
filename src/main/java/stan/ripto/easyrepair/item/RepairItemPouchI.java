package stan.ripto.easyrepair.item;

import stan.ripto.easyrepair.menu.provider.RepairItemPouchIMenuProvider;

public class RepairItemPouchI extends AbstractPouchItem<RepairItemPouchIMenuProvider> {
    public RepairItemPouchI() {
        super(new Properties().stacksTo(1), RepairItemPouchIMenuProvider::new);
    }
}
