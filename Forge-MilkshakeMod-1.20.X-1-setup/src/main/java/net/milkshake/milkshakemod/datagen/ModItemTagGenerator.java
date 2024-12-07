package net.milkshake.milkshakemod.datagen;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, MilkshakeMod.Mod_ID, existingFileHelper); 
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {   
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.LUNATEAN_HELMET.get(),
                        ModItems.LUNATEAN_CHESTPLATE.get(),
                        ModItems.LUNATEAN_LEGGINGS.get(),
                        ModItems.LUNATEAN_BOOTS.get());
        this.tag(ItemTags.MUSIC_DISCS)
                .add(ModItems.BAR_BRAWL_MUSIC_DISC.get());
    }
}