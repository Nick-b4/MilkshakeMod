package net.milkshake.milkshakemod.datagen;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.item.ModItems;
import net.milkshake.milkshakemod.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, MilkshakeMod.Mod_ID);
    }

    @Override
    protected void start() {
        add("lunatean_from_ender_dragon", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:entities/ender_dragon")).build() }, 
                ModItems.LUNATEAN.get()));

        add("metal_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:chests/jungle_temple")).build() }, 
                ModItems.METAL_DETECTOR.get()));

        add("soulfire_crystal_from_soul_bender", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("milkshakemod:entities/soul_bender")).build() }, 
                ModItems.SOULFIRE_CRYSTAL.get()));

        add("echo_of_oblivion_from_void_mage", new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(new ResourceLocation("milkshakemod:entities/void_mage")).build() }, 
                ModItems.ECHO_OF_OBLIVION.get()));

        add("stone_tablet_from_woodland_mansion", new AddItemModifier(new LootItemCondition[] {
                        new LootTableIdCondition.Builder(new ResourceLocation("minecraft:chests/woodland_mansion")).build() }, 
                        ModItems.STONE_TABLET.get()));
        add("essence_of_the_bloom_from_bee", new AddItemModifier(new LootItemCondition[] {
                                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:entities/bee")).build() }, 
                        ModItems.ESSENCE_OF_THE_BLOOM.get())); 
        add("root_of_renewal_from_spore_blossom", new AddItemModifier(new LootItemCondition[] {
                                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:blocks/spore_blossom")).build() }, 
                        ModItems.ROOT_OF_RENEWAL.get()));  
        add("ender_heart_from_endermite", new AddItemModifier(new LootItemCondition[] {
                                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:entities/endermite")).build() }, 
                        ModItems.ENDER_HEART.get())); 
        add("ember_of_eternity_from_wither", new AddItemModifier(new LootItemCondition[] {
                                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:entities/wither")).build() }, 
                        ModItems.EMBER_OF_ETERNITY.get()));  
        add("infernal_core_from_blaze", new AddItemModifier(new LootItemCondition[] {
                                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:entities/blaze")).build() }, 
                        ModItems.INFERNAL_CORE.get()));                                
        



    }
}