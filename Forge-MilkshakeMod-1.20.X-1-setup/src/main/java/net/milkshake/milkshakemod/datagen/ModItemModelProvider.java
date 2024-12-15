package net.milkshake.milkshakemod.datagen;

import java.util.LinkedHashMap;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MilkshakeMod.Mod_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.LUNATEAN);
        simpleItem(ModItems.RAW_LUNATEAN);

        evenSimplerBlockItem(ModBlocks.LUNATEAN_LOG);
        evenSimplerBlockItem(ModBlocks.STRIPPED_LUNATEAN_LOG);
        evenSimplerBlockItem(ModBlocks.ANCIENT_WOOD_LOG);


        simpleItem(ModItems.METAL_DETECTOR);
        simpleItem(ModItems.LUNATEANOAL);
        simpleItem(ModItems.MILKBERRY);
        

        withExistingParent(ForgeRegistries.BLOCKS.getKey(ModBlocks.LUNATEAN_DOOR.get()).getPath(),
                new ResourceLocation("item/generated"))
            .texture("layer0", new ResourceLocation(MilkshakeMod.Mod_ID, 
                "item/" + ForgeRegistries.BLOCKS.getKey(ModBlocks.LUNATEAN_DOOR.get()).getPath()));

        fenceItem(ModBlocks.LUNATEAN_FENCE, ModBlocks.LUNATEAN_BLOCK);
        buttonItem(ModBlocks.LUNATEAN_BUTTON, ModBlocks.LUNATEAN_BLOCK);
        wallItem(ModBlocks.LUNATEAN_WALL, ModBlocks.LUNATEAN_BLOCK);

        evenSimplerBlockItem(ModBlocks.LUNATEAN_STAIRS);
        evenSimplerBlockItem(ModBlocks.LUNATEAN_SLAB);
        evenSimplerBlockItem(ModBlocks.LUNATEAN_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.LUNATEAN_FENCE_GATE);

        trapdoorItem(ModBlocks.LUNATEAN_TRAPDOOR);

        handheldItem(ModItems.LUNATEAN_PICKAXE);
        handheldItem(ModItems.LUNATEAN_AXE);
        handheldItem(ModItems.LUNATEAN_SHOVEL);
        handheldItem(ModItems.LUNATEAN_HOE);
        handheldItem(ModItems.LUNATEAN_PAXEL);
        handheldItem(ModItems.LUNATEAN_SWORD);

        trimmedArmorItem(ModItems.LUNATEAN_HELMET);
        trimmedArmorItem(ModItems.LUNATEAN_CHESTPLATE);
        trimmedArmorItem(ModItems.LUNATEAN_LEGGINGS);
        trimmedArmorItem(ModItems.LUNATEAN_BOOTS);

        simpleItem(ModItems.LUNATEAN_SEEDS);
        simpleItem(ModItems.BAR_BRAWL_MUSIC_DISC);
        withExistingParent(ModItems.ITACHI_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.BOTTLE_OF_BLACK_FLAME);
        simpleItem(ModItems.EYES_OF_A_FRIEND);
        simpleItem(ModItems.INFERNAL_CORE);
        simpleItem(ModItems.SOULFIRE_CRYSTAL);
        simpleItem(ModItems.EMBER_OF_ETERNITY);
        simpleItem(ModItems.STONE_TABLET);
        saplingItem(ModBlocks.LUNATEAN_SAPLING);
        simpleItem(ModItems.ENDER_HEART);
        simpleItem(ModItems.ECHO_OF_OBLIVION);
        simpleItem(ModItems.FRAGMENT_OF_THE_VOID);
        simpleItem(ModItems.ESSENCE_OF_THE_BLOOM);
        simpleItem(ModItems.ROOT_OF_RENEWAL);
        simpleItem(ModItems.LUNATEAN_PAXEL);
        simpleItem(ModItems.ESSENCE_OF_THE_ABYSS);
        simpleItem(ModItems.FRAGMENT_OF_SHADOWS);
        simpleItem(ModItems.BLAZING_EMBER);
        simpleItem(ModItems.HEART_OF_THE_FOREST);
        simpleItem(ModItems.STAR_OF_YULE);
        simpleItem(ModItems.CROWN_OF_THE_KING);

    }

    
    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {
                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MilkshakeMod.Mod_ID, armorItemPath); 
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MilkshakeMod.Mod_ID, currentTrimName);

                
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MilkshakeMod.Mod_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }

    private ItemModelBuilder blockItem(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation(block.getId().getNamespace(), "block/" + block.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MilkshakeMod.Mod_ID, "item/" + item.getId().getPath()));
    }

    private void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", new ResourceLocation(MilkshakeMod.Mod_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", new ResourceLocation(MilkshakeMod.Mod_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", new ResourceLocation(MilkshakeMod.Mod_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
        }

        private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
            return withExistingParent(item.getId().getPath(),
                    new ResourceLocation("item/generated")).texture("layer0",
                    new ResourceLocation(MilkshakeMod.Mod_ID,"block/" + item.getId().getPath()));
        }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(MilkshakeMod.Mod_ID,"item/" + item.getId().getPath()));
    }
}