package net.milkshake.milkshakemod.datagen;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.item.ModItems;
import net.milkshake.milkshakemod.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
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

        add("lunatean_from_blaze", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:entities/blaze")).build() },
                ModItems.LUNATEAN.get()));

        add("ember_of_eternity_from_wither", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:entities/wither")).build() }, 
                ModItems.EMBER_OF_ETERNITY.get()));

        add("metal_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("minecraft:chests/jungle_temple")).build() }, 
                ModItems.METAL_DETECTOR.get()));

        add("soulfire_crystal_from_soul_bender", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("milkshakemod:entities/soul_bender")).build() }, 
                ModItems.SOULFIRE_CRYSTAL.get()));
    }
}