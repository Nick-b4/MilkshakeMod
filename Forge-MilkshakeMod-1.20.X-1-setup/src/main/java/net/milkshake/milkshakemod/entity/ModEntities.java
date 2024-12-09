package net.milkshake.milkshakemod.entity;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, MilkshakeMod.Mod_ID);

    public static final RegistryObject<EntityType<ItachiEntity>> ITACHI =
            ENTITY_TYPES.register("itachi",
                    () -> EntityType.Builder.of(ItachiEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.8f)
                            .build("itachi"));

    public static final RegistryObject<EntityType<InfernalBeingEntity>> INFERNAL_BEING =
            ENTITY_TYPES.register("infernal_being",
                    () -> EntityType.Builder.of(InfernalBeingEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.8f)
                            .fireImmune()
                            .build("infernal_being"));

    public static final RegistryObject<EntityType<UnlimitedVoidEntity>> UNLIMITED_VOID =
            ENTITY_TYPES.register("unlimited_void",
                    () -> EntityType.Builder.of(UnlimitedVoidEntity::new, MobCategory.MONSTER)
                            .sized(1.5f, 3.0f)
                            .build("unlimited_void"));

    public static final RegistryObject<EntityType<SoulBenderEntity>> SOUL_BENDER = 
            ENTITY_TYPES.register("soul_bender",
                    () -> EntityType.Builder.of(SoulBenderEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(MilkshakeMod.Mod_ID + ":soul_bender"));

    public static final RegistryObject<EntityType<RootEntity>> ROOT = 
            ENTITY_TYPES.register("root",
                    () -> EntityType.Builder.of(RootEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .fireImmune()
                            .build("root"));

    public static final RegistryObject<EntityType<VoidMageEntity>> VOID_MAGE = 
            ENTITY_TYPES.register("void_mage",
                    () -> EntityType.Builder.of(VoidMageEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.95f)
                            .build(MilkshakeMod.Mod_ID + ":void_mage"));

    public static final RegistryObject<EntityType<ItachiShadowCloneEntity>> ITACHI_SHADOW_CLONE =
            ENTITY_TYPES.register("itachi_shadow_clone",
                    () -> EntityType.Builder.of(ItachiShadowCloneEntity::new, MobCategory.MONSTER)
                            .sized(0.6f, 1.8f)
                            .build("itachi_shadow_clone"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}