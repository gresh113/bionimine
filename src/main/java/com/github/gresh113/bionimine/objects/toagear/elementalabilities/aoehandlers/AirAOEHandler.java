package com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AirAOEHandler extends AOEAttackHandler {

	@Override
	protected void doAttackDamage(Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.FALL, 5.0F);
		entityIn.setMotion(0, 1, 0);
	}

	@Override
	protected void handleParticles(World worldIn, BlockPos pos) {
		for (double i = 2; i <= 10; ++i) {
			worldIn.addParticle(ParticleTypes.POOF, pos.getX() + .05, pos.getY() + (i / 2), pos.getZ() + .05, 0, .5, 0);
		}
	}

}
