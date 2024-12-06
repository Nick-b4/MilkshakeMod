package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.UnlimitedVoidEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class UnlimitedVoidRenderer extends MobRenderer<UnlimitedVoidEntity, UnlimitedVoidModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID,
            "textures/entity/unlimited_void.png");

    public UnlimitedVoidRenderer(EntityRendererProvider.Context context) {
        super(context, new UnlimitedVoidModel(context.bakeLayer(ModModelLayers.UNLIMITED_VOID_LAYER)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(UnlimitedVoidEntity entity) {
        return TEXTURE;
    }
} 