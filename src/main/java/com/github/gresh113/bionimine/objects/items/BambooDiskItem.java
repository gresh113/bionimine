package com.github.gresh113.bionimine.objects.items;

import java.util.function.Predicate;

import com.github.gresh113.bionimine.entities.KanokaEntity;
import com.github.gresh113.bionimine.registration.ItemRegistration;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ShootableItem;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class BambooDiskItem extends ShootableItem {

	public static final Predicate<ItemStack> DISKS = (stack) -> {
		return stack.getItem().isIn(ItemTags.ARROWS);
	};

	public BambooDiskItem(Item.Properties properties) {
		super(properties);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		 if (entityLiving instanceof PlayerEntity) {
	         PlayerEntity playerentity = (PlayerEntity)entityLiving;
	         boolean flag = playerentity.abilities.isCreativeMode;
	         ItemStack itemstack = playerentity.findAmmo(stack);

	         int i = this.getUseDuration(stack) - timeLeft;
	         i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
	         if (i < 0) return;

	         if (!itemstack.isEmpty() || flag) {
	            if (itemstack.isEmpty()) {
	               itemstack = new ItemStack(Items.ARROW);
	            }

	            float f = getArrowVelocity(i);
	            if (!((double)f < 0.1D)) {
	               boolean flag1 = playerentity.abilities.isCreativeMode;
	               if (!worldIn.isRemote) {
	                  BambooDiskItem diskitem = (BambooDiskItem)(itemstack.getItem() instanceof BambooDiskItem ? itemstack.getItem() : ItemRegistration.bamboo_disk.get());
	                  KanokaEntity diskentity = diskitem.createDisk(worldIn, itemstack, playerentity);
	                  diskentity.func_234612_a_(playerentity,playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

	                  stack.damageItem(1, playerentity, (consumer) -> {consumer.sendBreakAnimation(playerentity.getActiveHand());
	                  });
	                  if (flag1) {
	                     diskentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
	                  }

	                  worldIn.addEntity(diskentity);
	               }

	               worldIn.playSound((PlayerEntity)null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	               if (!flag1 && !playerentity.abilities.isCreativeMode) {
	                  itemstack.shrink(1);
	                  if (itemstack.isEmpty()) {
	                     playerentity.inventory.deleteStack(itemstack);
	                  }
	               }

	               playerentity.addStat(Stats.ITEM_USED.get(this));
	            }
	         }
	      }
	   }
	
	
	public KanokaEntity createDisk(World worldIn, ItemStack stack, LivingEntity shooter) {
	      KanokaEntity entity = new KanokaEntity(worldIn, shooter);
	      return entity;
	   }

	   /**
	    * Gets the velocity of the arrow entity from the bow's charge
	    */
	   public static float getArrowVelocity(int charge) {
		   float f = (float) charge / 20.0F;
			f = (f * f + f * 2.0F) / 3.0F;
			if (f > 1.0F) {
				f = 1.0F;
			}
			return f;
	   }

	   /**
	    * How long it takes to use or consume an item
	    */
	   @Override
	   public int getUseDuration(ItemStack stack) {
	      return 1000;
	   }

	   /**
	    * returns the action that specifies what animation to play when the items is being used
	    */
	   public UseAction getUseAction(ItemStack stack) {
	      return UseAction.BOW;
	   }

	   /**
	    * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
	    * {@link #onItemUse}.
	    */
	   public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
	      ItemStack itemstack = playerIn.getHeldItem(handIn);
	      boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

	      ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
	      if (ret != null) return ret;

	      if (!playerIn.abilities.isCreativeMode && !flag) {
	         return ActionResult.resultFail(itemstack);
	      } else {
	         playerIn.setActiveHand(handIn);
	         return ActionResult.resultConsume(itemstack);
	      }
	   }

	   /**
	    * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
	    */
	   public Predicate<ItemStack> getInventoryAmmoPredicate() {
	      return ARROWS;
	   }

	@Override
	public int func_230305_d_() {
		return 15;
	}	
}
