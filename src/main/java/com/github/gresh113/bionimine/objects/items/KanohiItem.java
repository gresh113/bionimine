package com.github.gresh113.bionimine.objects.items;

import javax.annotation.Nonnull;

import com.github.gresh113.bionimine.BioniMine;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public class KanohiItem extends ArmorItem {
	public String texture = "kanohi";
	
	public static final KanohiMaterial KANOHI_MATERIAL = new KanohiMaterial();

	public KanohiItem(Properties builder) {
		this(KANOHI_MATERIAL, EquipmentSlotType.HEAD, builder);
	}
	public KanohiItem(EquipmentSlotType slot, Properties builder) {
		this(KANOHI_MATERIAL, slot, builder);
	}
	public KanohiItem(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builder) {
		super(materialIn, slot, builder);
	}
	
	
	protected static class KanohiMaterial implements IArmorMaterial {

        @Override
        public int getDurability(EquipmentSlotType slotType) {
            return 0;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotType) {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return Ingredient.EMPTY;
        }

        @Override
        public String getName() {
            return BioniMine.MODID + ":kanohi";
        }

        @Override
        public float getToughness() {
            return 0;
        }
    }
	
	public Item setArmorTexture(String string) {
        this.texture = string;
        return this;
    }

    @Override
    public String getArmorTexture(@Nonnull ItemStack stack, Entity entity, EquipmentSlotType slot, String layer) {
        return "bionimine:textures/models/armor/" + this.texture + ".png";
    }
}
