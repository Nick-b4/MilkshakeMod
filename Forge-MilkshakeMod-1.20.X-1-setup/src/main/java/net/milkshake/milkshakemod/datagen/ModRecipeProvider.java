package net.milkshake.milkshakemod.datagen;

import net.milkshake.milkshakemod.MilkshakeMod;     
import net.milkshake.milkshakemod.block.ModBlocks;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> LUNATEAN_SMELTABLES = List.of(ModItems.RAW_LUNATEAN.get(), 
            ModBlocks.LUNATEAN_ORE.get(), ModBlocks.DEEPSLATE_LUNATEAN_ORE.get(), ModBlocks.NETHER_LUNATEAN_ORE.get(),
            ModBlocks.END_STONE_LUNATEAN_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, LUNATEAN_SMELTABLES, RecipeCategory.MISC, ModItems.LUNATEAN.get(), 0.25f, 200, "lunatean");
        oreBlasting(pWriter, LUNATEAN_SMELTABLES, RecipeCategory.MISC, ModItems.LUNATEAN.get(), 0.25f, 100, "lunatean");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LUNATEAN_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.LUNATEAN.get())
                .unlockedBy(getHasName(ModItems.LUNATEAN.get()), has(ModItems.LUNATEAN.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LUNATEAN.get(), 9)
                .requires(ModBlocks.LUNATEAN_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LUNATEAN_BLOCK.get()), has(ModBlocks.LUNATEAN_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.LUNATEAN_PLANKS.get(), 4)
                .requires(ModBlocks.LUNATEAN_LOG.get())
                .unlockedBy(getHasName(ModBlocks.LUNATEAN_LOG.get()), has(ModBlocks.LUNATEAN_LOG.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BOTTLE_OF_BLACK_FLAME.get())
                .requires(Items.DRAGON_BREATH)
                .requires(ModItems.EYES_OF_A_FRIEND.get())
                .unlockedBy("has_eyes_of_friend", has(ModItems.EYES_OF_A_FRIEND.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  MilkshakeMod.Mod_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike)); 
        }
    }
}