package stan.ripto.easyrepair.event;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraftforge.event.server.ServerStartingEvent;
import slimeknights.tconstruct.library.recipe.TinkerRecipeTypes;
import slimeknights.tconstruct.library.recipe.material.MaterialRecipe;
import slimeknights.tconstruct.library.tools.part.IRepairKitItem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepairItemHandler {
    private static final Set<Item> REPAIR_ITEMS = new HashSet<>();

    public static void onServerStarting(ServerStartingEvent event) {
        RecipeManager manager = event.getServer().getRecipeManager();
        List<MaterialRecipe> materialRecipes = manager.getAllRecipesFor(TinkerRecipeTypes.MATERIAL.get());

        for (MaterialRecipe recipe : materialRecipes) {
            ItemStack[] stacks = recipe.getIngredient().getItems();
            for (ItemStack stack : stacks) {
                REPAIR_ITEMS.add(stack.getItem());
            }
        }
    }

    public static boolean isRepairItem(Item item) {
        return REPAIR_ITEMS.contains(item) || item instanceof IRepairKitItem;
    }
}
