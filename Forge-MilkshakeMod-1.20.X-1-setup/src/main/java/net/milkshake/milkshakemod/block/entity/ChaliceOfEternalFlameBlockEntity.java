package net.milkshake.milkshakemod.block.entity; 

import net.milkshake.milkshakemod.entity.ModEntities;
import net.milkshake.milkshakemod.item.ModItems;
import net.milkshake.milkshakemod.screen.ChaliceOfEternalFlameMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.MobSpawnType;
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

public class ChaliceOfEternalFlameBlockEntity extends BlockEntity implements MenuProvider { 
    private final ItemStackHandler itemHandler = new ItemStackHandler(4) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }           
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public ChaliceOfEternalFlameBlockEntity(BlockPos pPos, BlockState pBlockState) {       
        super(ModBlockEntities.CHALICE_OF_ETERNAL_FLAME_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> ChaliceOfEternalFlameBlockEntity.this.progress;
                    case 1 -> ChaliceOfEternalFlameBlockEntity.this.maxProgress;      
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> ChaliceOfEternalFlameBlockEntity.this.progress = pValue;
                    case 1 -> ChaliceOfEternalFlameBlockEntity.this.maxProgress = pValue;
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
        return Component.literal("Chalice of Eternal Flame");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new ChaliceOfEternalFlameMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("chalice_of_eternal_flame.progress", progress);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("chalice_of_eternal_flame.progress");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        if(!level.isClientSide()) {
            checkForRecipe();
        }
    }

    private void checkForRecipe() {
        // Get items from all slots
        ItemStack slot0 = itemHandler.getStackInSlot(0);
        ItemStack slot1 = itemHandler.getStackInSlot(1);
        ItemStack slot2 = itemHandler.getStackInSlot(2);
        ItemStack slot3 = itemHandler.getStackInSlot(3);

        // Check if the recipe matches:
        // - Infernal Core in slot 0
        // - Soulfire Crystal in slot 1
        // - Ember of Eternity in slot 2
        // - Lunateans in slot 3
        boolean hasCorrectItems = 
            !slot0.isEmpty() && slot0.getItem() == ModItems.INFERNAL_CORE.get() &&
            !slot1.isEmpty() && slot1.getItem() == ModItems.SOULFIRE_CRYSTAL.get() &&
            !slot2.isEmpty() && slot2.getItem() == ModItems.EMBER_OF_ETERNITY.get() &&
            !slot3.isEmpty() && slot3.getItem() == ModItems.LUNATEAN.get();

        if(hasCorrectItems) {
            // Clear all items
            for(int i = 0; i < 4; i++) {
                itemHandler.extractItem(i, 1, false);
            }

            // Spawn Itachi
            if(level != null && !level.isClientSide()) {
                summonInfernalBeing(level, worldPosition);
            }
        }
    }

    private void summonInfernalBeing(Level level, BlockPos pos) {
        // Spawn Infernal Being 10 blocks north of the chalice and 1 block up
        BlockPos spawnPos = pos.offset(0, 1, -10);
        
        ModEntities.INFERNAL_BEING.get().spawn(
            (ServerLevel) level,
            spawnPos,
            MobSpawnType.MOB_SUMMONED
        );
    }

    public Container getInventory() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        return inventory;
    }
}
