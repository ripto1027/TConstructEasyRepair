package stan.ripto.easyrepair.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import stan.ripto.easyrepair.inventory.RepairItemPouchInventory;

public class RepairItemPouchInventoryProvider implements ICapabilitySerializable<CompoundTag> {
    private final RepairItemPouchInventory INVENTORY = new RepairItemPouchInventory(9);
    private final LazyOptional<IItemHandler> OPTIONAL = LazyOptional.of(() -> INVENTORY);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == ForgeCapabilities.ITEM_HANDLER ? OPTIONAL.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        return INVENTORY.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        INVENTORY.deserializeNBT(nbt);
    }
}
