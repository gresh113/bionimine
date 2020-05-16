package com.github.gresh113.bionimine.kanohi;

import java.util.List;

import com.github.gresh113.bionimine.BioniMine.KanohiItemGroup;

import net.minecraft.block.DispenserBlock;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.dispenser.IDispenseItemBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

//@Mod.EventBusSubscriber(modid = BioniMine.MODID, bus = Bus.FORGE)
public class NewKanohiItem extends Item {

	public static final IDispenseItemBehavior DISPENSER_BEHAVIOR = new DefaultDispenseItemBehavior() {
		/**
		 * Dispense the specified stack, play the dispense sound and spawn particles.
		 */
		protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
			return NewKanohiItem.dispenseFunction(source, stack) ? stack : super.dispenseStack(source, stack);
		}
	};
	protected final EquipmentSlotType slot = EquipmentSlotType.HEAD;

	public static boolean dispenseFunction(IBlockSource blockSource, ItemStack stackIn) {
		BlockPos blockpos = blockSource.getBlockPos().offset(blockSource.getBlockState().get(DispenserBlock.FACING));
		List<LivingEntity> list = blockSource.getWorld().getEntitiesWithinAABB(LivingEntity.class,
				new AxisAlignedBB(blockpos),
				EntityPredicates.NOT_SPECTATING.and(new EntityPredicates.ArmoredMob(stackIn)));
		if (list.isEmpty()) {
			return false;
		} else {
			LivingEntity livingentity = list.get(0);
			EquipmentSlotType equipmentslottype = MobEntity.getSlotForItemStack(stackIn);
			ItemStack itemstack = stackIn.split(1);
			livingentity.setItemStackToSlot(equipmentslottype, itemstack);
			if (livingentity instanceof MobEntity) {
				((MobEntity) livingentity).setDropChance(equipmentslottype, 2.0F);
				((MobEntity) livingentity).enablePersistence();
			}

			return true;
		}
	}
	
	
	private final Kanohi kanohiID;
	private static final Item.Properties defaultProperties = new Item.Properties().group(KanohiItemGroup.instance).maxStackSize(1);
	
	public NewKanohiItem(Kanohi kanohiIn, Item.Properties properties) {
		super(properties);
		kanohiID = kanohiIn;
		DispenserBlock.registerDispenseBehavior(this, DISPENSER_BEHAVIOR);
	}

	public NewKanohiItem(Kanohi kanohiIn) {
		super(defaultProperties);
		kanohiID = kanohiIn;
		DispenserBlock.registerDispenseBehavior(this, DISPENSER_BEHAVIOR);
	}
	
	public NewKanohiItem() {
		super(defaultProperties);
		kanohiID = Kanohi.TAHUS_HAU;
		DispenserBlock.registerDispenseBehavior(this, DISPENSER_BEHAVIOR);
	}
	
	public Kanohi getKanohi() {
		return this.kanohiID;
	}

	/**
	 * Gets the equipment slot of this armor piece (formerly known as armor type)
	 */
	public EquipmentSlotType getEquipmentSlot() {
		return this.slot;
	}

	/**
	 * Return whether this item is repairable in an anvil.
	 */
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}

	/**
	 * Called to trigger the item's "innate" right click behavior. To handle when
	 * this item is used on a Block, see {@link #onItemUse}.
	 */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		EquipmentSlotType equipmentslottype = MobEntity.getSlotForItemStack(itemstack);
		ItemStack itemstack1 = playerIn.getItemStackFromSlot(equipmentslottype);
		if (itemstack1.isEmpty()) {
			playerIn.setItemStackToSlot(equipmentslottype, itemstack.copy());
			itemstack.setCount(0);
			return ActionResult.resultSuccess(itemstack);
		} else {
			return ActionResult.resultFail(itemstack);
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		NewKanohiItem kanohiItemIn = (NewKanohiItem) stack.getItem();
		Kanohi kanohiIn = kanohiItemIn.getKanohi();
		String infoString = kanohiIn.getDescriptionText();
		tooltip.add(new StringTextComponent(infoString));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
}
