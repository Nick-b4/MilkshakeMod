package net.milkshake.milkshakemod.entity.client;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.EntityRenderersEvent;

public class ModModelLayers {
    public static final ModelLayerLocation ITACHI_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "itachi"), "main");

    public static final ModelLayerLocation SOUL_BENDER_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "soul_bender"), "main");

    public static final ModelLayerLocation ROOT_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "root"), "main");

    public static final ModelLayerLocation UNLIMITED_VOID_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "unlimited_void"), "main");

    public static final ModelLayerLocation INFERNAL_BEING_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "infernal_being"), "main");

    public static final ModelLayerLocation VOID_MAGE_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "void_mage"), "main");

    public static final ModelLayerLocation ITACHI_SHADOW_CLONE_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "itachishadowclone"), "main");

    public static final ModelLayerLocation FROST_FALLEN_KING_LAYER = new ModelLayerLocation(
            new ResourceLocation(MilkshakeMod.Mod_ID, "frost_fallen_king"), "main");

    public static void register(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(UNLIMITED_VOID_LAYER, UnlimitedVoidModel::createBodyLayer);
    }
}