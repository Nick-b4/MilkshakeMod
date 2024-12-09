package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.ItachiShadowCloneEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ItachiShadowCloneRenderer extends MobRenderer<ItachiShadowCloneEntity, ItachiShadowCloneModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID, 
            "textures/entity/itachishadowclone.png");

    public ItachiShadowCloneRenderer(EntityRendererProvider.Context context) {
        super(context, new ItachiShadowCloneModel(context.bakeLayer(ModModelLayers.ITACHI_SHADOW_CLONE_LAYER)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(ItachiShadowCloneEntity entity) {
        return TEXTURE;
    }
}