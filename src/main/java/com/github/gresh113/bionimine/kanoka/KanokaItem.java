package com.github.gresh113.bionimine.kanoka;

import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;
import com.github.gresh113.bionimine.BioniMine.KanohiItemGroup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class KanokaItem extends Item {
	private static final Item.Properties defaultProperties = new Item.Properties().group(BioniMineItemGroup.instance)
			.maxStackSize(16);

	// Constructors
	public KanokaItem(Properties properties) {super(properties);}

	public KanokaItem() {super(defaultProperties);}

	public static KanokaMetru getMetru(ItemStack stack) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();
		return KanokaMetru.fromNBT(compoundNBT, "metru");
		}

	public void setMetru(ItemStack stack, KanokaMetru metruIn) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();
		metruIn.putIntoNBT(compoundNBT, "metru");
		}

	public static int powerLevelFromNBT(ItemStack stack) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();
		String tag = "power_level";
		
		int num = 1; // Default
		if (compoundNBT != null && compoundNBT.contains(tag)) {
			num = compoundNBT.getInt(tag);
			}
		return num;
		}

	public void putPowerLevelIntoNBT(ItemStack stack, int powerLevel) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();
		String tag = "power_level";
		compoundNBT.putInt(tag, powerLevel);
		}

}
