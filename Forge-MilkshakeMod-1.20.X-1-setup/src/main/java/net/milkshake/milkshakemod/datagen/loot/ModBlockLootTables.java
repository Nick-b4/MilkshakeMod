package net.milkshake.milkshakemod.datagen.loot;

import net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.block.custom.LunateanCropBlock;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.client.Minecraft;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;    
import net.minecraft.world.level.block.LeavesBlock;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {

        this.dropSelf(ModBlocks.LUNATEAN_BLOCK.get());
        this.dropSelf(ModBlocks.RAW_LUNATEAN_BLOCK.get());
        this.dropSelf(ModBlocks.SOUND_BLOCK.get());

        this.add(ModBlocks.LUNATEAN_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));
        this.add(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));
        this.add(ModBlocks.NETHER_LUNATEAN_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.NETHER_LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));
        this.add(ModBlocks.END_STONE_LUNATEAN_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.END_STONE_LUNATEAN_ORE.get(), ModItems.RAW_LUNATEAN.get()));

        this.dropSelf(ModBlocks.LUNATEAN_LOG.get());
        this.dropSelf(ModBlocks.LUNATEAN_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_LUNATEAN_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_LUNATEAN_WOOD.get());
        this.dropSelf(ModBlocks.LUNATEAN_PLANKS.get());
        this.dropSelf(ModBlocks.LUNATEAN_STAIRS.get());
        this.dropSelf(ModBlocks.LUNATEAN_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.LUNATEAN_FENCE.get());
        this.dropSelf(ModBlocks.LUNATEAN_FENCE_GATE.get());
        this.dropSelf(ModBlocks.LUNATEAN_WALL.get());
        this.dropSelf(ModBlocks.LUNATEAN_TRAPDOOR.get());
        this.dropSelf(ModBlocks.LUNATEAN_BUTTON.get());
        this.dropSelf(ModBlocks.CHALICE_OF_DECEPTION.get());
        this.dropSelf(ModBlocks.CHALICE_OF_ETERNAL_FLAME.get());
        this.dropSelf(ModBlocks.CHALICE_OF_NATURE.get());
        this.dropSelf(ModBlocks.CHALICE_OF_VOID.get());
        this.dropSelf(ModBlocks.LUNATEAN_SAPLING.get());
        this.add(ModBlocks.LUNATEAN_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.LUNATEAN_SLAB.get()));
        this.add(ModBlocks.LUNATEAN_DOOR.get(),
                block -> createDoorTable(ModBlocks.LUNATEAN_DOOR.get()));
               
                LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.LUNATEAN_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(LunateanCropBlock.AGE, 5));

        this.add(ModBlocks.LUNATEAN_CROP.get(), createCropDrops(ModBlocks.LUNATEAN_CROP.get(), ModItems.LUNATEAN.get(),
                ModItems.LUNATEAN_SEEDS.get(), lootitemcondition$builder));


        this.add(ModBlocks.LUNATEAN_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.LUNATEAN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 1.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}