package stan.ripto.easyrepair.item;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import stan.ripto.easyrepair.datagen.client.lang.TranslateKeys;
import stan.ripto.easyrepair.menu.provider.AbstractPouchMenuProvider;

import java.util.List;
import java.util.function.Function;

public class AbstractPouchItem<T extends AbstractPouchMenuProvider> extends Item {
    private final Function<ItemStack, T> function;
    private final int size;

    public AbstractPouchItem(Properties properties, Function<ItemStack, T> function, int size) {
        super(properties);
        this.function = function;
        this.size = size;
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

    @SuppressWarnings("NullableProblems")
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltips, TooltipFlag isAdvanced) {
        super.appendHoverText(stack, level, tooltips, isAdvanced);

        tooltips.add(Component.translatable(TranslateKeys.FEATURE_DESCRIPTION));
        tooltips.add(Component.translatable(TranslateKeys.SIZE_DESCRIPTION, this.size));
    }
}
