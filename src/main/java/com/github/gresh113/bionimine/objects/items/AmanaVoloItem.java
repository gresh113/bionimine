package com.github.gresh113.bionimine.objects.items;

import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class AmanaVoloItem extends Item {

	public AmanaVoloItem(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	public AmanaVoloItem() {
		super(new Item.Properties().maxStackSize(16).group(BioniMineItemGroup.instance));
	}
	
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerIn.setHealth(playerIn.getMaxHealth());
		playerIn.addPotionEffect(new EffectInstance(Effects.STRENGTH, 60, 2));
		playerIn.setAbsorptionAmount(5);
		playerIn.getCooldownTracker().setCooldown(this, 20);
		itemstack.shrink(1);
		return ActionResult.resultSuccess(itemstack);
		
	}


}
