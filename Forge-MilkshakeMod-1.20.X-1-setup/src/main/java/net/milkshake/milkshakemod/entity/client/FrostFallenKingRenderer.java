package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.FrostFallenKingEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FrostFallenKingRenderer extends MobRenderer<FrostFallenKingEntity, FrostFallenKingModel<FrostFallenKingEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID,
            "textures/entity/frost_fallen_king.png");

    public FrostFallenKingRenderer(EntityRendererProvider.Context context) {
        super(context, new FrostFallenKingModel(context.bakeLayer(ModModelLayers.FROST_FALLEN_KING_LAYER)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(FrostFallenKingEntity entity) {
        return TEXTURE;
    }
} 