package net.milkshake.milkshakemod.datagen;

import net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.block.custom.LunateanCropBlock;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        // Add your block loot tables here
        dropSelf(ModBlocks.LUNATEAN_BLOCK.get());
        dropSelf(ModBlocks.RAW_LUNATEAN_BLOCK.get());
        dropSelf(ModBlocks.SOUND_BLOCK.get());
        
        add(ModBlocks.LUNATEAN_ORE.get(),
                (block) -> createOreDrop(ModBlocks.LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));
        add(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get(),
                (block) -> createOreDrop(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));
        add(ModBlocks.NETHER_LUNATEAN_ORE.get(),
                (block) -> createOreDrop(ModBlocks.NETHER_LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));
        add(ModBlocks.END_STONE_LUNATEAN_ORE.get(),
                (block) -> createOreDrop(ModBlocks.END_STONE_LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));

        dropSelf(ModBlocks.LUNATEAN_STAIRS.get());
        dropSelf(ModBlocks.LUNATEAN_BUTTON.get());
        dropSelf(ModBlocks.LUNATEAN_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.LUNATEAN_FENCE.get());
        dropSelf(ModBlocks.LUNATEAN_FENCE_GATE.get());
        dropSelf(ModBlocks.LUNATEAN_WALL.get());
        dropSelf(ModBlocks.LUNATEAN_TRAPDOOR.get());
        dropSelf(ModBlocks.LUNATEAN_DOOR.get());
        dropSelf(ModBlocks.LUNATEAN_SLAB.get());

        dropSelf(ModBlocks.LUNATEAN_LOG.get());
        dropSelf(ModBlocks.STRIPPED_LUNATEAN_LOG.get());
        dropSelf(ModBlocks.ANCIENT_WOOD_LOG.get());
        dropSelf(ModBlocks.LUNATEAN_PLANKS.get());
        dropSelf(ModBlocks.LUNATEAN_LEAVES.get());

        dropSelf(ModBlocks.CHALICE_OF_DECEPTION.get());
        dropSelf(ModBlocks.CHALICE_OF_ETERNAL_FLAME.get()); 
        dropSelf(ModBlocks.CHALICE_OF_VOID.get());
        dropSelf(ModBlocks.CHALICE_OF_NATURE.get());
        dropSelf(ModBlocks.CURSED_ICE_PEDESTAL.get());
        dropSelf(ModBlocks.FESTIVE_SPIRE.get());

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.LUNATEAN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LunateanCropBlock.AGE, 7));

        add(ModBlocks.LUNATEAN_CROP.get(), createCropDrops(ModBlocks.LUNATEAN_CROP.get(), 
                ModItems.LUNATEAN.get(), ModItems.LUNATEAN_SEEDS.get(), lootitemcondition$builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
} 