package stan.ripto.easyrepair.datagen.server.recipe;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import stan.ripto.easyrepair.TinkersEasyRepair;
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
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .group(TinkersEasyRepair.MOD_ID + "_I")
                .unlockedBy(getHasName(Items.LEATHER), has(Items.LEATHER))
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EasyRepairItems.REPAIR_ITEM_POUCH_II.get())
                .define('A', Items.GOLD_INGOT)
                .define('B', EasyRepairItems.REPAIR_ITEM_POUCH_I.get())
                .pattern("ABA")
                .group(TinkersEasyRepair.MOD_ID + "_II")
                .unlockedBy(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .unlockedBy(getHasName(EasyRepairItems.REPAIR_ITEM_POUCH_I.get()), has(EasyRepairItems.REPAIR_ITEM_POUCH_I.get()))
                .save(writer);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, EasyRepairItems.REPAIR_ITEM_POUCH_III.get())
                .define('A', Items.DIAMOND)
                .define('B', EasyRepairItems.REPAIR_ITEM_POUCH_II.get())
                .pattern("ABA")
                .group(TinkersEasyRepair.MOD_ID + "_III")
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .unlockedBy(getHasName(EasyRepairItems.REPAIR_ITEM_POUCH_II.get()), has(EasyRepairItems.REPAIR_ITEM_POUCH_II.get()))
                .save(writer);
    }
}
