package stan.ripto.easyrepair.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import stan.ripto.easyrepair.menu.provider.AbstractPouchMenuProvider;

import java.util.function.Function;

public class AbstractPouchItem<T extends AbstractPouchMenuProvider> extends Item {
    private final Function<ItemStack, T> function;

    public AbstractPouchItem(Properties properties, Function<ItemStack, T> function) {
        super(properties);
        this.function = function;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        if (usedHand == InteractionHand.OFF_HAND) return InteractionResultHolder.fail(player.getItemInHand(usedHand));
        ItemStack pouch = player.getItemInHand(usedHand);
        openMenu(level, player, pouch);
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide());
    }

    public void openMenu(Level level, Player player, ItemStack pouch) {
        if (!level.isClientSide() && player instanceof ServerPlayer serverPlayer) {
            T menuProvider = this.function.apply(pouch);
            NetworkHooks.openScreen(serverPlayer, menuProvider, buf -> buf.writeItem(pouch));
        }
    }
}
