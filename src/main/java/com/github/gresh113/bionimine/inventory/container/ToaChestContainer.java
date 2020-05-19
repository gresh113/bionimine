package com.github.gresh113.bionimine.inventory.container;

import java.util.Objects;

import com.github.gresh113.bionimine.init.BlockInit;
import com.github.gresh113.bionimine.objects.tileentity.ToaChestTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class ToaChestContainer extends Container {


	public final ToaChestTileEntity toaChestTileEntity;
	private final IWorldPosCallable canInteractWithCallable;
	//public Item displayItem;

	public ToaChestContainer(final int windowId, final PlayerInventory playerInventory,
			final ToaChestTileEntity tileEntity) {
		super(BionimineContainerTypes.TOA_CHEST.get(), windowId);
		this.toaChestTileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

		// Mask Slot
		this.addSlot(new MaskSlot(tileEntity, 0, 81, 12));
		
		// Other four item slots
		int startX = 54;
		int startY = 34;
		int slotSizePlus2 = 18;
		for (int column = 1; column < 5; ++column) {
			this.addSlot(new Slot(tileEntity, column, startX + (column * slotSizePlus2), startY));
		}

		// Player Inventory
		int startPlayerInvX = 8;
		int startPlayerInvY = 56;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column,
						startPlayerInvX + (column * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
			}
		}

		// Hotbar
		int hotbarY = 114;
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startPlayerInvX + (column * slotSizePlus2), hotbarY));
		}
	}

	private static ToaChestTileEntity getTileEntity(final PlayerInventory playerInventory,
			final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof ToaChestTileEntity) {
			return (ToaChestTileEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	public ToaChestContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.toa_chest.get());
	}
	
	

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 5) {
				if (!this.mergeItemStack(itemstack1, 5, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 5, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}
		return itemstack;

	}
}
