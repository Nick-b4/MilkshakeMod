package net.milkshake.milkshakemod.item;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.milkshake.milkshakemod.block.custom.ChaliceOfDeceptionBlock;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MilkshakeMod.Mod_ID);

    public static final RegistryObject<CreativeModeTab> MILKY_TAB = CREATIVE_MODE_TABS.register("milky_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LUNATEAN.get()))
                    .title(Component.translatable("creativetab.milky_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.LUNATEAN.get());
                        pOutput.accept(ModItems.RAW_LUNATEAN.get());

                        pOutput.accept(Items.DIAMOND);

                        pOutput.accept(ModBlocks.LUNATEAN_BLOCK.get());
                        pOutput.accept(ModBlocks.RAW_LUNATEAN_BLOCK.get());
                        pOutput.accept(ModBlocks.LUNATEAN_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get());
                        pOutput.accept(ModBlocks.NETHER_LUNATEAN_ORE.get());
                        pOutput.accept(ModBlocks.END_STONE_LUNATEAN_ORE.get());
                        pOutput.accept(ModBlocks.SOUND_BLOCK.get());
                        pOutput.accept(ModItems.MILKBERRY.get());
                        pOutput.accept(ModItems.LUNATEANOAL.get());
                        pOutput.accept(ModBlocks.LUNATEAN_LOG.get());
                        pOutput.accept(ModBlocks.ANCIENT_WOOD_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_LUNATEAN_LOG.get());
                        pOutput.accept(ModBlocks.LUNATEAN_PLANKS.get());
                        pOutput.accept(ModBlocks.LUNATEAN_LEAVES.get());
                        pOutput.accept(ModBlocks.LUNATEAN_DOOR.get());
                        pOutput.accept(ModBlocks.LUNATEAN_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.LUNATEAN_FENCE.get());
                        pOutput.accept(ModBlocks.LUNATEAN_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.LUNATEAN_WALL.get());
                        pOutput.accept(ModBlocks.LUNATEAN_SLAB.get());
                        pOutput.accept(ModBlocks.LUNATEAN_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.LUNATEAN_BUTTON.get());
                        pOutput.accept(ModBlocks.LUNATEAN_STAIRS.get());
                        pOutput.accept(ModItems.LUNATEAN_STAFF.get());
                        pOutput.accept(ModItems.LUNATEAN_SWORD.get());
                        pOutput.accept(ModItems.LUNATEAN_PICKAXE.get());
                        pOutput.accept(ModItems.LUNATEAN_AXE.get());
                        pOutput.accept(ModItems.LUNATEAN_SHOVEL.get());
                        pOutput.accept(ModItems.LUNATEAN_HOE.get());
                        pOutput.accept(ModItems.LUNATEAN_HELMET.get());
                        pOutput.accept(ModItems.LUNATEAN_CHESTPLATE.get());
                        pOutput.accept(ModItems.LUNATEAN_LEGGINGS.get());
                        pOutput.accept(ModItems.LUNATEAN_BOOTS.get());
                        pOutput.accept(ModItems.LUNATEAN_SEEDS.get());
                        pOutput.accept(ModItems.BAR_BRAWL_MUSIC_DISC.get());
                        pOutput.accept(ModItems.ITACHI_SPAWN_EGG.get());
                        pOutput.accept(ModItems.ENDER_HEART.get());
                        pOutput.accept(ModItems.ECHO_OF_OBLIVION.get());
                        pOutput.accept(ModItems.FRAGMENT_OF_THE_VOID.get());
                        pOutput.accept(ModItems.ESSENCE_OF_THE_BLOOM.get());
                        pOutput.accept(ModItems.ROOT_OF_RENEWAL.get());
                        pOutput.accept(ModBlocks.CHALICE_OF_DECEPTION.get());
                        pOutput.accept(ModBlocks.CHALICE_OF_ETERNAL_FLAME.get());
                        pOutput.accept(ModItems.BOTTLE_OF_BLACK_FLAME.get());
                        pOutput.accept(ModItems.EYES_OF_A_FRIEND.get());
                        pOutput.accept(ModBlocks.CHALICE_OF_NATURE.get());
                        pOutput.accept(ModItems.INFERNAL_CORE.get());
                        pOutput.accept(ModItems.SOULFIRE_CRYSTAL.get());
                        pOutput.accept(ModBlocks.CHALICE_OF_VOID.get());
                        pOutput.accept(ModItems.EMBER_OF_ETERNITY.get());
                        pOutput.accept(ModItems.STONE_TABLET.get());
                        pOutput.accept(ModBlocks.LUNATEAN_SAPLING.get());
                        pOutput.accept(ModItems.LUNATEAN_PAXEL.get());
                        pOutput.accept(ModItems.ESSENCE_OF_THE_ABYSS.get());
                        pOutput.accept(ModItems.FRAGMENT_OF_SHADOWS.get());
                        pOutput.accept(ModItems.BLAZING_EMBER.get());
                        pOutput.accept(ModItems.HEART_OF_THE_FOREST.get());
                        pOutput.accept(ModItems.STAR_OF_YULE.get());
                        pOutput.accept(ModItems.CROWN_OF_THE_KING.get());
                        pOutput.accept(ModBlocks.CURSED_ICE_PEDESTAL.get());
                        pOutput.accept(ModBlocks.FESTIVE_SPIRE.get());
                    
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }
}
