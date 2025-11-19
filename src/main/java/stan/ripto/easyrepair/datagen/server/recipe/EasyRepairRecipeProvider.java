package stan.ripto.easyrepair.datagen.server.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import stan.ripto.easyrepair.item.EasyRepairItems;

import java.util.function.Consumer;

public class EasyRepairRecipeProvider extends RecipeProvider {
    public EasyRepairRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EasyRepairItems.REPAIR_ITEM_POUCH_I.get())
                .define('A', Items.LEATHER)
                .define('B', Items.IRON_INGOT)
                .pattern("ABA")
                .pattern("B B")
                .pattern("ABA")
                .group("repair_item_pouch_i")
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(writer);
    }
}
