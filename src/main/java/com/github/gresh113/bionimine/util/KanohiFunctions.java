package com.github.gresh113.bionimine.util;

import com.github.gresh113.bionimine.BioniMine;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class KanohiFunctions {
	public static boolean isKanohi(ItemStack stackIn) {
		final Tag<Item> maskTag = new ItemTags.Wrapper(new ResourceLocation(BioniMine.MODID, "masks"));
		final Item item = stackIn.getItem();
		final boolean itemIsMask = item.isIn(maskTag);
		return itemIsMask;
	}
}
