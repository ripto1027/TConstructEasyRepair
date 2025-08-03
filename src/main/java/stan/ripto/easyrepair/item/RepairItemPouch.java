package stan.ripto.easyrepair.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import stan.ripto.easyrepair.screen.RepairItemPouchMenuProvider;

public class RepairItemPouch extends Item {
    public RepairItemPouch() {
        super(new Item.Properties().stacksTo(1));
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (usedHand == InteractionHand.OFF_HAND)
            return InteractionResultHolder.fail(player.getItemInHand(usedHand));

        if (!level.isClientSide && player instanceof ServerPlayer splayer) {
            ItemStack stack = player.getItemInHand(usedHand);
            RepairItemPouchMenuProvider menuProvider = new RepairItemPouchMenuProvider(stack);

            NetworkHooks.openScreen(splayer, menuProvider, buf -> buf.writeItem(stack));
        }
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide);
    }
}
