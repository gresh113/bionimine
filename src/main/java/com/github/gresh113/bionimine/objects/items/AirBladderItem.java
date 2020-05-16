package com.github.gresh113.bionimine.objects.items;

import com.github.gresh113.bionimine.BioniMine.BioniMineItemGroup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class AirBladderItem extends Item {

	public AirBladderItem(Properties properties) {
		super(properties);
	}
	
	public AirBladderItem() {
		super(new Item.Properties().maxStackSize(16).group(BioniMineItemGroup.instance));
	}

	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		if (playerIn.getAir() < playerIn.getMaxAir()) {
		worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerIn.setAir(playerIn.getMaxAir());
		playerIn.getCooldownTracker().setCooldown(this, 20);
		itemstack.shrink(1);
		return ActionResult.resultSuccess(itemstack);
		} 
		else {
			return ActionResult.resultFail(itemstack);
		}
		
	}
}
