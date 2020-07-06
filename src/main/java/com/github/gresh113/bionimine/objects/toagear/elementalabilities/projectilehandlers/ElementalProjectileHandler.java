package com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers;

import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.entities.ElementalProjectileEntity;
import com.github.gresh113.bionimine.init.ItemRegistration;
import com.github.gresh113.bionimine.network.BioniminePacketHandler;
import com.github.gresh113.bionimine.network.ToaEnergyMessage;
import com.github.gresh113.bionimine.objects.toagear.ToaTool;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.ToaAbilityHandler;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

public abstract class ElementalProjectileHandler extends ToaAbilityHandler{
	private static String name = "Projectile";
	private int cooldownTicks = 20;
	
	public void summonProjectile(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		boolean flag = playerentity.abilities.isCreativeMode;
		ToaTool toolItem = (ToaTool) (stackIn.getItem() instanceof ToaTool ? stackIn.getItem() : ItemRegistration.ice_sword.get());
		ElementalProjectileEntity projectileEntity = new ElementalProjectileEntity(worldIn, playerentity, toolItem.getElement());
		float velocity = ToaTool.getArrowVelocity(1000);
		projectileEntity.func_234612_a_(playerentity,playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, velocity * 3.0F, 1.0F);
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
	}
	
	@Override
	public String getAbilityTypeName() {
		return name;
	}
	
	public abstract void spawnParticles(ElementalProjectileEntity entityIn);
	
	public abstract EntityModel<? extends ElementalProjectileEntity> getProjectileModel();

	protected void handleEnergy(PlayerEntity playerentityIn, boolean flag) {
		IToaEnergy playerCapability = playerentityIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		int eEnergy = playerCapability.getElementalEnergy();
		if (!flag) {
			eEnergy -= (ToaEnergy.maxElementalEnergy / 6);
			if (eEnergy < 0)
				eEnergy = 0;
		}
		playerCapability.setElementalEnergy(eEnergy);
		
		if (playerentityIn instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) playerentityIn;
			IToaEnergy capability = player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
			ToaEnergyMessage kanohiMessage = new ToaEnergyMessage(capability.getKanohiEnergy(), capability.getElementalEnergy());
			BioniminePacketHandler.INSTANCE.sendTo(kanohiMessage, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}
	

}