package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.InfernalBeingEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class InfernalBeingRenderer extends MobRenderer<InfernalBeingEntity, InfernalBeingModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID,
            "textures/entity/infernal_being.png");

    public InfernalBeingRenderer(EntityRendererProvider.Context context) {
        super(context, new InfernalBeingModel(context.bakeLayer(ModModelLayers.INFERNAL_BEING_LAYER)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(InfernalBeingEntity entity) {
        return TEXTURE;
    }
} 