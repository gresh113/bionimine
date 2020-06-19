package com.github.gresh113.bionimine.inventory.container;

import java.util.Objects;

import com.github.gresh113.bionimine.init.BlockInit;
import com.github.gresh113.bionimine.objects.tileentity.KanohiPedestalTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

public class KanohiPedestalContainer extends Container {

	public final KanohiPedestalTileEntity pedestalTileEntity;
	private final IWorldPosCallable canInteractWithCallable;
	//public Item displayItem;

	public KanohiPedestalContainer(final int windowId, final PlayerInventory playerInventory,
			final KanohiPedestalTileEntity tileEntity) {
		super(BionimineContainerTypes.PEDESTAL.get(), windowId);
		this.pedestalTileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

		// Pedestal Inventory
		int startX = 35;
		int startY = 20;
		int slotSizePlus2 = 18;
		for (int column = 0; column < 6; ++column) {
			this.addSlot(new MaskSlot(tileEntity, column, startX + (column * slotSizePlus2), startY));
		}

		// Player Inventory
		int startPlayerInvX = 8;
		int startPlayerInvY = 51;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column,
						startPlayerInvX + (column * slotSizePlus2), startPlayerInvY + (row * slotSizePlus2)));
			}
		}

		// Hotbar
		int hotbarY = 109;
		for (int column = 0; column < 9; ++column) {
			this.addSlot(new Slot(playerInventory, column, startPlayerInvX + (column * slotSizePlus2), hotbarY));
		}
	}

	private static KanohiPedestalTileEntity getTileEntity(final PlayerInventory playerInventory,
			final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof KanohiPedestalTileEntity) {
			return (KanohiPedestalTileEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

	public KanohiPedestalContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.kanohi_pedestal.get());
	}
	
	

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < 6) {
				if (!this.mergeItemStack(itemstack1, 6, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, 6, false)) {
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
