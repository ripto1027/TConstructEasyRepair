package stan.ripto.easyrepair.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import stan.ripto.easyrepair.TinkersEasyRepair;

public class EasyRepairRecipeSerializers {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TinkersEasyRepair.MOD_ID);

    public static final RegistryObject<RecipeSerializer<PouchUpgradeRecipe>> POUCH =
            SERIALIZERS.register("pouch_upgrade", PouchUpgradeRecipe.Serializer::new);

    public static void register(IEventBus bus) {
        SERIALIZERS.register(bus);
    }
}
