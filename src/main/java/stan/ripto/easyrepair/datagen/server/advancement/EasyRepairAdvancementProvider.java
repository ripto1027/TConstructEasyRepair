package stan.ripto.easyrepair.datagen.server.advancement;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;
import stan.ripto.easyrepair.TinkersEasyRepair;
import stan.ripto.easyrepair.util.PouchTier;

import java.util.*;
import java.util.function.Consumer;

public class EasyRepairAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {
    @SuppressWarnings("NullableProblems")
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper existingFileHelper) {
        this.createAdvancementBuilder(
                Items.GOLD_INGOT,
                ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, PouchTier.II.getName())
        ).save(
                saver,
                ResourceLocation.parse(TinkersEasyRepair.MOD_ID + ":recipes/tools/" + PouchTier.II.getName()),
                existingFileHelper
        );

        this.createAdvancementBuilder(
                Items.DIAMOND,
                ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, PouchTier.III.getName())
        ).save(
                saver,
                ResourceLocation.parse(TinkersEasyRepair.MOD_ID + ":recipes/tools/" + PouchTier.III.getName()),
                existingFileHelper
        );
    }

    private Advancement.Builder createAdvancementBuilder(Item item, ResourceLocation recipe) {
        return Advancement.Builder.recipeAdvancement()
                .parent(ResourceLocation.parse("minecraft:recipes/root"))
                .addCriterion("has_" + this.getItemName(item), InventoryChangeTrigger.TriggerInstance.hasItems(item))
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(recipe))
                .requirements(RequirementsStrategy.OR)
                .rewards(AdvancementRewards.Builder.recipe(recipe));
    }

    private String getItemName(Item item) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)).getPath();
    }
}
