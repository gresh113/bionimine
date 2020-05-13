package com.github.gresh113.bionimine.objects.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;
import com.github.gresh113.bionimine.inventory.container.KanohiPedestalContainer;
import com.github.gresh113.bionimine.objects.blocks.KanohiPedestalBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LecternBlock;
import net.minecraft.command.CommandSource;
import net.minecraft.command.ICommandSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WrittenBookItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class KanohiPedestalTileEntity extends LockableLootTileEntity implements IClearable, INamedContainerProvider {
	private ItemStack mask = ItemStack.EMPTY;
	protected int numPlayersUsing;
	private NonNullList<ItemStack> pedestalContents = NonNullList.withSize(1, ItemStack.EMPTY);
	private IItemHandlerModifiable items = createHandler();
	private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory() {
		return 1;
	}

	public boolean isEmpty() {
		return KanohiPedestalTileEntity.this.mask.isEmpty();
	}

	public ItemStack getStackInSlot(int index) {
		return index == 0 ? KanohiPedestalTileEntity.this.mask : ItemStack.EMPTY;
	}

	public ItemStack decrStackSize(int index, int count) {
		if (index == 0) {
			ItemStack itemstack = KanohiPedestalTileEntity.this.mask.split(count);
			if (KanohiPedestalTileEntity.this.mask.isEmpty()) {
				KanohiPedestalTileEntity.this.maskRemoved();
			}

			return itemstack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	public ItemStack removeStackFromSlot(int index) {
		if (index == 0) {
			ItemStack itemstack = KanohiPedestalTileEntity.this.mask;
			KanohiPedestalTileEntity.this.mask = ItemStack.EMPTY;
			KanohiPedestalTileEntity.this.maskRemoved();
			return itemstack;
		} else {
			return ItemStack.EMPTY;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int index, ItemStack stack) {
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be 64,
	 * possibly will be extended.
	 */
	public int getInventoryStackLimit() {
		return 1;
	}

//	public void markDirty() {
//		this.markDirty();
//	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (KanohiPedestalTileEntity.this.world
				.getTileEntity(KanohiPedestalTileEntity.this.pos) != KanohiPedestalTileEntity.this) {
			return false;
		} else {
			return player.getDistanceSq((double) KanohiPedestalTileEntity.this.pos.getX() + 0.5D,
					(double) KanohiPedestalTileEntity.this.pos.getY() + 0.5D,
					(double) KanohiPedestalTileEntity.this.pos.getZ() + 0.5D) > 64.0D ? false
							: KanohiPedestalTileEntity.this.hasMask();
		}
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot. For guis use Slot.isItemValid
	 */
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return false;
	}

	public KanohiPedestalTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	public KanohiPedestalTileEntity() {
		this(BionimineTileEntityTypes.KANOHI_PEDESTAL.get());
	}

	public ItemStack getMask() {
		return this.mask;
	}

	/**
	 * True if the item in this pedestal is a mask. False if it is another item
	 * (i.e. an empty stack)
	 */
	public boolean hasMask() {
		ResourceLocation masks = new ResourceLocation("bionimine", "items");
		Item item = this.mask.getItem();
		boolean isInGroup = ItemTags.getCollection().getOrCreate(masks).contains(item);
		return isInGroup;
	}

	/**
	 * Sets the item stack in this pedestal. Note that this does not update the
	 * block state; use {@link KanohiPedestalBlock#tryPlacemask} for that.
	 */
	public void setMask(ItemStack stack) {
		this.setMask(stack, (PlayerEntity) null);
	}

	private void maskRemoved() {
		KanohiPedestalBlock.setHasMask(this.getWorld(), this.getPos(), this.getBlockState(), false);
	}

	/**
	 * Sets the item stack in this pedestal. Note that this does not update the
	 * block state; use {@link LecternBlock#tryPlaceBook} for that.
	 */
	public void setMask(ItemStack stack, @Nullable PlayerEntity player) {
		this.mask = this.ensureResolved(stack, player);
		this.markDirty();
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.pedestalContents;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> itemsIn) {
		this.pedestalContents = itemsIn;

	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("containers.kanohi_pedestal");
	}

	public int getComparatorSignalLevel() {
		return this.hasMask() ? 15 : 0;
	}

	/**
	 * Resolves the passed mask, if it is a mask and has not yet been resolved.
	 */
	private ItemStack ensureResolved(ItemStack stack, @Nullable PlayerEntity player) {
		ResourceLocation masks = new ResourceLocation("bionimine", "items");
		Item item = stack.getItem();
		boolean isMask = ItemTags.getCollection().getOrCreate(masks).contains(item);
		if (this.world instanceof ServerWorld && isMask) {
			WrittenBookItem.resolveContents(stack, this.createCommandSource(player), player);
		}

		return stack;
	}

	/**
	 * Creates a CommandSource for resolving the contents of a maks. If the player
	 * is null, a fake commandsource for this Kanohi Pedestal is used.
	 */
	private CommandSource createCommandSource(@Nullable PlayerEntity player) {
		String s;
		ITextComponent itextcomponent;
		if (player == null) {
			s = "KanohiPedestal";
			itextcomponent = new StringTextComponent("KanohiPedestal");
		} else {
			s = player.getName().getString();
			itextcomponent = player.getDisplayName();
		}

		Vec3d vec3d = new Vec3d((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
				(double) this.pos.getZ() + 0.5D);
		return new CommandSource(ICommandSource.DUMMY, vec3d, Vec2f.ZERO, (ServerWorld) this.world, 2, s,
				itextcomponent, this.world.getServer(), player);
	}

	@Override
	public boolean receiveClientEvent(int id, int type) {
		if (id == 1) {
			this.numPlayersUsing = type;
			return true;
		} else {
			return super.receiveClientEvent(id, type);
		}
	}

	@Override
	public void openInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			if (this.numPlayersUsing < 0) {
				this.numPlayersUsing = 0;
			}

			++this.numPlayersUsing;
			this.onOpenOrClose();
		}
	}

	@Override
	public void closeInventory(PlayerEntity player) {
		if (!player.isSpectator()) {
			--this.numPlayersUsing;
			this.onOpenOrClose();
		}
	}

	protected void onOpenOrClose() {
		Block block = this.getBlockState().getBlock();
		if (block instanceof KanohiPedestalBlock) {
			this.world.addBlockEvent(this.pos, block, 1, this.numPlayersUsing);
			this.world.notifyNeighborsOfStateChange(this.pos, block);
		}
	}

	public static int getPlayersUsing(IBlockReader reader, BlockPos pos) {
		BlockState blockstate = reader.getBlockState(pos);
		if (blockstate.hasTileEntity()) {
			TileEntity tileentity = reader.getTileEntity(pos);
			if (tileentity instanceof KanohiPedestalTileEntity) {
				return ((KanohiPedestalTileEntity) tileentity).numPlayersUsing;
			}
		}
		return 0;
	}

	public static void swapContents(KanohiPedestalTileEntity te, KanohiPedestalTileEntity otherTe) {
		NonNullList<ItemStack> list = te.getItems();
		te.setItems(otherTe.getItems());
		otherTe.setItems(list);
	}

	@Override
	public void updateContainingBlockInfo() {
		super.updateContainingBlockInfo();
		if (this.itemHandler != null) {
			this.itemHandler.invalidate();
			this.itemHandler = null;
		}
	}

	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nonnull Direction side) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return itemHandler.cast();
		}
		return super.getCapability(cap, side);
	}

	private IItemHandlerModifiable createHandler() {
		return new InvWrapper(this);
	}

	public void read(CompoundNBT compound) {
		super.read(compound);
		this.pedestalContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound)) {
			ItemStackHelper.loadAllItems(compound, this.pedestalContents);
		}

	}

	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.pedestalContents);
		}
		return compound;
	}

	public void clear() {
		this.setMask(ItemStack.EMPTY);
	}

	@Override
	public Container createMenu(int windowId, PlayerInventory playerInventory) {
		return new KanohiPedestalContainer(windowId, playerInventory, this);
	}

	public ITextComponent getDisplayName() {
		return new TranslationTextComponent("container.kanohi_pedestal");
	}
	
	
	@Override
	public void remove() {
		super.remove();
		if(itemHandler != null) {
			itemHandler.invalidate();
		}
	}

}
