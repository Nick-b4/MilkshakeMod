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

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.LUNATEAN_PAXEL.get())
            .pattern("PAS")
            .pattern(" R ")
            .pattern(" R ")
            .define('P', ModItems.LUNATEAN_PICKAXE.get())
            .define('A', ModItems.LUNATEAN_AXE.get())
            .define('S', ModItems.LUNATEAN_SHOVEL.get())
            .define('R', Items.STICK)
            .unlockedBy(getHasName(ModItems.LUNATEAN_PICKAXE.get()), has(ModItems.LUNATEAN_PICKAXE.get()))
            .unlockedBy(getHasName(ModItems.LUNATEAN_AXE.get()), has(ModItems.LUNATEAN_AXE.get()))
            .unlockedBy(getHasName(ModItems.LUNATEAN_SHOVEL.get()), has(ModItems.LUNATEAN_SHOVEL.get()))
            .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
            .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ANCIENT_WOOD_LOG.get())
            .pattern("LLL")
            .pattern("LSL")
            .pattern("LLL")
            .define('L', ModBlocks.LUNATEAN_LOG.get())
            .define('S', Items.NETHER_STAR)
            .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
            .unlockedBy(getHasName(ModBlocks.LUNATEAN_LOG.get()), has(ModBlocks.LUNATEAN_LOG.get()))
            .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.FRAGMENT_OF_THE_VOID.get())
            .pattern("SSS")
            .pattern("SES")
            .pattern("SSS")
            .define('S', Items.NETHER_STAR)
            .define('E', ModItems.ECHO_OF_OBLIVION.get())
            .unlockedBy(getHasName(Items.NETHER_STAR), has(Items.NETHER_STAR))
            .unlockedBy(getHasName(ModItems.ECHO_OF_OBLIVION.get()), has(ModItems.ECHO_OF_OBLIVION.get()))
            .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAR_OF_YULE.get())
            .pattern("FLH")
            .pattern("LLL")
            .pattern("SLE")
            .define('F', ModItems.FRAGMENT_OF_SHADOWS.get())
            .define('S', ModItems.BLAZING_EMBER.get())
            .define('H', ModItems.HEART_OF_THE_FOREST.get())
            .define('E', ModItems.ESSENCE_OF_THE_ABYSS.get())
            .define('L', ModBlocks.LUNATEAN_BLOCK.get())
            .unlockedBy("has_fragment_of_shadows", has(ModItems.FRAGMENT_OF_SHADOWS.get()))
            .unlockedBy("has_blazing_ember", has(ModItems.BLAZING_EMBER.get()))
            .unlockedBy("has_heart_of_forest", has(ModItems.HEART_OF_THE_FOREST.get()))
            .unlockedBy("has_essence_of_abyss", has(ModItems.ESSENCE_OF_THE_ABYSS.get()))
            .unlockedBy("has_lunatean_block", has(ModBlocks.LUNATEAN_BLOCK.get()))
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