package net.milkshake.milkshakemod.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.damagesource.DamageSource;

public class SoulBenderEntity extends Monster {
    public SoulBenderEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 120.0D)
                .add(Attributes.FOLLOW_RANGE, 32D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 20.0D)
                .add(Attributes.ATTACK_SPEED, 1.0D);
    }

    @Override
    public void die(DamageSource damageSource) {
        super.die(damageSource);
        
        if (!this.level().isClientSide && random.nextFloat() <= 1.0f) { // 0.4% drop chance
            this.spawnAtLocation(ModItems.SOULFIRE_CRYSTAL.get());
        }
        
        // Drop experience
        int experience = 5 + this.level().random.nextInt(5);
        while (experience > 0) {
            int i = ExperienceOrb.getExperienceValue(experience);
            experience -= i;
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY(), this.getZ(), i));
        }
    }
} 