package com.github.gresh113.bionimine.objects.items;

import javax.annotation.ParametersAreNonnullByDefault;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeItem;

public class KanohiItem extends ArmorItem implements IForgeItem {

	public KanohiItem(Properties builder) {
		this(KanohiMaterial.hau, EquipmentSlotType.HEAD, builder);
	}

	public KanohiItem(EquipmentSlotType slot, Properties builder) {
		this(KanohiMaterial.hau, slot, builder);
	}

	public KanohiItem(IArmorMaterial materialIn) {
		this(materialIn, EquipmentSlotType.HEAD,
				new Item.Properties().group(BioniMineItemGroup.instance).maxStackSize(1));
	}

	public KanohiItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}


	@Override
	public void onArmorTick(ItemStack stack, World world, PlayerEntity playerIn) {
		Item currentMask = playerIn.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem();
		KanohiItem currentKanohi = (KanohiItem) currentMask;
		IArmorMaterial kanohiType = currentKanohi.getArmorMaterial();
		if (kanohiType == KanohiMaterial.kaukau) {
			playerIn.setAir(playerIn.getMaxAir());
		}
		if (kanohiType == KanohiMaterial.kakama) {
			playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 1, 3));
		}
		if (kanohiType == KanohiMaterial.miru) {
			playerIn.addPotionEffect(new EffectInstance(Effects.LEVITATION, 1, -1));
		}

	}
	
	public enum KanohiMaterial implements IArmorMaterial {
		hau("hau", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		miru("miru", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		akaku("akaku", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		kaukau("kaukau", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		pakari("pakari", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		kakama("kakama", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		vahi("vahi", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		avohkii("avokhii", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY),
		kkrahkhan("krahkhan", 0, new int[] { 0, 0, 0, 0 }, 0, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0.0f, Ingredient.EMPTY);

		private String name;
		private SoundEvent equipSound;
		private int durability, enchantability;
		private final int[] damageReductionAmountArray;
		private float toughness;

		KanohiMaterial(String name, int durability, int[] damageReduction, int enchantability, SoundEvent sound,
				float toughness, Ingredient empty) {
			this.name = name;
			this.equipSound = sound;
			this.durability = durability;
			this.enchantability = enchantability;
			this.damageReductionAmountArray = damageReduction;
			this.toughness = toughness;
		}

		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}

		@ParametersAreNonnullByDefault
		@Override
		public int getDurability(EquipmentSlotType arg0) {
			return this.durability;
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public String getName() {
			return BioniMine.MODID + ":" + this.name;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return Ingredient.EMPTY;
		}

		@Override
		public SoundEvent getSoundEvent() {
			return equipSound;
		}

		@Override
		public float getToughness() {
			return this.toughness;
		}
	}
}
