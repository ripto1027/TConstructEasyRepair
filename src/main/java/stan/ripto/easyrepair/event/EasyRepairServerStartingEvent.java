package stan.ripto.easyrepair.event;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.event.server.ServerStartingEvent;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipe;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EasyRepairServerStartingEvent {
    private static final Set<Item> REPAIR_ITEMS = new HashSet<>();

    public static void onServerStarting(ServerStartingEvent event) {
        REPAIR_ITEMS.clear();
        RecipeManager manager = event.getServer().getRecipeManager();
        List<MaterialRecipe> materialRecipes = manager.getAllRecipesFor(TinkerRecipeTypes.MATERIAL.get());

        for (MaterialRecipe recipe : materialRecipes) {
            ItemStack[] stacks = recipe.getIngredient().getItems();
            for (ItemStack stack : stacks) {
                REPAIR_ITEMS.add(stack.getItem());
            }
        }
    }

    public static Set<Item> getRepairItems() {
        return REPAIR_ITEMS;
    }
}
