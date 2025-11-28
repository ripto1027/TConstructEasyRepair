package stan.ripto.easyrepair.datagen.server.advancement;

import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
        for (PouchTier tier : PouchTier.values()) {
            if (!tier.equals(PouchTier.I)) {
                this.createAdvancementBuilder(
                        tier.getAround(),
                        ResourceLocation.fromNamespaceAndPath(TinkersEasyRepair.MOD_ID, tier.getName())
                ).save(
                        saver,
                        ResourceLocation.parse(TinkersEasyRepair.MOD_ID + ":recipes/tools/" + tier.getName()),
                        existingFileHelper
                );
            }
        }
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
