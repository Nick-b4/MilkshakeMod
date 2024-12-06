package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.VoidMageEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class VoidMageRenderer extends MobRenderer<VoidMageEntity, VoidMageModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID,
            "textures/entity/void_mage.png");

    public VoidMageRenderer(EntityRendererProvider.Context context) {
        super(context, new VoidMageModel(context.bakeLayer(ModModelLayers.VOID_MAGE_LAYER)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(VoidMageEntity entity) {
        return TEXTURE;
    }
} 