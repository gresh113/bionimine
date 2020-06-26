package com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great;


import java.util.List;

import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.KanohiPowerHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GreatAkakuPowerHandler extends KanohiPowerHandler {

	@Override
	public void trigger(int powerLevel, ItemStack stackIn, World world, PlayerEntity playerIn) {
		if (!(world == null)) {
			int distance = powerLevel*20;
			BlockPos playerPos = new BlockPos(playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ());
			BlockPos pos = playerIn.func_233580_cy_();
			//Vec3d vector = new Vec3d(kanohiPowerLevel*7, kanohiPowerLevel*7, kanohiPowerLevel*7);
			AxisAlignedBB bb = new AxisAlignedBB(pos);
			bb = bb.expand(distance, distance, distance);
			List<Entity> entityList= world.getEntitiesWithinAABBExcludingEntity(playerIn, bb);
			for (Entity entity : entityList) {
				if (entity instanceof LivingEntity) {
					LivingEntity livingEntity = (LivingEntity) entity;
					livingEntity.addPotionEffect(new EffectInstance(Effects.GLOWING, 60, 1, false, true));
					
				}
			}
		}
	}
}
