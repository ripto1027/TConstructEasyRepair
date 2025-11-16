package stan.ripto.easyrepair.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import stan.ripto.easyrepair.util.PouchTier;

public class AbstractPouchScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    private static final int IMAGE_HEIGHT_BASE = 111;
    private static final int IMAGE_WIDTH_BASE = 175;
    private static final int SLOT_LENGTH = 18;
    private static final int INV_LABEL_Y_MOVE_INT = 91;

    protected final ResourceLocation texture;

    public AbstractPouchScreen(T menu, Inventory playerInventory, Component title, PouchTier tier) {
        super(menu, playerInventory, title);
        this.texture = tier.getTexture();
        this.imageHeight = IMAGE_HEIGHT_BASE + tier.getRows() * SLOT_LENGTH;
        this.imageWidth = IMAGE_WIDTH_BASE;
        this.inventoryLabelY = this.imageHeight - INV_LABEL_Y_MOVE_INT;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(this.texture, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
