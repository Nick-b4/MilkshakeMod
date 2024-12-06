package net.milkshake.milkshakemod.item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ModFoods {
    public static final FoodProperties MILKBERRY = new FoodProperties.Builder().nutrition(6).fast()
            .saturationMod(6.0f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200,3), 1.0f).build();

}