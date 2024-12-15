package net.milkshake.milkshakemod;
import com.mojang.logging.LogUtils;
import  net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.entity.client.*;
import net.milkshake.milkshakemod.events.ModEventBusEvents;
import net.milkshake.milkshakemod.item.ModItems;
import net.milkshake.milkshakemod.item.ModCreativeModTabs;
import net.milkshake.milkshakemod.loot.ModLootModifiers;
import net.milkshake.milkshakemod.screen.ChaliceOfDeceptionScreen;
import net.milkshake.milkshakemod.screen.ChaliceOfEternalFlameScreen;
import net.milkshake.milkshakemod.screen.ChaliceOfNatureScreen;
import net.milkshake.milkshakemod.screen.ChaliceOfVoidScreen;
import net.milkshake.milkshakemod.screen.CursedIcePedestalScreen;
import net.milkshake.milkshakemod.screen.FestiveSpireScreen;
import net.milkshake.milkshakemod.screen.ModMenuTypes;
import net.milkshake.milkshakemod.villager.ModVillagers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.milkshake.milkshakemod.sound.ModSounds;
import net.milkshake.milkshakemod.entity.ModEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.milkshake.milkshakemod.block.entity.ModBlockEntities;
import org.slf4j.Logger;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.Monster;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MilkshakeMod.Mod_ID)
public class MilkshakeMod {
    public static final String Mod_ID = "milkshakemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MilkshakeMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register entities first
        ModEntities.register(modEventBus);
        
        // Register blocks before items
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        
        // Register items after blocks
        ModItems.register(modEventBus);
        
        
        // Register other components
        ModSounds.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        
        // Register creative tabs last
        ModCreativeModTabs.register(modEventBus);
        // Event registration
        modEventBus.addListener(this::addCreative);
        
        // Register event handlers
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.register(ModEventBusEvents.class);
    }

    
    

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
    if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
        event.accept(ModItems.LUNATEAN);
        event.accept(ModItems.RAW_LUNATEAN);

    }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                EntityRenderers.register(ModEntities.ITACHI.get(), ItachiRenderer::new);
                EntityRenderers.register(ModEntities.SOUL_BENDER.get(), SoulBenderRenderer::new);
                EntityRenderers.register(ModEntities.VOID_MAGE.get(), VoidMageRenderer::new);
                EntityRenderers.register(ModEntities.ROOT.get(), RootRenderer::new);
                EntityRenderers.register(ModEntities.UNLIMITED_VOID.get(), UnlimitedVoidRenderer::new);
                EntityRenderers.register(ModEntities.INFERNAL_BEING.get(), InfernalBeingRenderer::new);
                EntityRenderers.register(ModEntities.ITACHI_SHADOW_CLONE.get(), ItachiShadowCloneRenderer::new);
                EntityRenderers.register(ModEntities.FROST_FALLEN_KING.get(), FrostFallenKingRenderer::new);

                MenuScreens.register(ModMenuTypes.CHALICE_OF_DECEPTION_MENU.get(), ChaliceOfDeceptionScreen::new);
                MenuScreens.register(ModMenuTypes.CHALICE_OF_ETERNAL_FLAME_MENU.get(), ChaliceOfEternalFlameScreen::new);
                MenuScreens.register(ModMenuTypes.CHALICE_OF_NATURE_MENU.get(), ChaliceOfNatureScreen::new);
                MenuScreens.register(ModMenuTypes.CHALICE_OF_VOID_MENU.get(), ChaliceOfVoidScreen::new);
                MenuScreens.register(ModMenuTypes.CURSED_ICE_PEDESTAL_MENU.get(), CursedIcePedestalScreen::new);
                MenuScreens.register(ModMenuTypes.FESTIVE_SPIRE_MENU.get(), FestiveSpireScreen::new);
            });
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(ModModelLayers.ITACHI_LAYER, ItachiModel::createBodyLayer);
            event.registerLayerDefinition(ModModelLayers.SOUL_BENDER_LAYER, SoulBenderModel::createBodyLayer);
            event.registerLayerDefinition(ModModelLayers.ROOT_LAYER, RootModel::createBodyLayer);
            event.registerLayerDefinition(ModModelLayers.UNLIMITED_VOID_LAYER, UnlimitedVoidModel::createBodyLayer);
            event.registerLayerDefinition(ModModelLayers.INFERNAL_BEING_LAYER, InfernalBeingModel::createBodyLayer);
            event.registerLayerDefinition(ModModelLayers.VOID_MAGE_LAYER, VoidMageModel::createBodyLayer);
            event.registerLayerDefinition(ModModelLayers.ITACHI_SHADOW_CLONE_LAYER, ItachiShadowCloneModel::createBodyLayer);
            event.registerLayerDefinition(ModModelLayers.FROST_FALLEN_KING_LAYER, FrostFallenKingModel::createBodyLayer);
        }

    }

}
