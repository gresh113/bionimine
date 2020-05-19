package com.github.gresh113.bionimine.objects.tileentity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.init.BionimineTileEntityTypes;
import com.github.gresh113.bionimine.inventory.container.KanohiPedestalContainer;
import com.github.gresh113.bionimine.objects.blocks.KanohiPedestalBlock;
import com.github.gresh113.bionimine.state.properties.Mask_Type;
import com.github.gresh113.bionimine.util.KanohiFunctions;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.InvWrapper;

public class KanohiPedestalTileEntity extends LockableLootTileEntity implements IClearable, INamedContainerProvider, ITickableTileEntity {
	private NonNullList<ItemStack> pedestalContents = NonNullList.withSize(6, ItemStack.EMPTY);
	protected int numPlayersUsing;
	private IItemHandlerModifiable items = createHandler();
	private LazyOptional<IItemHandlerModifiable> itemHandler = LazyOptional.of(() -> items);

	public KanohiPedestalTileEntity(TileEntityType<?> typeIn) {
		super(typeIn);
	}

	public KanohiPedestalTileEntity() {
		this(BionimineTileEntityTypes.KANOHI_PEDESTAL.get());
	}

	@Override
	public int getSizeInventory() {
		return 6;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.pedestalContents;
	}
	
	@Override
	public void setItems(NonNullList<ItemStack> itemsIn) {
		ItemStack itemStack = itemsIn.get(0);
		if (this.isMask(itemStack)) {this.pedestalContents = itemsIn;}
		else {this.pedestalContents.add(ItemStack.EMPTY);}
		this.markDirty();
	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.kanohi_pedestal");
	}
	
	public boolean isMask(ItemStack stackIn) {
		return KanohiFunctions.isKanohi(stackIn);
	}

	@Nullable 
	public ItemStack getDisplayItem() {
		ItemStack displayItem = ItemStack.EMPTY;
		for (int index = 0; index < 6; ++index) {
			ItemStack stack = pedestalContents.get(index);
			if (stack != null && !stack.isEmpty() && this.isMask(stack)) {
				displayItem = stack;
				break;
			}
		}
		return displayItem;
	}
	@Nullable
	public Mask_Type getDisplayMask()
	{
		ItemStack displayMaskItem = this.getDisplayItem();
		if (this.isMask(displayMaskItem)) {
		Mask_Type displayMask = Mask_Type.valueOf(displayMaskItem.getItem().toString());
		return displayMask;
		}
		else {return Mask_Type.NONE;}
		
	}
	
	public boolean hasMask() {
		return !(this.getDisplayMask() == Mask_Type.NONE || this.getDisplayMask() == null) ? true : false;
	}
	
	@Override
	public void tick() {
		BlockState state = this.getBlockState();
		Mask_Type mask = Mask_Type.NONE;
		if (this.hasMask()) {
			mask = this.getDisplayMask();
		}	
		this.world.setBlockState(this.pos, state.with(KanohiPedestalBlock.MASK, mask), 2);
	}

	@Override
	protected Container createMenu(int id, PlayerInventory player) {
		return new KanohiPedestalContainer(id, player, this);
	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		if (!this.checkLootAndWrite(compound)) {
			ItemStackHelper.saveAllItems(compound, this.pedestalContents);
		}
		return compound;
	}

	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		this.pedestalContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		if (!this.checkLootAndRead(compound)) {
			ItemStackHelper.loadAllItems(compound, this.pedestalContents);
		}
	}

//	private void playSound(SoundEvent sound) {
//		double dx = (double) this.pos.getX() + 0.5D;
//		double dy = (double) this.pos.getY() + 0.5D;
//		double dz = (double) this.pos.getZ() + 0.5D;
//		this.world.playSound((PlayerEntity) null, dx, dy, dz, sound, SoundCategory.BLOCKS, 0.5f,
//				this.world.rand.nextFloat() * 0.1f + 0.9f);
//	}

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
	
	@Override
	public void remove() {
		super.remove();
		if(itemHandler != null) {
			itemHandler.invalidate();
		}
	}

	
}
