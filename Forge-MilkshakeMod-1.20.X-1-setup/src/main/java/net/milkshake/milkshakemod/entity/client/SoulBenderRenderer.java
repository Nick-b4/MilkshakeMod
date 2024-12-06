package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.SoulBenderEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SoulBenderRenderer extends MobRenderer<SoulBenderEntity, SoulBenderModel<SoulBenderEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MilkshakeMod.Mod_ID,
            "textures/entity/soul_bender.png");

    public SoulBenderRenderer(EntityRendererProvider.Context context) {
        super(context, new SoulBenderModel<>(context.bakeLayer(ModModelLayers.SOUL_BENDER_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(SoulBenderEntity entity) {
        return TEXTURE;
    }
} 