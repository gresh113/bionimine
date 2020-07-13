package com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers;

import com.github.gresh113.bionimine.entities.ElementalProjectileEntity;
import com.github.gresh113.bionimine.objects.toagear.ToaTool;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.ToaAbilityHandler;
import com.github.gresh113.bionimine.registration.ItemRegistration;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public abstract class ElementalProjectileHandler extends ToaAbilityHandler{
	private static String name = "Projectile";
	private int cooldownTicks = 20;
	
	public void summonProjectile(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		ToaTool toolItem = (ToaTool) (stackIn.getItem() instanceof ToaTool ? stackIn.getItem() : ItemRegistration.ice_sword.get());
		ElementalProjectileEntity projectileEntity = new ElementalProjectileEntity(worldIn, playerentity, toolItem.getElement());
		float velocity = ToaTool.getArrowVelocity(1000);
		projectileEntity.func_234612_a_(playerentity,playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
		projectileEntity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
		/*
		 * stackIn.damageItem(1, playerentity, (consumer) -> {
		 * consumer.sendBreakAnimation(playerentity.getActiveHand()); });
		 */
		worldIn.addEntity(projectileEntity);
		worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);
		
//		if (elementIn == Elements.AIR) {//			entity.setKnockbackStrength(2);
//		}
		// return entity;
	}
	
	@Override
	public void trigger(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		summonProjectile(stackIn, worldIn, playerentity);
		playerentity.getCooldownTracker().setCooldown(stackIn.getItem(), cooldownTicks);
		this.handleEnergy(playerentity);
	}
	
	@Override
	public String getAbilityTypeName() {
		return name;
	}
	
	public abstract void spawnParticles(ElementalProjectileEntity entityIn);
	
	public abstract EntityModel<? extends ElementalProjectileEntity> getProjectileModel();
	

}