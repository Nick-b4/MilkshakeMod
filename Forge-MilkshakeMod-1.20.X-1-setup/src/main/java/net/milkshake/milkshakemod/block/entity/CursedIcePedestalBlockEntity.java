package net.milkshake.milkshakemod.block.entity;

import net.milkshake.milkshakemod.screen.CursedIcePedestalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.Containers;
import net.milkshake.milkshakemod.item.ModItems;
import net.milkshake.milkshakemod.entity.ModEntities;
import net.minecraft.world.entity.MobSpawnType;
import java.util.Random;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.particles.ParticleTypes;

public class CursedIcePedestalBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                ItemStack stack = getStackInSlot(0);
                // Check if the placed item is the Crown of the King
                if (stack.getItem() == ModItems.CROWN_OF_THE_KING.get()) {
                    spawnFrostFallenKing();
                    setStackInSlot(0, ItemStack.EMPTY); // Remove the crown
                }
            }
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    public CursedIcePedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CURSED_ICE_PEDESTAL_BE.get(), pos, state);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.milkshakemod.cursed_ice_pedestal");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new CursedIcePedestalMenu(id, inventory, this, new SimpleContainerData(1));
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public ItemStack getRenderStack() {
        return itemHandler.getStackInSlot(0);
    }

    public void setItem(ItemStack itemStack) {
        itemHandler.setStackInSlot(0, itemStack);
    }

    private void spawnFrostFallenKing() {
        if (level != null && !level.isClientSide()) {
            // Get the pedestal's position
            BlockPos pedestalPos = this.getBlockPos();
            
            // Generate random offset between 10-15 blocks
            Random random = new Random();
            int xOffset = random.nextInt(6) + 10; // Random number between 10-15
            int zOffset = random.nextInt(6) + 10; // Random number between 10-15
            
            // Randomly make the offsets negative or positive
            if (random.nextBoolean()) xOffset *= -1;
            if (random.nextBoolean()) zOffset *= -1;
            
            BlockPos spawnPos = worldPosition.offset(xOffset, 1, zOffset);
            
            // Find the highest block at the spawn position
            BlockPos finalSpawnPos = level.getHeightmapPos(
                net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, 
                spawnPos
            );

            // Spawn the boss using the correct method signature
            ModEntities.FROST_FALLEN_KING.get().spawn(
                (ServerLevel)level, 
                finalSpawnPos,
                MobSpawnType.TRIGGERED
            );

            // Add effects
            level.playSound(null, pedestalPos, SoundEvents.WITHER_SPAWN, 
                SoundSource.HOSTILE, 1.0F, 1.0F);
            
            ((ServerLevel) level).sendParticles(ParticleTypes.SNOWFLAKE,
                finalSpawnPos.getX(), finalSpawnPos.getY(), finalSpawnPos.getZ(),
                50, 1.0, 1.0, 1.0, 0.1);
        }
    }
}
