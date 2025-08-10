package stan.ripto.easyrepair.datagen.server.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import stan.ripto.easyrepair.TConstructEasyRepair;
import stan.ripto.easyrepair.item.EasyRepairItems;

import java.util.function.Consumer;

public class EasyRepairRecipeProvider extends RecipeProvider {
    public EasyRepairRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EasyRepairItems.REPAIR_ITEM_POUCH.get())
                .define('A', Items.LEATHER)
                .define('B', Items.WHITE_DYE)
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .group(TConstructEasyRepair.MOD_ID)
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .unlockedBy(getHasName(Items.WHITE_DYE), has(Items.WHITE_DYE))
                .save(writer);
    }
}
