package com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers;

import com.github.gresh113.bionimine.entities.elemental_projectiles.ElementalProjectileEntity;
import com.github.gresh113.bionimine.entities.elemental_projectiles.WaterProjectileModel;
import com.github.gresh113.bionimine.objects.toagear.ToaTool;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WaterProjectileHandler extends ElementalProjectileHandler {
	WaterProjectileModel model = new WaterProjectileModel();

	@Override
	public void summonProjectile(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		boolean flag = playerentity.abilities.isCreativeMode;
		ElementalProjectileEntity projectileEntity = new ElementalProjectileEntity(worldIn, playerentity, Elements.WATER);
		float velocity = ToaTool.getArrowVelocity(1000);
		projectileEntity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
		projectileEntity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
		/*
		 * stackIn.damageItem(1, playerentity, (consumer) -> {
		 * consumer.sendBreakAnimation(playerentity.getActiveHand()); });
		 */

		handleEnergy(playerentity, flag);
		
		worldIn.addEntity(projectileEntity);
		worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

//		if (elementIn == Elements.AIR) {//			entity.setKnockbackStrength(2);
//		}
		// return entity;
	}

	@Override
	public void spawnParticles(ElementalProjectileEntity entityIn) {
		Vec3d vector = entityIn.getMotion();
		double dx = vector.x;
		double dy = vector.y;
		double dz = vector.z;
		for (int i = 0; i < 4; ++i) {
			entityIn.world.addParticle(ParticleTypes.BUBBLE, entityIn.getPosX() + dx * (double) i / 4.0D, entityIn.getPosY() + dy * (double) i / 4.0D, entityIn.getPosZ() + dz * (double) i / 4.0D, -dx, -dy + 0.2D, -dz);
			entityIn.world.addParticle(ParticleTypes.FALLING_WATER, entityIn.getPosX() + dx * (double) i / 2.0D, entityIn.getPosY() + dy * (double) i / 2.0D, entityIn.getPosZ() + dz * (double) i / 2.0D, -dx, -dy + 0.1D, -dz);
		}
	}

	@Override
	public EntityModel<? extends ElementalProjectileEntity> getProjectileModel() {
		return model;
	}

}
