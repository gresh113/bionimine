package com.github.gresh113.bionimine.kanohi;

import java.util.List;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.BioniMine.KanohiItemGroup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

//@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.FORGE)
public class NewKanohiItem extends ArmorItem {

	protected final EquipmentSlotType slot = EquipmentSlotType.HEAD;

	private final Kanohi kanohiID;
	private static final Item.Properties defaultProperties = new Item.Properties().group(KanohiItemGroup.instance)
			.maxStackSize(1);
	private static EquipmentSlotType defaultSlot = EquipmentSlotType.HEAD;
	private static ArmorMaterial material = ArmorMaterial.IRON;
	private static ResourceLocation resourceLocation = new ResourceLocation("shape");
	private IItemPropertyGetter shapeGetter = new IItemPropertyGetter() {
		@OnlyIn(Dist.CLIENT)
		public float call(ItemStack stackIn, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
			NewKanohiItem kanohiItemFromStack = (NewKanohiItem) stackIn.getItem();
			Kanohi kanohiRetrieved = kanohiItemFromStack.getKanohi();
			KanohiShape shapeRetrieved = kanohiRetrieved.getShape();
			return shapeRetrieved.getPredicate();}

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
		Kanohi kanohiIn = kanohiItemIn.getKanohi();
		String infoString = kanohiIn.getDescriptionText();
		tooltip.add(new StringTextComponent(infoString));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
