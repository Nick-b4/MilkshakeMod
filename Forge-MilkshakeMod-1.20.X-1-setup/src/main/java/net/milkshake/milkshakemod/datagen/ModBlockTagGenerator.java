package net.milkshake.milkshakemod.datagen;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import java.util.concurrent.CompletableFuture;


public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MilkshakeMod.Mod_ID, existingFileHelper);
    }
    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
            .add(ModBlocks.LUNATEAN_ORE.get())
            .add(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get())
            .add(ModBlocks.NETHER_LUNATEAN_ORE.get())
            .add(ModBlocks.END_STONE_LUNATEAN_ORE.get())
            .addTag(Tags.Blocks.ORES);
    

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(ModBlocks.LUNATEAN_ORE.get())
            .add(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get())
            .add(ModBlocks.NETHER_LUNATEAN_ORE.get())
            .add(ModBlocks.END_STONE_LUNATEAN_ORE.get())
            .add(ModBlocks.RAW_LUNATEAN_BLOCK.get())
            .add(ModBlocks.LUNATEAN_BLOCK.get())
            .add(ModBlocks.LUNATEAN_STAIRS.get())
            .add(ModBlocks.LUNATEAN_SLAB.get())
            .add(ModBlocks.LUNATEAN_WALL.get())
            .add(ModBlocks.CHALICE_OF_DECEPTION.get())
            .add(ModBlocks.CHALICE_OF_ETERNAL_FLAME.get())
            .add(ModBlocks.CHALICE_OF_NATURE.get())
            .add(ModBlocks.CHALICE_OF_VOID.get())
            .add(ModBlocks.CURSED_ICE_PEDESTAL.get());

        this.tag(BlockTags.LOGS)
            .add(ModBlocks.LUNATEAN_LOG.get())
            .add(ModBlocks.STRIPPED_LUNATEAN_LOG.get())
            .add(ModBlocks.ANCIENT_WOOD_LOG.get());

        this.tag(BlockTags.PLANKS)
            .add(ModBlocks.LUNATEAN_PLANKS.get());

        this.tag(ModTags.Blocks.NEEDS_LUNATEAN_TOOL)
            .add(ModBlocks.LUNATEAN_ORE.get())
            .add(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get())
            .add(ModBlocks.NETHER_LUNATEAN_ORE.get())
            .add(ModBlocks.END_STONE_LUNATEAN_ORE.get())
            .add(ModBlocks.LUNATEAN_LOG.get())
            .add(ModBlocks.LUNATEAN_PLANKS.get());

        this.tag(BlockTags.FENCES)
            .add(ModBlocks.LUNATEAN_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
            .add(ModBlocks.LUNATEAN_FENCE_GATE.get());
        this.tag(BlockTags.WALLS)
            .add(ModBlocks.LUNATEAN_WALL.get());
        this.tag(BlockTags.BUTTONS)
            .add(ModBlocks.LUNATEAN_BUTTON.get());
        this.tag(BlockTags.PRESSURE_PLATES)
            .add(ModBlocks.LUNATEAN_PRESSURE_PLATE.get());
        this.tag(BlockTags.STAIRS)
            .add(ModBlocks.LUNATEAN_STAIRS.get());
        this.tag(BlockTags.SLABS)
            .add(ModBlocks.LUNATEAN_SLAB.get());

    }

}
