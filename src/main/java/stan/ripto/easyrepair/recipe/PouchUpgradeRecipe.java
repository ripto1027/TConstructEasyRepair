package stan.ripto.easyrepair.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;
import stan.ripto.easyrepair.util.PouchTier;

@SuppressWarnings("NullableProblems")
public class PouchUpgradeRecipe implements CraftingRecipe, IShapedRecipe<CraftingContainer> {
    private final ResourceLocation id;
    private final ItemStack result;
    private final NonNullList<Ingredient> ingredients;

    private static final int WIDTH = 3;
    private static final int HEIGHT = 3;
    private static final int CONTAINER_SIZE = WIDTH * HEIGHT;
    private static final RecipeSerializer<PouchUpgradeRecipe> SERIALIZER = EasyRepairRecipeSerializers.POUCH.get();

    public PouchUpgradeRecipe(ResourceLocation id) {
        this.id = id;

        PouchTier tier = PouchTier.get(id.getPath());
        this.result = new ItemStack(tier.getPouch());
        Ingredient center = tier.getCenterIngredient();
        Ingredient around = tier.getAroundIngredient();

        this.ingredients = NonNullList.withSize(CONTAINER_SIZE, Ingredient.EMPTY);
        this.ingredients.set(1, around);
        this.ingredients.set(3, around);
        this.ingredients.set(4, center);
        this.ingredients.set(5, around);
        this.ingredients.set(7, around);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        for (int i = 0; i < CONTAINER_SIZE; ++i) {
            if (!this.ingredients.get(i).test(container.getItem(i))) return false;
        }

        return true;
    }

    @Override
    public ItemStack assemble(CraftingContainer container, RegistryAccess access) {
        ItemStack resultCopy = this.result.copy();

        container.getItem(4).getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(pouchInv -> resultCopy.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(resultInv -> {
            for (int i = 0; i < pouchInv.getSlots(); i++) {
                ItemStack invStack = pouchInv.getStackInSlot(i);
                if (!invStack.isEmpty()) {
                    ItemHandlerHelper.insertItem(resultInv, invStack, false);
                }
            }
        }));

        return resultCopy;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return WIDTH == width && HEIGHT == height;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return this.result.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public String getGroup() {
        return this.id.getPath();
    }

    @Override
    public CraftingBookCategory category() {
        return CraftingBookCategory.EQUIPMENT;
    }

    @Override
    public int getRecipeWidth() {
        return WIDTH;
    }

    @Override
    public int getRecipeHeight() {
        return HEIGHT;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public boolean isIncomplete() {
        return this.ingredients.stream()
                .filter(ingredient -> !ingredient.isEmpty()).anyMatch(ForgeHooks::hasNoElements);
    }

    public static class Serializer implements RecipeSerializer<PouchUpgradeRecipe> {
        @Override
        public PouchUpgradeRecipe fromJson(ResourceLocation id, JsonObject json) {
            return new PouchUpgradeRecipe(id);
        }

        @Override
        public @Nullable PouchUpgradeRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            return new PouchUpgradeRecipe(id);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, PouchUpgradeRecipe recipe) {}
    }
}
