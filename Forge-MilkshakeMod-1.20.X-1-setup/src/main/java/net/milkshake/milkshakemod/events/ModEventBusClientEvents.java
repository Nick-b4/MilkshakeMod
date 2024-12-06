package net.milkshake.milkshakemod.events;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.client.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MilkshakeMod.Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.ITACHI_LAYER, ItachiModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.INFERNAL_BEING_LAYER, InfernalBeingModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.UNLIMITED_VOID_LAYER, UnlimitedVoidModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.ROOT_LAYER, RootModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.VOID_MAGE_LAYER, VoidMageModel::createBodyLayer);
    }
}