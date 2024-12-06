package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.RootEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RootRenderer extends MobRenderer<RootEntity, RootModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID,
            "textures/entity/root.png");

    public RootRenderer(EntityRendererProvider.Context context) {
        super(context, new RootModel(context.bakeLayer(ModModelLayers.ROOT_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(RootEntity entity) {
        return TEXTURE;
    }
} 