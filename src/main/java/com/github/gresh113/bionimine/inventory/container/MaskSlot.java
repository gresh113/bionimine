//package com.github.gresh113.bionimine.inventory.container;
//
//import com.github.gresh113.bionimine.Bionimine;
//
//import net.minecraft.inventory.IInventory;
//import net.minecraft.inventory.container.Slot;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.tags.ItemTags;
//import net.minecraft.tags.Tag;
//import net.minecraft.util.ResourceLocation;
//
//class MaskSlot extends Slot {
//	private Tag<Item> maskTag = new Tag<Item>(new ResourceLocation(Bionimine.MODID, "masks"));
//	
//
//	public MaskSlot(IInventory inventoryIn, int index, int xIn, int yIn) {
//		super(inventoryIn, index, xIn, yIn);
//	}
//
//	/**
//	 * Check if the stack is allowed to be placed in this slot, used for armor slots
//	 * as well as furnace fuel.
//	 */
//	public boolean isItemValid(ItemStack stack) {
//		final Item item = stack.getItem();
//		final boolean itemIsMask = item.isIn(maskTag);
//		return itemIsMask;
//	}
//
//	/**
//	 * Returns the maximum stack size for a given slot (usually the same as
//	 * getInventoryStackLimit(), but 1 in the case of armor slots)
//	 */
//	public int getSlotStackLimit() {
//		return 1;
//	}
//}
