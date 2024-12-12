package net.milkshake.milkshakemod.block.entity;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MilkshakeMod.Mod_ID);

    public static final RegistryObject<BlockEntityType<ChaliceOfDeceptionBlockEntity>> CHALICE_OF_DECEPTION_BE =
            BLOCK_ENTITIES.register("chalice_of_deception_be", () ->
                    BlockEntityType.Builder.of(ChaliceOfDeceptionBlockEntity::new,
                            ModBlocks.CHALICE_OF_DECEPTION.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChaliceOfEternalFlameBlockEntity>> CHALICE_OF_ETERNAL_FLAME_BE =
            BLOCK_ENTITIES.register("chalice_of_eternal_flame_be", () ->
                    BlockEntityType.Builder.of(ChaliceOfEternalFlameBlockEntity::new,
                            ModBlocks.CHALICE_OF_ETERNAL_FLAME.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChaliceOfVoidBlockEntity>> CHALICE_OF_VOID_BE =
            BLOCK_ENTITIES.register("chalice_of_void_be",
                    () -> BlockEntityType.Builder.of(ChaliceOfVoidBlockEntity::new,
                            ModBlocks.CHALICE_OF_VOID.get()).build(null));

    public static final RegistryObject<BlockEntityType<ChaliceOfNatureBlockEntity>> CHALICE_OF_NATURE_BE =
            BLOCK_ENTITIES.register("chalice_of_nature_be",
                    () -> BlockEntityType.Builder.of(ChaliceOfNatureBlockEntity::new,
                            ModBlocks.CHALICE_OF_NATURE.get()).build(null));

    public static final RegistryObject<BlockEntityType<CursedIcePedestalBlockEntity>> CURSED_ICE_PEDESTAL_BE =
            BLOCK_ENTITIES.register("cursed_ice_pedestal_be",
                    () -> BlockEntityType.Builder.of(CursedIcePedestalBlockEntity::new,
                            ModBlocks.CURSED_ICE_PEDESTAL.get()).build(null));

    public static final RegistryObject<BlockEntityType<FestiveSpireBlockEntity>> FESTIVE_SPIRE_BE =
            BLOCK_ENTITIES.register("festive_spire_be",
                    () -> BlockEntityType.Builder.of(FestiveSpireBlockEntity::new,
                            ModBlocks.FESTIVE_SPIRE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}