package net.milkshake.milkshakemod.item;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier LUNATEAN = TierSortingRegistry.registerTier(
            new ForgeTier(5, -1, 5f, 24f, 30,
                    ModTags.Blocks.NEEDS_LUNATEAN_TOOL, () -> Ingredient.of(ModItems.LUNATEAN.get())),
            new ResourceLocation(MilkshakeMod.Mod_ID, "lunatean"), List.of(Tiers.NETHERITE), List.of());

}