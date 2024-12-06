package net.milkshake.milkshakemod.util;

import net.milkshake.milkshakemod.MilkshakeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");
        public static final TagKey<Block> NEEDS_LUNATEAN_TOOL = tag("needs_lunatean_tool");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(MilkshakeMod.Mod_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> DRAGON_BREATH = null;

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(MilkshakeMod.Mod_ID, name));
        }
    }
}