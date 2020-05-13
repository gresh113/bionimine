package com.github.gresh113.bionimine.inventory.container;

import java.util.Objects;

import com.github.gresh113.bionimine.init.BlockInit;
import com.github.gresh113.bionimine.objects.tileentity.KanohiPedestalTileEntity;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.ResourceLocation;

public class KanohiPedestalContainer extends Container {

	public final KanohiPedestalTileEntity pedestalTileEntity;
	private final IWorldPosCallable canInteractWithCallable;

	public KanohiPedestalContainer(final int windowId, final PlayerInventory playerInventory, final KanohiPedestalTileEntity tileEntity) {
		super(BionimineContainerTypes.PEDESTAL.get(), windowId);
		this.pedestalTileEntity = tileEntity;
		this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), pedestalTileEntity.getPos());

		// Main Inventory
		int startX = 80;
		int startY = 20;
		// int slotSizePlus2 = 18;
		this.addSlot(new Slot(pedestalTileEntity, 0, startX, startY));
		/**
		 * for (int row = 0; row < 1; ++row) { for (int column = 0; column < 1;
		 * ++column) { this.addSlot(new Slot(pedestalTileEntity, (row * 1) + column,
		 * startX + (column*slotSizePlus2), startY + (row*slotSizePlus2)); } }
		 */
		// Main Player Inventory
		int startPlayerInvY = 51;
		int startPlayerInvX = 8;
		for (int row = 0; row < 3; ++row) {
			for (int column = 0; column < 9; ++column) {
				this.addSlot(new Slot(playerInventory, column + (row * 9) + 9, startPlayerInvX + (column * 18),
						startPlayerInvY + (row * 18)));
			}
		}

		// Hotbar
		int hotbarY = 109;
		int hotbarX = 8;
		for (int hotbar = 0; hotbar < 9; ++hotbar) {
			this.addSlot(new Slot(playerInventory, hotbar, hotbarX + (hotbar * 18), hotbarY));
		}

	}

	private static KanohiPedestalTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
		Objects.requireNonNull(data, "data cannot be null");
		final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
		if (tileAtPos instanceof KanohiPedestalTileEntity) {
			return (KanohiPedestalTileEntity) tileAtPos;
		}
		throw new IllegalStateException("Tile entity is not correct!" + tileAtPos);
	}

	public KanohiPedestalContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
		this(windowId, playerInventory, getTileEntity(playerInventory, data));
	}

	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return isWithinUsableDistance(canInteractWithCallable, playerIn, BlockInit.kanohi_pedestal.get());
	}

	
//	  public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
//	  ItemStack itemstack = ItemStack.EMPTY; ResourceLocation masks = new
//	  ResourceLocation("bionimine", "items"); Item item = itemstack.getItem();
//	  boolean isInGroup =
//	  ItemTags.getCollection().getOrCreate(masks).contains(item);
//	  
//	  Slot slot = this.inventorySlots.get(index); if (slot != null &&
//	  slot.getHasStack() && isInGroup) { ItemStack itemstack1 = slot.getStack();
//	  itemstack = itemstack1.copy(); if (index <
//	  this.pedestalTileEntity.getSizeInventory()) { if
//	  (!this.mergeItemStack(itemstack1, this.pedestalTileEntity.getSizeInventory(),
//	  this.inventorySlots.size(), true)) { return ItemStack.EMPTY; } } else if
//	  (!this.mergeItemStack(itemstack1, 0,
//	  this.pedestalTileEntity.getSizeInventory(), false)) { return ItemStack.EMPTY;
//	  }
//	  
//	  if (itemstack1.isEmpty()) { slot.putStack(ItemStack.EMPTY); } else {
//	  slot.onSlotChanged(); } }
//	  
//	  return itemstack; }
	 
	/**
	    * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player
	    * inventory and the other inventory(s).
	    */
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
	      ItemStack itemstack = ItemStack.EMPTY;
	      Slot slot = this.inventorySlots.get(index);
	      ResourceLocation masks = new ResourceLocation("bionimine", "items"); 
	      
	      if (slot != null && slot.getHasStack()) {
	         ItemStack itemstack1 = slot.getStack();
	         itemstack = itemstack1.copy();
	         Item item = itemstack.getItem();
	         boolean isInGroup =  ItemTags.getCollection().getOrCreate(masks).contains(item);
	         
	         if (index < 1 && isInGroup) {
	            if (!this.mergeItemStack(itemstack1, 2, 38, true)) {
	               return ItemStack.EMPTY;
	            }
	         }
	         else if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemstack1.isEmpty()) {
	            slot.putStack(ItemStack.EMPTY);
	         } 
	         else {
	            slot.onSlotChanged();
	         }

	         if (itemstack1.getCount() == itemstack.getCount()) {
	            return ItemStack.EMPTY;
	         }

	         slot.onTake(playerIn, itemstack1);
	      }

	      return itemstack;
	   }
}
