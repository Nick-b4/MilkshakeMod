package net.milkshake.milkshakemod.block;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.milkshake.milkshakemod.block.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.milkshake.milkshakemod.block.custom.SoundBlock;
import java.util.function.Supplier;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.milkshake.milkshakemod.block.custom.ChaliceOfDeceptionBlock;
import net.milkshake.milkshakemod.block.custom.ChaliceOfEternalFlameBlock;
import net.milkshake.milkshakemod.block.custom.ChaliceOfNatureBlock;
import net.milkshake.milkshakemod.block.custom.ChaliceOfVoidBlock;
import net.milkshake.milkshakemod.block.custom.CursedIcePedestalBlock;
import net.milkshake.milkshakemod.block.custom.FestiveSpireBlock;
import net.milkshake.milkshakemod.block.custom.LunateanCropBlock;
import net.milkshake.milkshakemod.sound.ModSounds;
import net.milkshake.milkshakemod.worldgen.tree.LunateanTreeGrower;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MilkshakeMod.Mod_ID);

    public static final RegistryObject<Block> LUNATEAN_BLOCK = registerBlock("lunatean_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
                    .sound(SoundType.AMETHYST)
                    .requiresCorrectToolForDrops()
                    .strength(5f, 6f)));
    public static final RegistryObject<Block> RAW_LUNATEAN_BLOCK = registerBlock("raw_lunatean_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                    .sound(SoundType.AMETHYST)
                    .requiresCorrectToolForDrops()
                    .strength(4f, 5f)));

    public static final RegistryObject<Block> LUNATEAN_ORE = registerBlock("lunatean_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.ANCIENT_DEBRIS)
                    .requiresCorrectToolForDrops()
                    .strength(3f, 4f), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> DEEPSLATE_LUNATEAN_ORE = registerBlock("deepslate_lunatean_ore",    
                () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_DIAMOND_ORE)
                        .requiresCorrectToolForDrops()
                        .strength(3f, 4f), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> NETHER_LUNATEAN_ORE = registerBlock("nether_lunatean_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_GOLD_ORE)
                    .requiresCorrectToolForDrops()
                    .strength(3f, 4f), UniformInt.of(3, 7)));
    public static final RegistryObject<Block> END_STONE_LUNATEAN_ORE = registerBlock("end_stone_lunatean_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.END_STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3f, 4f), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> LUNATEAN_LOG = registerBlock("lunatean_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_LUNATEAN_LOG = registerBlock("stripped_lunatean_log",    
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));
       public static final RegistryObject<Block> ANCIENT_WOOD_LOG = registerBlock("ancient_wood_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));
            public static final RegistryObject<Block> LUNATEAN_PLANKS = registerBlock("lunatean_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
 
    public static final RegistryObject<Block> LUNATEAN_LEAVES = registerBlock("lunatean_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES))  {   
             @Override
            public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return true;
            }

            @Override
            public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 60;
            }

            @Override
            public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                return 30;
            }
        });
            

    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).sound(ModSounds.SOUND_BLOCK_SOUNDS)));

               public static final RegistryObject<Block> LUNATEAN_STAIRS = registerBlock("lunatean_stairs",
            () -> new StairBlock(() -> ModBlocks.LUNATEAN_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> LUNATEAN_SLAB = registerBlock("lunatean_slab",    
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

    public static final RegistryObject<Block> LUNATEAN_BUTTON = registerBlock("lunatean_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.STONE_BUTTON).sound(SoundType.AMETHYST),
                    BlockSetType.IRON, 10, true));
    public static final RegistryObject<Block> LUNATEAN_PRESSURE_PLATE = registerBlock("lunatean_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST),
                    BlockSetType.IRON));

    public static final RegistryObject<Block> LUNATEAN_FENCE = registerBlock("lunatean_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.AMETHYST)));
    public static final RegistryObject<Block> LUNATEAN_FENCE_GATE = registerBlock("lunatean_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST), SoundEvents.CHAIN_PLACE, SoundEvents.ANVIL_BREAK));
    public static final RegistryObject<Block> LUNATEAN_WALL = registerBlock("lunatean_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.AMETHYST))); 

    public static final RegistryObject<Block> LUNATEAN_DOOR = registerBlock("lunatean_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> LUNATEAN_TRAPDOOR = registerBlock("lunatean_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR).sound(SoundType.AMETHYST).noOcclusion(), BlockSetType.OAK));
    public static final RegistryObject<Block> LUNATEAN_CROP = BLOCKS.register("lunatean_crop",
            () -> new LunateanCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion().noCollission()));
    public static final RegistryObject<Block> CHALICE_OF_DECEPTION = registerBlock("chalice_of_deception",
            () -> new ChaliceOfDeceptionBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> CHALICE_OF_ETERNAL_FLAME = registerBlock("chalice_of_eternal_flame",
            () -> new ChaliceOfEternalFlameBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> CHALICE_OF_NATURE = registerBlock("chalice_of_nature",
            () -> new ChaliceOfNatureBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> CHALICE_OF_VOID = registerBlock("chalice_of_void",
            () -> new ChaliceOfVoidBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> CURSED_ICE_PEDESTAL = registerBlock("cursed_ice_pedestal",
            () -> new CursedIcePedestalBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()));

    public static final RegistryObject<Block> FESTIVE_SPIRE = registerBlock("festive_spire",
            () -> new FestiveSpireBlock(BlockBehaviour.Properties.copy(Blocks.AMETHYST_BLOCK).noOcclusion()));

             public static final RegistryObject<Block> LUNATEAN_SAPLING = registerBlock("lunatean_sapling",
            () -> new SaplingBlock(new LunateanTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));



    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
