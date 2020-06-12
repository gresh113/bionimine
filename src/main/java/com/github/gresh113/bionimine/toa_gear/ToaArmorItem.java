package com.github.gresh113.bionimine.toa_gear;

import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.toa_gear.kanohi.ArmorPalette;
import com.github.gresh113.bionimine.toa_gear.kanohi.KanohiItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ToaArmorItem extends ArmorItem {
	private static EquipmentSlotType defaultSlot = EquipmentSlotType.CHEST;
	private static ArmorMaterial material = ArmorMaterial.IRON;
	Elements element;
	private ArmorPalette color;
	private static final Item.Properties defaultProperties = new Item.Properties().group(BioniMineItemGroup.instance).maxStackSize(1);

	public ToaArmorItem(Properties propertiesIn, ArmorPalette colorIn) {
		super(material, defaultSlot, propertiesIn);
		this.color = colorIn;
		this.element = Elements.NONE;
	}

	public ToaArmorItem(Properties propertiesIn) {
		super(material, defaultSlot, propertiesIn);
		this.color = ArmorPalette.GRAY;
		this.element = Elements.NONE;
	}

	public ToaArmorItem(ArmorPalette colorIn) {
		super(material, defaultSlot, defaultProperties);
		this.color = colorIn;
		this.element = Elements.NONE;
	}
	
	public ToaArmorItem(ArmorPalette colorIn, Elements elementIn) {
		super(material, defaultSlot, defaultProperties);
		this.color = colorIn;
		this.element = elementIn;
	}

	public ToaArmorItem() {
		super(material, defaultSlot, defaultProperties);
		this.color = ArmorPalette.GRAY;
		this.element = Elements.NONE;
	}

	public static ArmorPalette getPalette(ItemStack stackIn) {
		ArmorPalette defaultPalette = ((ToaArmorItem) stackIn.getItem()).getDefaultColor();

		CompoundNBT compoundNBT = stackIn.getOrCreateTag();
		String tag = "color";
		ArmorPalette stackPalette;
		if (compoundNBT.contains(tag)) {
			stackPalette = ArmorPalette.fromNBT(compoundNBT, tag);
		} else {
			stackPalette = defaultPalette;
			stackPalette.putIntoNBT(compoundNBT, tag);
		}
		return stackPalette;

	}

	public void setPalette(ItemStack stack, ArmorPalette paletteIn) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();
		paletteIn.putIntoNBT(compoundNBT, "color");
	}

	public ArmorPalette getDefaultColor() {
		if (!color.equals(null))
			return color;
		else
			return ArmorPalette.GRAY;
	}
	
	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity playerIn) {
		byte energy = getElementalEnergy(playerIn);
		if (this.element == Elements.FIRE) playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 0, 1, false, false, false));
		if (energy < 0)
			energy = 0;
		energy += 1;
		if (energy > 124)
			energy = 124;
		setElementalEnergy(playerIn, energy);
	}
	
	// Uses capability system to check a given player's current element energy
	public byte getElementalEnergy(PlayerEntity playerIn) {
		IToaEnergy playerCapability = playerIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		return playerCapability.getElementalEnergy();
	}

	public void setElementalEnergy(PlayerEntity playerIn, byte energyIn) {
		IToaEnergy playerCapability = playerIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		playerCapability.setElementalEnergy(energyIn);
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
