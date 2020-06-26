package com.github.gresh113.bionimine.objects.toagear;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;
import com.github.gresh113.bionimine.objects.toagear.kanohi.KanohiItem;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
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
		int energy = getElementalEnergy(playerIn);
		if (this.element == Elements.FIRE)
			playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 0, 1, false, false, false));
		if (energy < 0)
			energy = 0;
		energy += 1;
		if (energy > ToaEnergy.maxElementalEnergy)
			energy = ToaEnergy.maxElementalEnergy;
		setElementalEnergy(playerIn, energy);
	}

	// Uses capability system to check a given player's current element energy
	public int getElementalEnergy(PlayerEntity playerIn) {
		IToaEnergy playerCapability = playerIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		return playerCapability.getElementalEnergy();
	}

	public void setElementalEnergy(PlayerEntity playerIn, int energyIn) {
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

	@Override
	public IArmorMaterial getArmorMaterial() {
		return material;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		if (stack.getItem() instanceof ToaArmorItem) {
			ToaArmorItem armoritem = (ToaArmorItem) stack.getItem();
			return (Bionimine.MODID + ":textures/models/armor/toa_armor/" + armoritem.element.func_176610_l() + ".png");
		}else
			return null;
	}

//	private enum KanohiMaterial implements IArmorMaterial {
//		KANOHI("kanohi", 25, 5, 9, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0F);
//
//		private final String name;
//		private final int maxDamageFactor;
//		private final int damageReductionAmount;
//		private final int enchantability;
//		private final SoundEvent soundEvent;
//		private final float toughness;
//
//		private KanohiMaterial(String nameIn, int maxDamageFactorIn, int damageReductionAmountsIn, int enchantabilityIn, SoundEvent equipSoundIn, float toughnessIn) {
//			this.name = nameIn;
//			this.maxDamageFactor = maxDamageFactorIn;
//			this.damageReductionAmount = damageReductionAmountsIn;
//			this.enchantability = enchantabilityIn;
//			this.soundEvent = equipSoundIn;
//			this.toughness = toughnessIn;
//		}
//
//		@Override
//		public int getDurability(EquipmentSlotType slotIn) {
//			// TODO Auto-generated method stub
//			return 15 * maxDamageFactor;
//		}
//
//		@Override
//		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
//			return damageReductionAmount;
//		}
//
//		@Override
//		public int getEnchantability() {
//			return enchantability;
//		}
//
//		@Override
//		public SoundEvent getSoundEvent() {
//			return soundEvent;
//		}
//
//		@Override
//		public Ingredient getRepairMaterial() {
//			return null;
//		}
//
//		@Override
//		public String getName() {
//			// TODO Auto-generated method stub
//			return name;
//		}
//
//		@Override
//		public float getToughness() {
//			// TODO Auto-generated method stub
//			return toughness;
//		}
//
//		@Override
//		public float func_230304_f_() {
//			// TODO Auto-generated method stub
//			return 0;
//		}
//
//	}

}
