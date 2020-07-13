package com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers;

import com.github.gresh113.bionimine.objects.toagear.elementalabilities.ToaAbilityHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract class TraversalHandler extends ToaAbilityHandler{
	private static final String name = "Traversal";
	protected int cooldownTicks = 50;
	@Override
	public String getAbilityTypeName() {return name;}

	@Override
	public void trigger(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		doTraversal(stackIn, worldIn, playerentity);
		//playerentity.getCooldownTracker().setCooldown(stackIn.getItem(), this.cooldownTicks );
		//this.handleEnergy(playerentity); Moved to the individual handlers
	}

	protected abstract void doTraversal(ItemStack stackIn, World worldIn, PlayerEntity playerentity);

}
