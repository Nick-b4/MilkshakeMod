package net.milkshake.milkshakemod.entity.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.milkshake.milkshakemod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import org.jetbrains.annotations.Nullable;

public class FrostFallenKingEntity extends Monster {
    private static final EntityDimensions DIMENSIONS = EntityDimensions.fixed(0.6F, 1.8F);

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private final ServerBossEvent bossEvent =
            (ServerBossEvent) new ServerBossEvent(Component.literal("Frost Fallen King"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS).setPlayBossMusic(true).setDarkenScreen(true).setCreateWorldFog(true);

    public FrostFallenKingEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 500;
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }

        Level level = this.level();
        if (level.isClientSide) {  // Only spawn particles on client side
            double x = this.getX();
            double y = this.getY();
            double z = this.getZ();
            
            // Create a snow effect in a 5 block radius
            for (int i = 0; i < 5; i++) {
                double offsetX = this.random.nextDouble() * 10 - 5; // Random position in -5 to 5 range
                double offsetZ = this.random.nextDouble() * 10 - 5;
                
                level.addParticle(
                    ParticleTypes.SNOWFLAKE,
                    x + offsetX,  // X position
                    y + 3,        // Y position (3 blocks above entity)
                    z + offsetZ,  // Z position
                    0,            // X velocity
                    -0.1,         // Y velocity (falling snow)
                    0             // Z velocity
                );
            }
        }
    }

    private void setupAnimationStates() {
        if (this.isMoving()) {
            this.idleAnimationState.stop();
            this.walkAnimationState.startIfStopped(this.tickCount);
        } else {
            this.walkAnimationState.stop();
            if(this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                this.idleAnimationState.start(this.tickCount);
            } else {
                --this.idleAnimationTimeout;
            }
        }
    }

    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true) {
            @Override
            protected double getAttackReachSqr(LivingEntity target) {
                return 4.0D + target.getBbWidth();
            }
        });
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 0.7D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 16f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25000D)
                .add(Attributes.FOLLOW_RANGE, 48D)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.ARMOR_TOUGHNESS, 10f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 115f)
                .add(Attributes.ATTACK_SPEED, 2.0D);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SHULKER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENDER_DRAGON_DEATH;
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return DIMENSIONS;
    }

    // Boss Bar
    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public Component getName() {
        return Component.translatable("entity.milkshakemod.frost_fallen_king")
                .withStyle(ChatFormatting.AQUA);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        
        // Drop Star of Yule with 100% chance
        this.spawnAtLocation(ModItems.STAR_OF_YULE.get());
    }
}