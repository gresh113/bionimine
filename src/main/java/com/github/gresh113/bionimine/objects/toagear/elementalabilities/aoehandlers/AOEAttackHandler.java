package com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers;

import java.util.List;

import com.github.gresh113.bionimine.objects.toagear.elementalabilities.ToaAbilityHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AOEAttackHandler extends ToaAbilityHandler{
	private static final String name = "AOE";
	private int cooldownTicks = 50;
	@Override
	public String getAbilityTypeName() {return name;}

	@Override
	public void trigger(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		callAoe(stackIn, worldIn, playerentity);
		playerentity.getCooldownTracker().setCooldown(stackIn.getItem(), cooldownTicks );
	}

	protected void callAoe(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		BlockPos playerPos = playerentity.func_233580_cy_();
		AxisAlignedBB aabb = new AxisAlignedBB(playerPos.north().west().north().west(), playerPos.south().east().south().east());
		for (double x = aabb.minX; x <= aabb.maxX; ++x) {
			for (double z = aabb.minZ; z <= aabb.maxZ; ++z) {
				BlockPos pos = new BlockPos(x, playerPos.getY(), z);
				handleParticles(worldIn, pos);
				List<Entity> entities = worldIn.getEntitiesWithinAABBExcludingEntity(playerentity, new AxisAlignedBB(pos).expand(1, 0, 1));
				for (Entity entity : entities) {
					doAttackDamage(entity);
				}
			}
		}
	}

	protected abstract void handleParticles(World worldIn, BlockPos pos);
	protected abstract void doAttackDamage(Entity entityIn);

	
	
	

}
