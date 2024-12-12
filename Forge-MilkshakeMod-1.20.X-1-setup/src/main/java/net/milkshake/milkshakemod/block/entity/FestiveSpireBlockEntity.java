package net.milkshake.milkshakemod.block.entity; 

import net.milkshake.milkshakemod.item.ModItems;
import net.milkshake.milkshakemod.screen.FestiveSpireMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.Items;
import net.minecraft.nbt.ListTag;

import static net.minecraft.world.inventory.AnvilMenu.INPUT_SLOT;

public class FestiveSpireBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (!level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return stack.getItem() == ModItems.STAR_OF_YULE.get();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;
    private int fireworkWavesRemaining = 0;
    private int fireworkDelayTicks = 0;
    private static final int FIREWORK_DELAY = 100; // 5 seconds (20 ticks = 1 second)

    public FestiveSpireBlockEntity(BlockPos pPos, BlockState pBlockState) {       
        super(ModBlockEntities.FESTIVE_SPIRE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> FestiveSpireBlockEntity.this.progress;
                    case 1 -> FestiveSpireBlockEntity.this.maxProgress;      
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> FestiveSpireBlockEntity.this.progress = pValue;      
                    case 1 -> FestiveSpireBlockEntity.this.maxProgress = pValue;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
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

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Festive Spire");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FestiveSpireMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("festive_spire.progress", progress);
        pTag.putInt("festive_spire.firework_waves", fireworkWavesRemaining);
        pTag.putInt("festive_spire.firework_delay", fireworkDelayTicks);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("festive_spire.progress");
        fireworkWavesRemaining = pTag.getInt("festive_spire.firework_waves");
        fireworkDelayTicks = pTag.getInt("festive_spire.firework_delay");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(!level.isClientSide()) {
            if (fireworkWavesRemaining > 0) {
                if (fireworkDelayTicks > 0) {
                    fireworkDelayTicks--;
                } else {
                    launchFestiveFireworks(level, worldPosition);
                    fireworkWavesRemaining--;
                    if (fireworkWavesRemaining > 0) {
                        fireworkDelayTicks = FIREWORK_DELAY;
                    }
                }
            }
            checkForRecipe();
        }
    }

    private void checkForRecipe() {
        ItemStack slot0 = itemHandler.getStackInSlot(0);
        
        boolean hasCorrectItems = 
            !slot0.isEmpty() && slot0.getItem() == ModItems.STAR_OF_YULE.get(); 
            
        if(hasCorrectItems && fireworkWavesRemaining == 0) {  // Only start if not already running
            if(level != null && !level.isClientSide()) {
                fireworkWavesRemaining = 4;  // Set number of waves
                launchFestiveFireworks(level, worldPosition);  // Launch first wave immediately
                fireworkWavesRemaining--;  // Decrease count after first wave
                fireworkDelayTicks = FIREWORK_DELAY;  // Set delay for next wave
            }
        }
    }

    private void launchFestiveFireworks(Level level, BlockPos pos) {
        // Create and launch firework rockets in a pattern
        for(int i = 0; i < 12; i++) {
            BlockPos fireworkPos = pos.offset(
                level.random.nextInt(9) - 4,  // Random X offset (-4 to 4)
                1 + level.random.nextInt(2),  // Reduced height variation (1-2 blocks instead of 1-3)
                level.random.nextInt(9) - 4   // Random Z offset (-4 to 4)
            );
            
            FireworkRocketEntity firework = new FireworkRocketEntity(
                level,
                fireworkPos.getX() + 0.5,
                fireworkPos.getY() + 0.5,
                fireworkPos.getZ() + 0.5,
                createFestiveFireworkItem()
            );
            
            // Reduced upward motion
            firework.setDeltaMovement(
                (level.random.nextDouble() - 0.5) * 0.1,  // Random X motion
                0.3 + level.random.nextDouble() * 0.1,    // Reduced upward motion (was 0.5)
                (level.random.nextDouble() - 0.5) * 0.1   // Random Z motion
            );
            
            level.addFreshEntity(firework);
        }
    }

    private ItemStack createFestiveFireworkItem() {
        ItemStack firework = new ItemStack(Items.FIREWORK_ROCKET);
        CompoundTag fireworkTag = new CompoundTag();
        CompoundTag explosionTag = new CompoundTag();
        ListTag explosionsList = new ListTag();
        
        // Configure firework properties
        explosionTag.putByte("Type", (byte)1);
        explosionTag.putIntArray("Colors", new int[]{0xFF0000, 0x00FF00});
        explosionTag.putBoolean("Flicker", true);
        explosionTag.putBoolean("Trail", true);
        
        explosionsList.add(explosionTag);
        
        CompoundTag explosion2Tag = new CompoundTag();
        explosion2Tag.putByte("Type", (byte)2);
        explosion2Tag.putIntArray("Colors", new int[]{0xFFFFFF, 0xFFA500});
        explosion2Tag.putBoolean("Flicker", true);
        explosion2Tag.putBoolean("Trail", true);
        explosionsList.add(explosion2Tag);
        
        fireworkTag.put("Explosions", explosionsList);
        fireworkTag.putByte("Flight", (byte)1);  // Reduced from 2 to 1 for lower height
        
        firework.getOrCreateTag().put("Fireworks", fireworkTag);
        return firework;
    }

    public Container getInventory() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        return inventory;
    }
}