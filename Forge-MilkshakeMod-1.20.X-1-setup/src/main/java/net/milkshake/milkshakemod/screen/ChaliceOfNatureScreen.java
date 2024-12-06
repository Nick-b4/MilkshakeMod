package net.milkshake.milkshakemod.screen;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ChaliceOfNatureScreen extends AbstractContainerScreen<ChaliceOfNatureMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(MilkshakeMod.Mod_ID, "textures/gui/chalice_of_nature_gui.png");

    public ChaliceOfNatureScreen(ChaliceOfNatureMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageHeight = 250;
        this.inventoryLabelY = this.imageHeight - 125;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }
}