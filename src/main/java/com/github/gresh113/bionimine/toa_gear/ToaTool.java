package com.github.gresh113.bionimine.toa_gear;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.Bionimine.BioniMineItemGroup;
import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.entities.elemental_projectiles.ElementalProjectileEntity;
import com.github.gresh113.bionimine.init.ItemInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class ToaTool extends Item {
	private static final Item.Properties defaultProperties = new Item.Properties().group(BioniMineItemGroup.instance).maxStackSize(16);
	Elements element;
	
	// Constructors
	public ToaTool(Properties properties) {
		super(properties);
	}
	
	public ToaTool(Properties properties, Elements elementIn) {
		super(properties);
		this.element = elementIn;
	}
	public ToaTool(Elements elementIn) {
		super(defaultProperties);
		this.element = elementIn;
	}

	public ToaTool() {
		super(defaultProperties);
	}

	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
		if (entityLiving instanceof PlayerEntity) {
			PlayerEntity playerentity = (PlayerEntity) entityLiving;
			boolean flag = playerentity.abilities.isCreativeMode;
			ItemStack itemstack = playerentity.findAmmo(stack);

			int i = this.getUseDuration(stack) - timeLeft;
			i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || flag);
			i = 1000;
			if (i < 0)
				return;

				float f = getArrowVelocity(i);
				if (!((double) f < 0.1D)) {
					if (!worldIn.isRemote) {
						ToaTool weaponItem = (ToaTool) (itemstack.getItem() instanceof ToaTool ? itemstack.getItem() : ItemInit.ice_sword.get());
						ElementalProjectileEntity projectileEntity = weaponItem.createProjectile(worldIn, itemstack, playerentity, this.element);
						projectileEntity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);

						stack.damageItem(1, playerentity, (consumer) -> {
							consumer.sendBreakAnimation(playerentity.getActiveHand());
						});
						projectileEntity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
						
						IToaEnergy playerCapability = playerentity.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
						byte eEnergy = playerCapability.getElementalEnergy();
						Bionimine.LOGGER.info("Before: " + eEnergy);
						eEnergy -= 20;
						if (eEnergy < 0)
							eEnergy = 0;
						
						playerCapability.setElementalEnergy(eEnergy);
						
						worldIn.addEntity(projectileEntity);
					}

					worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
					//Bionimine.LOGGER.info(element.getName());
					playerentity.addStat(Stats.ITEM_USED.get(this));
				}
			
		}
	}

	public ElementalProjectileEntity createProjectile(World worldIn, ItemStack stack, LivingEntity shooter, Elements elementIn) {
		ElementalProjectileEntity entity = new ElementalProjectileEntity(worldIn, shooter, elementIn);
		if (elementIn == Elements.AIR) {
			entity.setKnockbackStrength(2);
		}
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
	
	// public AbstractArrowEntity getProjectileEntityFromElement(Element elementIn) {	}

	/**
	 * How long it takes to use or consume an item
	 */
	@Override
	public int getUseDuration(ItemStack stack) {
		return 1000;
	}

	/**
	 * returns the action that specifies what animation to play when the items is
	 * being used
	 */
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BOW;
	}

	/**
	 * Called to trigger the item's "innate" right click behavior. To handle when
	 * this item is used on a Block, see {@link #onItemUse}.
	 */
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
		boolean flag = !playerIn.findAmmo(itemstack).isEmpty();

		ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, flag);
		if (ret != null)
			return ret;

		playerIn.setActiveHand(handIn);
		return ActionResult.resultConsume(itemstack);
	}

}
