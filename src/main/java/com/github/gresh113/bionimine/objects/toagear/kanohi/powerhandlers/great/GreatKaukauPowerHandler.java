package com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great;

import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.KanohiPowerHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GreatKaukauPowerHandler extends KanohiPowerHandler {

	@Override
	public void trigger(int powerLevel, ItemStack stackIn, World world, PlayerEntity playerIn) {
		playerIn.setAir(playerIn.getMaxAir());
	}

}
