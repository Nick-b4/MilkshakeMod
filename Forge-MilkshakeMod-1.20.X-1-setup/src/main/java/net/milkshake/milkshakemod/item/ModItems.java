package net.milkshake.milkshakemod.item;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.item.custom.FuelItem;
import net.milkshake.milkshakemod.item.custom.MetalDetectorItem;
import net.milkshake.milkshakemod.item.custom.ModArmorItem;
import net.milkshake.milkshakemod.sound.ModSounds;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.BlockItem;
import net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.entity.ModEntities;
import net.milkshake.milkshakemod.item.custom.EyesOfAFriendItem;



public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MilkshakeMod.Mod_ID);


        public static final RegistryObject<Item> LUNATEAN = ITEMS.register("lunatean",
        () -> new Item(new Item.Properties()));
public static final RegistryObject<Item> RAW_LUNATEAN = ITEMS.register("raw_lunatean",
        () -> new Item(new Item.Properties()));

        public static final RegistryObject<Item> MILKBERRY = ITEMS.register("milkberry",
        () -> new Item(new Item.Properties().food(ModFoods.MILKBERRY)));

        public static final RegistryObject<Item> LUNATEANOAL = ITEMS.register("lunateanoal",
        () -> new FuelItem(new Item.Properties(), 50000));

        public static final RegistryObject<Item> METAL_DETECTOR = ITEMS.register("metal_detector",
        () -> new MetalDetectorItem(new Item.Properties().durability(100)));

    public static final RegistryObject<Item> LUNATEAN_STAFF = ITEMS.register("lunatean_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));

            public static final RegistryObject<Item> LUNATEAN_SWORD = ITEMS.register("lunatean_sword",
            () -> new SwordItem(ModToolTiers.LUNATEAN, 45, 2, new Item.Properties()));
    public static final RegistryObject<Item> LUNATEAN_PICKAXE = ITEMS.register("lunatean_pickaxe",
            () -> new PickaxeItem(ModToolTiers.LUNATEAN, 42, 1.4f, new Item.Properties()));
    public static final RegistryObject<Item> LUNATEAN_AXE = ITEMS.register("lunatean_axe",
            () -> new AxeItem(ModToolTiers.LUNATEAN, 50, 1.4f, new Item.Properties()));
    public static final RegistryObject<Item> LUNATEAN_SHOVEL = ITEMS.register("lunatean_shovel",
            () -> new ShovelItem(ModToolTiers.LUNATEAN, 39, 1.1f, new Item.Properties()));
    public static final RegistryObject<Item> LUNATEAN_HOE = ITEMS.register("lunatean_hoe",
            () -> new HoeItem(ModToolTiers.LUNATEAN, 38, 4.5f, new Item.Properties()));

          public static final RegistryObject<Item> LUNATEAN_HELMET = ITEMS.register("lunatean_helmet",
            () -> new ModArmorItem(ModArmorMaterials.LUNATEAN, ArmorItem.Type.HELMET, new Item.Properties())); 
    public static final RegistryObject<Item> LUNATEAN_CHESTPLATE = ITEMS.register("lunatean_chestplate",
            () -> new ArmorItem(ModArmorMaterials.LUNATEAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> LUNATEAN_LEGGINGS = ITEMS.register("lunatean_leggings",
            () -> new ArmorItem(ModArmorMaterials.LUNATEAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> LUNATEAN_BOOTS = ITEMS.register("lunatean_boots",
            () -> new ArmorItem(ModArmorMaterials.LUNATEAN, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> LUNATEAN_SEEDS = ITEMS.register("lunatean_seeds", 
            () -> new BlockItem(ModBlocks.LUNATEAN_CROP.get(), new Item.Properties()));
             public static final RegistryObject<Item> BAR_BRAWL_MUSIC_DISC = ITEMS.register("bar_brawl_music_disc",
            () -> new RecordItem(6, ModSounds.BAR_BRAWL, new Item.Properties().stacksTo(1), 2440));
            @SuppressWarnings("deprecation")
        public static final RegistryObject<Item> ITACHI_SPAWN_EGG = ITEMS.register("itachi_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.ITACHI, 0x948e8d, 0x3b3635, new Item.Properties()));

    public static final RegistryObject<Item> EYES_OF_A_FRIEND = ITEMS.register("eyes_of_a_friend",
            () -> new EyesOfAFriendItem(new Item.Properties()));
    public static final RegistryObject<Item> BOTTLE_OF_BLACK_FLAME = ITEMS.register("bottle_of_black_flame", 
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SOULFIRE_CRYSTAL = ITEMS.register("soulfire_crystal",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> INFERNAL_CORE = ITEMS.register("infernal_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMBER_OF_ETERNITY = ITEMS.register("ember_of_eternity",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STONE_TABLET = ITEMS.register("stone_tablet",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    
} }


