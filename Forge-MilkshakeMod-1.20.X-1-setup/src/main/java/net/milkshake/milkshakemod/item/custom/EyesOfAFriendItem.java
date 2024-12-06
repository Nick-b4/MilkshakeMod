package net.milkshake.milkshakemod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class EyesOfAFriendItem extends Item {
    public EyesOfAFriendItem(Properties properties) {
        super(properties.stacksTo(1).rarity(net.minecraft.world.item.Rarity.EPIC));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        components.add(Component.translatable("tooltip.milkshakemod.eyes_of_a_friend.tooltip"));
        super.appendHoverText(stack, level, components, flag);
    }
} 