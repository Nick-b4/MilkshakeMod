package net.milkshake.milkshakemod.events;    

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.ModEntities;
import net.milkshake.milkshakemod.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MilkshakeMod.Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.INFERNAL_BEING.get(), InfernalBeingEntity.createAttributes().build());
        event.put(ModEntities.ITACHI.get(), ItachiEntity.createAttributes().build());
        event.put(ModEntities.ROOT.get(), RootEntity.createAttributes().build());
    }
}