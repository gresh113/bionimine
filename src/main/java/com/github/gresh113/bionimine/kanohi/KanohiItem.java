package com.github.gresh113.bionimine.kanohi;

import java.util.List;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.KeyHandler;
import com.github.gresh113.bionimine.BioniMine.KanohiItemGroup;
import com.github.gresh113.bionimine.kanohi.OldKanohiItem.KanohiMaterial;
import com.github.gresh113.bionimine.objects.items.ToaArmorItem;
import com.github.gresh113.bionimine.util.KanohiFunctions;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IArmorMaterial;
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
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KanohiItem extends ArmorItem {

	private Kanohi kanohiID;
	private static final Item.Properties defaultProperties = new Item.Properties().group(KanohiItemGroup.instance)
			.maxStackSize(1);
	private static EquipmentSlotType defaultSlot = EquipmentSlotType.HEAD;
	private static ArmorMaterial material = ArmorMaterial.IRON;

	private static ResourceLocation resourceLocation = new ResourceLocation(BioniMine.MODID, "shape");
	
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
		return (BioniMine.MODID + ":textures/models/kanohi/" + this.getShape(stack).getName() + "/"
				+ this.getPalette(stack).getName() + ".png");
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
	private static float getShapePropertyOverride(ItemStack itemStack, @Nullable World world,
			@Nullable LivingEntity livingEntity) {
		KanohiShape shape = getShape(itemStack);
		return shape.getPropertyOverrideValue();
	}

	
	boolean kanohiActive;
	
	@Override
	public void onArmorTick(ItemStack stackIn, World world, PlayerEntity playerIn) {
		
		if (playerIn.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() instanceof ToaArmorItem && (this.getPalette(stackIn) == ArmorPalette.GRAY || this.getPalette(stackIn) == null)) {
			ToaArmorItem toaArmorIn = (ToaArmorItem) playerIn.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem();
			this.setPalette(stackIn, toaArmorIn.getPalette());
		}
		
		// Checks for kanohiTrigger key press, toggles Kanohi's active state if key press occurs
		if(KeyHandler.kanohiTrigger.isPressed()) {kanohiActive = !kanohiActive;}
		
		if (!(stackIn == ItemStack.EMPTY || stackIn == null)) {
			
			KanohiItem currentKanohi = (KanohiItem) stackIn.getItem();
			KanohiPower power = currentKanohi.getKanohiPower();
			
			// Activates mask power based on "material" type
			if (kanohiActive) {
			if (power == KanohiPower.GREAT_KAUKAU) {
				playerIn.setAir(playerIn.getMaxAir());
			}
			if (power == KanohiPower.GREAT_KAKAMA) {
				playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 1, 3));
			}
			if (power == KanohiPower.GREAT_MIRU) {
				playerIn.addPotionEffect(new EffectInstance(Effects.LEVITATION, 1, -1));
			}
			if (power == KanohiPower.GREAT_PAKARI) {
				playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 1, 3));
				playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 1, 5));
			}
			if (power == KanohiPower.GREAT_HAU) {
				playerIn.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 1, 1));
				playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1, 1));
				playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1, 2));
			}
			if (power == KanohiPower.GREAT_HUNA) {
				playerIn.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 1, 1));
			}
			if (power == KanohiPower.GREAT_RURU) {
				playerIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 1, 1));
				playerIn.addPotionEffect(new EffectInstance(Effects.GLOWING, 1, 1));
			}
			if (power == KanohiPower.NOBLE_RURU) {
				playerIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 1, 1));
			}
			}
		}
	}
	
	@SubscribeEvent
	public void equipmentChange(LivingEquipmentChangeEvent event) {
		if (event.getSlot() == EquipmentSlotType.HEAD){kanohiActive = false;}
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
}
