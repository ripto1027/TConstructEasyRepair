package stan.ripto.easyrepair.event;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.server.ServerStartingEvent;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepairItemHandler {
    private static final Set<Item> REPAIR_ITEMS = new HashSet<>();

    public static void onServerStarting(ServerStartingEvent event) {
        List<MaterialRecipe> recipes =
                event.getServer().getRecipeManager().getAllRecipesFor(TinkerRecipeTypes.MATERIAL.get());

        recipes.forEach(recipe -> {
            ItemStack[] stacks = recipe.getIngredient().getItems();

            for (ItemStack stack : stacks) {
                REPAIR_ITEMS.add(stack.getItem());
            }
        });
    }

    public static boolean isRepairItem(ItemStack stack) {
        return REPAIR_ITEMS.contains(stack.getItem());
    }
}
