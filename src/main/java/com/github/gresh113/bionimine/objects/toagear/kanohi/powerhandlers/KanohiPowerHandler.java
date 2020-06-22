package com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class KanohiPowerHandler {
	
	public abstract void trigger(int powerLevel, ItemStack stackIn, World world, PlayerEntity playerIn);

}
