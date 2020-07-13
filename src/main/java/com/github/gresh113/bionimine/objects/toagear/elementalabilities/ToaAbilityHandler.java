package com.github.gresh113.bionimine.objects.toagear.elementalabilities;

import java.util.Random;

import com.github.gresh113.bionimine.capabilities.IToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergy;
import com.github.gresh113.bionimine.capabilities.ToaEnergyProvider;
import com.github.gresh113.bionimine.network.BioniminePacketHandler;
import com.github.gresh113.bionimine.network.ToaEnergyMessage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkDirection;

public abstract class ToaAbilityHandler {
	
	protected static final Random random = new Random();

	public abstract void trigger(ItemStack stackIn, World worldIn, PlayerEntity playerentity);
	
	public abstract String getAbilityTypeName();

	protected void handleEnergy(PlayerEntity playerentityIn) {
		IToaEnergy playerCapability = playerentityIn.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
		int eEnergy = playerCapability.getElementalEnergy();
		if (!playerentityIn.abilities.isCreativeMode) {
			eEnergy -= (ToaEnergy.maxElementalEnergy / 6);
			if (eEnergy < 0)
				eEnergy = 0;
		}
		playerCapability.setElementalEnergy(eEnergy);
		
		if (playerentityIn instanceof ServerPlayerEntity) {
			ServerPlayerEntity player = (ServerPlayerEntity) playerentityIn;
			IToaEnergy capability = player.getCapability(ToaEnergyProvider.TOA_ENERGY).orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!"));
			ToaEnergyMessage energymessage = new ToaEnergyMessage(capability.getKanohiEnergy(), capability.getElementalEnergy());
			BioniminePacketHandler.INSTANCE.sendTo(energymessage, player.connection.getNetworkManager(), NetworkDirection.PLAY_TO_CLIENT);
		}
	}

}
