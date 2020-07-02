package com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers;

import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WaterAOEHandler extends AOEAttackHandler {

	@Override
	protected void doAttackDamage(Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.DROWN, 5.0F);
		entityIn.setAir(0);
	}

	@Override
	protected void handleParticles(World worldIn, BlockPos pos) {
		for (double i = 2; i <= 10; ++i) {
			worldIn.addParticle(ParticleTypes.BUBBLE_COLUMN_UP, pos.getX() + .05, pos.getY() + (i / 2), pos.getZ() + .05, 0, .5, 0);
			worldIn.addParticle(ParticleTypes.FALLING_WATER, pos.getX() + .05, pos.getY() + ((i / 2) + 0.2), pos.getZ() + .05, 0, .5, 0);
		}
	}

}
