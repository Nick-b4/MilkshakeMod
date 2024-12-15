package net.milkshake.milkshakemod.entity.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.milkshake.milkshakemod.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.milkshake.milkshakemod.entity.ModEntities;
import net.minecraft.ChatFormatting;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.item.ItemStack;
import net.milkshake.milkshakemod.item.ModItems;

public class ItachiEntity extends Monster { 
    private static final EntityDimensions DIMENSIONS = EntityDimensions.fixed(0.6F, 1.8F);

    private int shadowCloneSpawnCooldown = 0;
    private static final int CLONE_SPAWN_COOLDOWN = 200; // 10 seconds
    private static final int MAX_NEARBY_CLONES = 3; // Maximum number of clones at once

    private boolean isShadowClone = false;

    public ItachiEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 500;
    }

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    private final ServerBossEvent bossEvent =
            (ServerBossEvent) new ServerBossEvent(Component.literal("Itachi"), BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.PROGRESS)
                    .setPlayBossMusic(false)
                    .setDarkenScreen(true)
                    .setCreateWorldFog(true);

    private boolean hasPlayedSpawnSound = false;

    @Override
    public void tick() {
        super.tick();

        if (!hasPlayedSpawnSound && !level().isClientSide() && !isShadowClone) {
            this.playSound(ModSounds.ITACHI_SPAWN.get(), 1.0F, 1.0F);
            hasPlayedSpawnSound = true;
        }

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.isMoving()) {
            walkAnimationState.startIfStopped(this.tickCount);
            idleAnimationState.stop();
        } else {
            walkAnimationState.stop();
            idleAnimationState.startIfStopped(this.tickCount);
        }
    }

    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6;
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
        this.goalSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.8D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 16f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25000D)
                .add(Attributes.FOLLOW_RANGE, 32D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR_TOUGHNESS, 10f)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5f)
                .add(Attributes.ATTACK_DAMAGE, 95f)
                .add(Attributes.ATTACK_SPEED, 1.5D);
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
        return ModSounds.ITACHI_SPAWN.get();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        return DIMENSIONS;
    }

    // Boss Bar
    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        if (!isShadowClone) {
            this.bossEvent.addPlayer(pServerPlayer);
            playBossMusic(pServerPlayer);
        }
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        if (!isShadowClone) {
            this.bossEvent.removePlayer(pServerPlayer);
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!isShadowClone) {
            this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
            
            if (!this.level().isClientSide() && this.tickCount % 20 == 0) {
                for (ServerPlayer player : ((ServerLevel)this.level()).getPlayers(p -> true)) {
                    if (!this.bossEvent.getPlayers().contains(player) && 
                        player.distanceToSqr(this) <= 64.0D * 64.0D) {
                        this.bossEvent.addPlayer(player);
                    } else if (player.distanceToSqr(this) > 64.0D * 64.0D) {
                        this.bossEvent.removePlayer(player);
                    }
                }
            }
        }
        
        if (shadowCloneSpawnCooldown > 0) {
            shadowCloneSpawnCooldown--;
        }
        
        if (this.getTarget() != null && this.getHealth() < this.getMaxHealth() * 0.75F) {
            trySpawnShadowClone();
        }
    }

    private void playBossMusic(ServerPlayer player) {
        if (player != null && player.distanceToSqr(this) <= 64.0D * 64.0D) {
            player.playNotifySound(ModSounds.ITACHI_MUSIC.get(), 
                SoundSource.MUSIC,
                1.0F,
                1.0F
            );
        }
    }

    @Override
    public void handleEntityEvent(byte pId) {
        if (pId == 4) {
            // Add smoke particles for clone spawn effect
            for(int i = 0; i < 20; ++i) {
                double d0 = this.random.nextGaussian() * 0.02D;
                double d1 = this.random.nextGaussian() * 0.02D;
                double d2 = this.random.nextGaussian() * 0.02D;
                this.level().addParticle(
                    ParticleTypes.SMOKE, 
                    this.getRandomX(1.0D), 
                    this.getRandomY(), 
                    this.getRandomZ(1.0D), 
                    d0, d1, d2
                );
            }
        } else {
            super.handleEntityEvent(pId);
        }
    }

    @SuppressWarnings("null")
    private void trySpawnShadowClone() {
        if (this.level().isClientSide() || shadowCloneSpawnCooldown > 0) {
            return;
        }

        // Count existing nearby clones
        int nearbyClones = 0;
        for (Entity entity : this.level().getEntitiesOfClass(ItachiShadowCloneEntity.class, 
                this.getBoundingBox().inflate(16.0D))) {
            nearbyClones++;
        }

        if (nearbyClones >= MAX_NEARBY_CLONES) {
            return;
        }

        shadowCloneSpawnCooldown = CLONE_SPAWN_COOLDOWN;

        // Create a shadow clone using the new entity type
        ItachiShadowCloneEntity shadowClone = ModEntities.ITACHI_SHADOW_CLONE.get().create(this.level());
        if (shadowClone != null) {
            shadowClone.setPos(this.getX() + (random.nextDouble() - 0.5) * 2, 
                              this.getY(), 
                              this.getZ() + (random.nextDouble() - 0.5) * 2);
            
            this.level().broadcastEntityEvent(this, (byte)4);
            this.level().addFreshEntity(shadowClone);
        }
    }

    @Override
    public Component getName() {
        return Component.translatable("entity.milkshakemod.itachi")
                .withStyle(ChatFormatting.RED);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        
        ItemStack fragmentOfShadows = new ItemStack(ModItems.FRAGMENT_OF_SHADOWS.get(), 1);
        this.spawnAtLocation(fragmentOfShadows);
    }

}