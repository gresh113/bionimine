package com.github.gresh113.bionimine.toa_gear.kanohi;

import java.util.List;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.KeyHandler;
import com.github.gresh113.bionimine.Bionimine.KanohiItemGroup;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.toa_gear.ArmorPalette;
import com.github.gresh113.bionimine.toa_gear.ToaArmorItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class KanohiItem extends ArmorItem {

	private Kanohi kanohiID;
	private static final Item.Properties defaultProperties = new Item.Properties().group(KanohiItemGroup.instance).maxStackSize(1);
	private static EquipmentSlotType defaultSlot = EquipmentSlotType.HEAD;
	private static ArmorMaterial material = ArmorMaterial.IRON;

	private static ResourceLocation resourceLocation = new ResourceLocation(Bionimine.MODID, "shape");

	private IItemPropertyGetter shapeGetter = new IItemPropertyGetter() {
		@OnlyIn(Dist.CLIENT)
		public float call(ItemStack stackIn, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
			CompoundNBT compoundNBT = stackIn.getOrCreateTag();
			String tag = "shape";
			KanohiShape stackShape;
			if (compoundNBT.contains(tag)) {
				stackShape = KanohiShape.fromNBT(compoundNBT, tag);
			} else {
				KanohiItem kanohiItem = (KanohiItem) stackIn.getItem();
				stackShape = kanohiItem.getDefaultShape();
				stackShape.putIntoNBT(compoundNBT, tag);
			}
			return stackShape.getPropertyOverrideValue();
		}

	};

	public KanohiItem(Kanohi kanohiIn, Item.Properties properties) {
		super(material, defaultSlot, properties);
		kanohiID = kanohiIn;
		this.addPropertyOverride(resourceLocation, shapeGetter);
	}

	public KanohiItem(Kanohi kanohiIn) {
		super(material, defaultSlot, defaultProperties);
		kanohiID = kanohiIn;
		this.addPropertyOverride(resourceLocation, shapeGetter);
	}

	public KanohiItem() {
		super(material, defaultSlot, defaultProperties);
		kanohiID = Kanohi.TAHUS_HAU;
		this.addPropertyOverride(resourceLocation, shapeGetter);
	}

	public Kanohi getKanohi() {
		return this.kanohiID;
	}

	public KanohiPower getKanohiPower() {
		return this.kanohiID.getPower();
	}

	public KanohiShape getDefaultShape() {
		Kanohi kanohi = this.kanohiID;
		if (kanohi.hasDefaultShape()) {
			return kanohi.getDefaultShape();
		} else {
			return KanohiShape.HAU_GREAT;
		}
	}

	public static KanohiShape getShape(ItemStack stack) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();
		return KanohiShape.fromNBT(compoundNBT, "shape");
	}

	public void setShape(ItemStack stack, KanohiShape shapeIn) {
		CompoundNBT compoundNBT = stack.getOrCreateTag();
		shapeIn.putIntoNBT(compoundNBT, "shape");
	}

	public static ArmorPalette getPalette(ItemStack stackIn) {
		ArmorPalette defaultPalette = ((KanohiItem) stackIn.getItem()).getKanohi().getDefaultPalette();

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

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		return (Bionimine.MODID + ":textures/models/kanohi/" + KanohiItem.getShape(stack).getName() + "/" + KanohiItem.getPalette(stack).getName() + ".png");
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		KanohiItem kanohiItemIn = (KanohiItem) stack.getItem();
		// kanohiItemIn.getPropertyGetter(resourceLocation).toString();

		Kanohi kanohiIn = kanohiItemIn.getKanohi();
		String infoString = kanohiIn.getDescriptionText();
		tooltip.add(new StringTextComponent(infoString));

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@OnlyIn(Dist.CLIENT)
	private static float getShapePropertyOverride(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity) {
		KanohiShape shape = getShape(itemStack);
		return shape.getPropertyOverrideValue();
	}

	private int kanohiPowerLevel;

	@Override
	public void onArmorTick(ItemStack stackIn, World world, PlayerEntity playerIn) {
		int energy = getEnergy(playerIn);
		if (energy < 0)
			energy = 0;

		ItemStack chestStack = playerIn.getItemStackFromSlot(EquipmentSlotType.CHEST);
		if (chestStack.getItem() instanceof ToaArmorItem && (KanohiItem.getPalette(stackIn) == ArmorPalette.GRAY || KanohiItem.getPalette(stackIn) == null)) {
			this.setPalette(stackIn, ToaArmorItem.getPalette(chestStack));
		}

		// Increments kanohiPowerLevel, resets after level 3
		if (KeyHandler.kanohiTrigger.isPressed()) {
			++kanohiPowerLevel;
			if (kanohiPowerLevel == 4) {
				kanohiPowerLevel = 0;
			}
		}

		if (!(stackIn == ItemStack.EMPTY || stackIn == null)) {

			KanohiItem currentKanohi = (KanohiItem) stackIn.getItem();
			KanohiPower power = currentKanohi.getKanohiPower();

			if (kanohiPowerLevel != 0) {
				if (energy > 0) {

					if (power == KanohiPower.GREAT_KAUKAU) {
						playerIn.setAir(playerIn.getMaxAir());
						energy -= 5;
					}
					if (power == KanohiPower.GREAT_KAKAMA) {
						if (kanohiPowerLevel == 1) {
							playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 1, false, false, false));
							energy -= 1;
						}
						if (kanohiPowerLevel == 2) {
							playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 5, false, false, false));
							energy -= 5;
						}
						if (kanohiPowerLevel == 3) {
							playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 10, false, false, false));
							energy -= 10;
						}

					}

					if (power == KanohiPower.GREAT_MIRU) {
						if (kanohiPowerLevel == 1) {
							playerIn.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 0, 1, false, false, false));
							energy -= 1;
						}
						if (kanohiPowerLevel == 2) {
							playerIn.addPotionEffect(new EffectInstance(Effects.LEVITATION, 0, -1, false, false, false));
							energy -= 5;
						}
						if (kanohiPowerLevel == 3) {
							playerIn.addPotionEffect(new EffectInstance(Effects.LEVITATION, 0, 2, false, false, false));
							energy -= 10;
						}
					}

					if (power == KanohiPower.GREAT_PAKARI) {
						if (kanohiPowerLevel == 1) {
							playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 0, 0, false, false, false));
							playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 0, 0, false, false, false));
							energy -= 1;
						}

						if (kanohiPowerLevel == 2) {
							playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 0, 2, false, false, false));
							playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 0, 1, false, false, false));
							energy -= 5;
						}

						if (kanohiPowerLevel == 3) {
							playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 0, 4, false, false, false));
							playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 0, 3, false, false, false));
							energy -= 10;
						}

					}
					if (power == KanohiPower.GREAT_HAU) {
						playerIn.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 0, 1, false, false, false));
						playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 0, 1, false, false, false));
						playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 0, 2, false, false, false));
						energy -= 5;
					}
					if (power == KanohiPower.NOBLE_HUNA) {
						playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 0, 1, false, false, false));
						energy -= 10;
					}
					if (power == KanohiPower.GREAT_HUNA) {
						playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 0, 1, false, false, false));
						energy -= 1;
					}
					if (power == KanohiPower.GREAT_RURU) {
						playerIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 0, 1, false, false, false));
						playerIn.addPotionEffect(new EffectInstance(Effects.GLOWING, 0, 1, false, false, false));
						energy -= 1;
					}
					if (power == KanohiPower.NOBLE_RURU) {
						playerIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 0, 1, false, false, false));
						energy -= 10;
					}
					if (energy < 0 || energy == 0) {
						energy = 0;
						kanohiPowerLevel = 0;
					}
				}
			} else {
				energy += 10;
				if (energy > ToaEnergy.maxKanohiEnergy)
					energy = ToaEnergy.maxKanohiEnergy;
			}
		}

		// BioniMine.LOGGER.info(energy);
		setEnergy(playerIn, energy);

	}

	public void resetPowerLevel() {
		kanohiPowerLevel = 0;
	}

	public int getPowerLevel() {
		return kanohiPowerLevel;
	}

	// Uses capability system to check a given player's current toa energy
	public int getEnergy(PlayerEntity playerIn) {
		IToaEnergy playerCapability = playerIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		return playerCapability.getKanohiEnergy();
	}

	public void setEnergy(PlayerEntity playerIn, int energyIn) {
		IToaEnergy playerCapability = playerIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		playerCapability.setKanohiEnergy(energyIn);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stackIn = playerIn.getHeldItemMainhand();
		CompoundNBT compoundNBT = stackIn.getOrCreateTag();
		String tag = "color";
		ArmorPalette stackPalette;
		if (compoundNBT.contains(tag)) {
			stackPalette = ArmorPalette.fromNBT(compoundNBT, tag);
		} else {
			stackPalette = ArmorPalette.ORANGE;
			stackPalette.putIntoNBT(compoundNBT, tag);
		}

		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stackIn);
	}

//	@Override
//	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundNBT nbt) {
//		if (this.getClass() == KanohiItem.class)
//	         return new ToaEnergyProvider();
//	      else
//	         return super.initCapabilities(stack, nbt);
//	}
}
