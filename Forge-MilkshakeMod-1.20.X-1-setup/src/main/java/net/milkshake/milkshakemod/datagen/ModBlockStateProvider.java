package net.milkshake.milkshakemod.datagen;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.block.custom.LunateanCropBlock;



public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MilkshakeMod.Mod_ID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.LUNATEAN_BLOCK);
        blockWithItem(ModBlocks.RAW_LUNATEAN_BLOCK);
        blockWithItem(ModBlocks.LUNATEAN_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_LUNATEAN_ORE);
        blockWithItem(ModBlocks.NETHER_LUNATEAN_ORE);
        blockWithItem(ModBlocks.END_STONE_LUNATEAN_ORE);    
        blockWithItem(ModBlocks.SOUND_BLOCK);

        
        stairsBlock(((StairBlock) ModBlocks.LUNATEAN_STAIRS.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.LUNATEAN_SLAB.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.LUNATEAN_BUTTON.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.LUNATEAN_PRESSURE_PLATE.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.LUNATEAN_FENCE.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.LUNATEAN_FENCE_GATE.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.LUNATEAN_WALL.get()), blockTexture(ModBlocks.LUNATEAN_BLOCK.get()));

        models().doorBottomLeft(name(ModBlocks.LUNATEAN_DOOR.get()) + "_bottom", 
                modLoc("block/lunatean_door_bottom"), 
                modLoc("block/lunatean_door_top"));
        models().doorBottomRight(name(ModBlocks.LUNATEAN_DOOR.get()) + "_bottom_hinge", 
                modLoc("block/lunatean_door_bottom"), 
                modLoc("block/lunatean_door_top"));
        models().doorTopLeft(name(ModBlocks.LUNATEAN_DOOR.get()) + "_top", 
                modLoc("block/lunatean_door_bottom"), 
                modLoc("block/lunatean_door_top"));
        models().doorTopRight(name(ModBlocks.LUNATEAN_DOOR.get()) + "_top_hinge", 
                modLoc("block/lunatean_door_bottom"), 
                modLoc("block/lunatean_door_top"));

        doorBlock(((DoorBlock) ModBlocks.LUNATEAN_DOOR.get()), 
                modLoc("block/lunatean_door_bottom"),
                modLoc("block/lunatean_door_top"));
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.LUNATEAN_TRAPDOOR.get()), modLoc("block/lunatean_trapdoor"), true, "cutout");

              makeLunateanCrop((CropBlock) ModBlocks.LUNATEAN_CROP.get(), "lunatean_stage", "lunatean_stage");

        logBlock(((RotatedPillarBlock) ModBlocks.LUNATEAN_LOG.get()));
       
        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_LUNATEAN_LOG.get()));
        logBlock(((RotatedPillarBlock) ModBlocks.ANCIENT_WOOD_LOG.get()));

        blockWithItem(ModBlocks.LUNATEAN_PLANKS);
        leavesBlock(ModBlocks.LUNATEAN_LEAVES);

        // Use existing custom model for Chalice of Deception
        ModelFile chaliceModel = models().getExistingFile(modLoc("block/chalice_of_deception"));
        simpleBlock(ModBlocks.CHALICE_OF_DECEPTION.get(), chaliceModel);
        simpleBlockItem(ModBlocks.CHALICE_OF_DECEPTION.get(), chaliceModel); 

        ModelFile chaliceMode2 = models().getExistingFile(modLoc("block/chalice_of_eternal_flame"));
        simpleBlock(ModBlocks.CHALICE_OF_ETERNAL_FLAME.get(), chaliceMode2);
        simpleBlockItem(ModBlocks.CHALICE_OF_ETERNAL_FLAME.get(), chaliceMode2); 

        ModelFile chaliceMode3 = models().getExistingFile(modLoc("block/chalice_of_nature"));
        simpleBlock(ModBlocks.CHALICE_OF_NATURE.get(), chaliceMode3);
        simpleBlockItem(ModBlocks.CHALICE_OF_NATURE.get(), chaliceMode3);

        ModelFile chaliceMode4 = models().getExistingFile(modLoc("block/chalice_of_void"));
        simpleBlock(ModBlocks.CHALICE_OF_VOID.get(), chaliceMode4);
        simpleBlockItem(ModBlocks.CHALICE_OF_VOID.get(), chaliceMode4);

        saplingBlock(ModBlocks.LUNATEAN_SAPLING);
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
        
    
    public void makeLunateanCrop(CropBlock block, String modelName, String textureName) {
        java.util.function.Function<BlockState, ConfiguredModel[]> function = state -> strawberryStates(state, block, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] strawberryStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((LunateanCropBlock) block).getAgeProperty()),
                new ResourceLocation(MilkshakeMod.Mod_ID, "block/" + textureName + state.getValue(((LunateanCropBlock) block).getAgeProperty()))).renderType("cutout"));
        return models;
    }

    protected void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        simpleBlockWithItem(block, cubeAll(block));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        ResourceLocation texture = blockTexture(block);
        
        var model = models().singleTexture(
                ForgeRegistries.BLOCKS.getKey(block).getPath(),
                new ResourceLocation("minecraft:block/leaves"),
                "all", texture)
                .renderType("cutout");
        
        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }

    private String name(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }
}
