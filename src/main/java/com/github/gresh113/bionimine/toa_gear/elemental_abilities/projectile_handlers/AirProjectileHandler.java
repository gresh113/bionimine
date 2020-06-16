package com.github.gresh113.bionimine.toa_gear.elemental_abilities.projectile_handlers;

import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.entities.elemental_projectiles.AirProjectileModel;
import com.github.gresh113.bionimine.entities.elemental_projectiles.ElementalProjectileEntity;
import com.github.gresh113.bionimine.init.ItemInit;
import com.github.gresh113.bionimine.network.BioniminePacketHandler;
import com.github.gresh113.bionimine.network.ToaEnergyMessage;
import com.github.gresh113.bionimine.toa_gear.ToaTool;
import com.github.gresh113.bionimine.toa_gear.elemental_abilities.Elements;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

public class AirProjectileHandler extends ElementalProjectileHandler{
	AirProjectileModel model = new AirProjectileModel();

	@Override
	public void summonProjectile(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		boolean flag = playerentity.abilities.isCreativeMode;
		// TO-DO: change these elements to correct when axe and elemental 
		ToaTool toolItem = (ToaTool) (stackIn.getItem() instanceof ToaTool ? stackIn.getItem() : ItemInit.fire_sword.get());
		ElementalProjectileEntity projectileEntity = new ElementalProjectileEntity(worldIn, playerentity, Elements.AIR);
		float velocity = ToaTool.getArrowVelocity(1000);
		projectileEntity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
		projectileEntity.pickupStatus = AbstractArrowEntity.PickupStatus.DISALLOWED;
		/*
		 * stackIn.damageItem(1, playerentity, (consumer) -> {
		 * consumer.sendBreakAnimation(playerentity.getActiveHand()); });
		 */

		IToaEnergy playerCapability = playerentity.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));

		int eEnergy = playerCapability.getElementalEnergy();
		if (!flag) {
			eEnergy -= (ToaEnergy.maxElementalEnergy / 6);
			if (eEnergy < 0)
				eEnergy = 0;
		}

		playerCapability.setElementalEnergy(eEnergy);
		if (playerentity instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) playerentity;
			IToaEnergy capability = player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
			ToaEnergyMessage kanohiMessage = new ToaEnergyMessage(capability.getKanohiEnergy(), capability.getElementalEnergy());
			BioniminePacketHandler.INSTANCE.sendTo(kanohiMessage, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}

		projectileEntity.setKnockbackStrength(10);
		worldIn.addEntity(projectileEntity);
		worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ITEM_TRIDENT_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + velocity * 0.5F);

	}
	
	@Override
	public void spawnParticles(ElementalProjectileEntity entityIn) {
		Vec3d vector = entityIn.getMotion();
		double dx = vector.x;
		double dy = vector.y;
		double dz = vector.z;
		for (int i = 0; i < 4; ++i) {
			entityIn.world.addParticle(ParticleTypes.CLOUD, entityIn.getPosX() + dx * (double) i / 4.0D, entityIn.getPosY() + dy * (double) i / 4.0D, entityIn.getPosZ() + dz * (double) i / 4.0D, -dx, -dy + 0.2D, -dz);
		}
	}

	@Override
	public EntityModel<? extends ElementalProjectileEntity> getProjectileModel() {
		return model;
	}

}
