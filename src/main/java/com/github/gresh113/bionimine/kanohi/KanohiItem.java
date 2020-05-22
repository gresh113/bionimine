package com.github.gresh113.bionimine.kanohi;

import java.util.List;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.KeyHandler;
import com.github.gresh113.bionimine.BioniMine.KanohiItemGroup;
import com.github.gresh113.bionimine.kanohi.OldKanohiItem.KanohiMaterial;
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

//@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.FORGE)
public class KanohiItem extends ArmorItem {

	private Kanohi kanohiID;
	private static final Item.Properties defaultProperties = new Item.Properties().group(KanohiItemGroup.instance)
			.maxStackSize(1);
	private static EquipmentSlotType defaultSlot = EquipmentSlotType.HEAD;
	private static ArmorMaterial material = ArmorMaterial.IRON;

	// private CompoundNBT nbt;

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

	public KanohiAbility getKanohiAbility() {
		return this.kanohiID.getAbility();
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

	public static KanohiPalette getPalette(ItemStack stackIn) {
		KanohiPalette defaultPalette = ((KanohiItem) stackIn.getItem()).getKanohi().getDefaultPalette();

		CompoundNBT compoundNBT = stackIn.getOrCreateTag();
		String tag = "color";
		KanohiPalette stackPalette;
		if (compoundNBT.contains(tag)) {
			stackPalette = KanohiPalette.fromNBT(compoundNBT, tag);
		} else {
			stackPalette = defaultPalette;
			stackPalette.putIntoNBT(compoundNBT, tag);
		}
		return stackPalette;
		// CompoundNBT compoundNBT = stack.getOrCreateTag();
		// return KanohiPalette.fromNBT(compoundNBT, "color");
		// BioniMine.LOGGER.info("Beep boop" + );
	}

//	 public void setPalette(ItemStack stack, KanohiPalette shapeIn)
//	  {
//	    CompoundNBT compoundNBT = stack.getOrCreateTag();
//	    shapeIn.putIntoNBT(compoundNBT, "color");
//	  }

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
		// tooltip.add(new StringTextComponent("| Shape: " +
		// kanohiIn.getShape().getPredicate()));

		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@OnlyIn(Dist.CLIENT)
	private static float getShapePropertyOverride(ItemStack itemStack, @Nullable World world,
			@Nullable LivingEntity livingEntity) {
		KanohiShape shape = getShape(itemStack);
		return shape.getPropertyOverrideValue();
	}

	@Override
	public void onArmorTick(ItemStack stackIn, World world, PlayerEntity playerIn) {
		boolean kanohiActive;
		CompoundNBT compoundNBT = stackIn.getOrCreateTag();
		String tag = "active";

		if (compoundNBT != null && compoundNBT.contains(tag)) {
			kanohiActive = compoundNBT.getBoolean(tag);
		} else {
			kanohiActive = false;
		}
		if (KeyHandler.kanohiTrigger.isPressed()) {
			kanohiActive = !kanohiActive;
		}

		KanohiAbility ability = ((KanohiItem) stackIn.getItem()).getKanohiAbility();

		// Activates mask power based on "material" type
		if (ability == KanohiAbility.KAUKAU && kanohiActive) {
			playerIn.setAir(playerIn.getMaxAir());
		}
		if (ability == KanohiAbility.KAKAMA && kanohiActive) {
			playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 1, 3));
		}
		if (ability == KanohiAbility.MIRU && kanohiActive) {
			playerIn.addPotionEffect(new EffectInstance(Effects.LEVITATION, 1, -1));
		}
		if (ability == KanohiAbility.PAKARI && kanohiActive) {
			playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 1, 3));
			playerIn.addPotionEffect(new EffectInstance(Effects.HASTE, 1, 5));
		}
		if (ability == KanohiAbility.HAU && kanohiActive) {
			playerIn.addPotionEffect(new EffectInstance(Effects.ABSORPTION, 1, 1));
			playerIn.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 1, 1));
			playerIn.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 1, 2));
		}

		compoundNBT.putBoolean(tag, kanohiActive);
		super.onArmorTick(stackIn, world, playerIn);
	}

	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stackIn = playerIn.getHeldItemMainhand();
		CompoundNBT compoundNBT = stackIn.getOrCreateTag();
		String tag = "color";
		KanohiPalette stackPalette;
		if (compoundNBT.contains(tag)) {
			stackPalette = KanohiPalette.fromNBT(compoundNBT, tag);
		} else {
			stackPalette = KanohiPalette.ORANGE;
			stackPalette.putIntoNBT(compoundNBT, tag);
		}

		// if (worldIn.isRemote) {final boolean PRINT_IN_CHAT_WINDOW = true;
		// playerIn.sendStatusMessage(new StringTextComponent(numOut.toString()),
		// PRINT_IN_CHAT_WINDOW); }

		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stackIn);
	}

}
