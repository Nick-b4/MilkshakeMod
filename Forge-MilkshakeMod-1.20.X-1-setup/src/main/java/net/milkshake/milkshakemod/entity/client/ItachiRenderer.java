package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.ItachiEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ItachiRenderer extends MobRenderer<ItachiEntity, ItachiModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID, 
            "textures/entity/itachi.png");

    public ItachiRenderer(EntityRendererProvider.Context context) {
        super(context, new ItachiModel(context.bakeLayer(ModModelLayers.ITACHI_LAYER)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(ItachiEntity entity) {
        return TEXTURE;
    }
}