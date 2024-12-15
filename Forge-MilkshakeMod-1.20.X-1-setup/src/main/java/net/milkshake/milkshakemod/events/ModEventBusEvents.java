package net.milkshake.milkshakemod.events;    

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.ModEntities;
import net.milkshake.milkshakemod.entity.custom.*;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MilkshakeMod.Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.INFERNAL_BEING.get(), InfernalBeingEntity.createAttributes().build());
        event.put(ModEntities.ITACHI.get(), ItachiEntity.createAttributes().build());
        event.put(ModEntities.ROOT.get(), RootEntity.createAttributes().build());
        event.put(ModEntities.ITACHI_SHADOW_CLONE.get(), ItachiShadowCloneEntity.createAttributes().build());
        event.put(ModEntities.VOID_MAGE.get(), VoidMageEntity.createAttributes().build());
        event.put(ModEntities.SOUL_BENDER.get(), SoulBenderEntity.createAttributes().build());
        event.put(ModEntities.FROST_FALLEN_KING.get(), FrostFallenKingEntity.createAttributes().build());
        event.put(ModEntities.UNLIMITED_VOID.get(), UnlimitedVoidEntity.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.SOUL_BENDER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.VOID_MAGE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}