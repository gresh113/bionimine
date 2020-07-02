package com.github.gresh113.bionimine.objects.toagear.elementalabilities;

import java.util.Random;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class ToaAbilityHandler {
	
	protected static final Random random = new Random();

	public abstract void trigger(ItemStack stackIn, World worldIn, PlayerEntity playerentity);
	
	public abstract String getAbilityTypeName();

}
