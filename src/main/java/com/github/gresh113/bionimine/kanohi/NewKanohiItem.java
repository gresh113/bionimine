package com.github.gresh113.bionimine.kanohi;

import java.util.List;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.BioniMine;
import com.github.gresh113.bionimine.BioniMine.KanohiItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
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
public class NewKanohiItem extends ArmorItem {

	protected final EquipmentSlotType slot = EquipmentSlotType.HEAD;

	private Kanohi kanohiID;
	private static final Item.Properties defaultProperties = new Item.Properties().group(KanohiItemGroup.instance)
			.maxStackSize(1);
	private static EquipmentSlotType defaultSlot = EquipmentSlotType.HEAD;
	private static ArmorMaterial material = ArmorMaterial.IRON;
	
	//private CompoundNBT nbt;
	
	private static ResourceLocation resourceLocation = new ResourceLocation(BioniMine.MODID, "shape");
	private IItemPropertyGetter shapeGetter = new IItemPropertyGetter() {
		@OnlyIn(Dist.CLIENT)
		public float call(ItemStack stackIn, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
			CompoundNBT compoundNBT = stackIn.getOrCreateTag();
			String tag = "shape";
			KanohiShape stackShape;
			if (compoundNBT.contains(tag)){ stackShape = KanohiShape.fromNBT(compoundNBT, tag);}
			else {
		    stackShape = KanohiShape.HAU_GREAT;
		    stackShape.putIntoNBT(compoundNBT, tag);
			}
			return stackShape.getPropertyOverrideValue();
			}

		};
		

	public NewKanohiItem(Kanohi kanohiIn, Item.Properties properties) {
		super(material, defaultSlot, properties);
		kanohiID = kanohiIn;
		this.addPropertyOverride(resourceLocation, shapeGetter);
	}

	public NewKanohiItem(Kanohi kanohiIn) {
		super(material, defaultSlot, defaultProperties);
		kanohiID = kanohiIn;
		this.addPropertyOverride(resourceLocation, shapeGetter);
	}

	public NewKanohiItem() {
		super(material, defaultSlot, defaultProperties);
		kanohiID = Kanohi.TAHUS_HAU;
		this.addPropertyOverride(resourceLocation, shapeGetter);
	}

	public Kanohi getKanohi() {
		return this.kanohiID;
	}
	
	public void setKanohiShape(KanohiShape shapeIn) {
		this.kanohiID.setShape(shapeIn);
	}
	
	
	public static KanohiShape getShape(ItemStack stack)
	  {
	    CompoundNBT compoundNBT = stack.getOrCreateTag();
	    return KanohiShape.fromNBT(compoundNBT, "shape");
	  }
	
	 public void setShape(ItemStack stack, KanohiShape shapeIn)	  {
	    CompoundNBT compoundNBT = stack.getOrCreateTag();
	    shapeIn.putIntoNBT(compoundNBT, "shape");
	  }
	 
	 public static KanohiPalette getPalette(ItemStack stack)
	  {
		 return ((NewKanohiItem) stack.getItem()).getKanohi().getPalette();
	    //CompoundNBT compoundNBT = stack.getOrCreateTag();
	    //return KanohiPalette.fromNBT(compoundNBT, "color");
	    //BioniMine.LOGGER.info("Beep boop" + );
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

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		NewKanohiItem kanohiItemIn = (NewKanohiItem) stack.getItem();
		//kanohiItemIn.getPropertyGetter(resourceLocation).toString();
		Kanohi kanohiIn = kanohiItemIn.getKanohi();
		String infoString = kanohiIn.getDescriptionText();
		tooltip.add(new StringTextComponent(infoString));
		//tooltip.add(new StringTextComponent("| Shape: " + kanohiIn.getShape().getPredicate()));
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	
	  @OnlyIn(Dist.CLIENT)
	  private static float getShapePropertyOverride(ItemStack itemStack, @Nullable World world, @Nullable LivingEntity livingEntity)
	  {
	    KanohiShape shape = getShape(itemStack);
	    return shape.getPropertyOverrideValue();
	  }
	  
	  
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack stackIn = playerIn.getHeldItemMainhand();
		CompoundNBT compoundNBT = stackIn.getOrCreateTag();
		String tag = "shape";
		KanohiShape stackShape;
		if (compoundNBT.contains(tag)){
			stackShape = KanohiShape.fromNBT(compoundNBT, tag);
			if (worldIn.isRemote) {  
		        final boolean PRINT_IN_CHAT_WINDOW = true;
		        playerIn.sendStatusMessage(new StringTextComponent("Tested predicate:" + compoundNBT.getFloat(tag)),
		                PRINT_IN_CHAT_WINDOW);
		      }
		}
		else {
	    stackShape = KanohiShape.HAU_GREAT;
	    stackShape.putIntoNBT(compoundNBT, tag);
	  
		}
		float numIn = stackShape.getPredicate();
		float numOut = numIn + ((float) 0.01f);
		compoundNBT.putFloat(tag, numOut);
		
		// if (worldIn.isRemote) {final boolean PRINT_IN_CHAT_WINDOW = true; playerIn.sendStatusMessage(new StringTextComponent(numOut.toString()), PRINT_IN_CHAT_WINDOW);  }
	    
		return new ActionResult<ItemStack>(ActionResultType.SUCCESS, stackIn);
	}
	  
	  
	
}
