package net.milkshake.milkshakemod.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.types.templates.List;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.entity.ModEntities;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_LUNATEAN_ORE = registerKey("add_lunatean_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_LUNATEAN_ORE = registerKey("add_nether_lunatean_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_LUNATEAN_ORE = registerKey("add_end_lunatean_ore");

    public static final ResourceKey<BiomeModifier> ADD_TREE_LUNATEAN = registerKey("add_tree_lunatean");

    public static final ResourceKey<BiomeModifier> SPAWN_SOUL_BENDER = registerKey("spawn_soul_bender");
    public static final ResourceKey<BiomeModifier> SPAWN_VOID_MAGE = registerKey("spawn_void_mage");


    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_LUNATEAN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LUNATEAN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_NETHER_LUNATEAN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_LUNATEAN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_END_LUNATEAN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(        
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_LUNATEAN_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_TREE_LUNATEAN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.IS_PLAINS),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LUNATEAN_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION));

                context.register(SPAWN_SOUL_BENDER, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                        HolderSet.direct(biomes.getOrThrow(Biomes.SOUL_SAND_VALLEY)),
                        ImmutableList.of(new MobSpawnSettings.SpawnerData(ModEntities.SOUL_BENDER.get(), 5, 1, 3))));

                context.register(SPAWN_VOID_MAGE, new ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                        HolderSet.direct(biomes.getOrThrow(Biomes.END_HIGHLANDS)),
                        ImmutableList.of(new MobSpawnSettings.SpawnerData(ModEntities.VOID_MAGE.get(), 5, 1, 3))));
    }

       
    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(MilkshakeMod.Mod_ID, name));
    }
}