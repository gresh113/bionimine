package com.github.gresh113.bionimine.objects.items;

import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;
import com.github.gresh113.bionimine.kanohi.ArmorPalette;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ToaArmorItem extends ArmorItem {
	private static EquipmentSlotType defaultSlot = EquipmentSlotType.CHEST;
	private static ArmorMaterial material = ArmorMaterial.IRON;
	private ArmorPalette color;
	private static final Item.Properties defaultProperties = new Item.Properties().group(BioniMineItemGroup.instance)
			.maxStackSize(1);

	public ToaArmorItem(Properties propertiesIn, ArmorPalette colorIn) {
		super(material, defaultSlot, propertiesIn);
		this.color = colorIn;		
	}
	
	public ToaArmorItem(Properties propertiesIn) {
		super(material, defaultSlot, propertiesIn);
		this.color = ArmorPalette.GRAY;		
	}
	
	public ToaArmorItem(ArmorPalette colorIn) {
		super(material, defaultSlot, defaultProperties);
		this.color = colorIn;		
	}
	public ToaArmorItem() {
		super(material, defaultSlot, defaultProperties);
		this.color = ArmorPalette.GRAY;
		
	}
	
	public ArmorPalette getPalette() {
		return this.color;
	}
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}
}
