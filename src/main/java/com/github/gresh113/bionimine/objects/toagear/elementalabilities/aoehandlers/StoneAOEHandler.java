package com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StoneAOEHandler extends AOEAttackHandler {

	@Override
	protected void doAttackDamage(Entity entityIn) {
		entityIn.attackEntityFrom(DamageSource.IN_WALL, 5.0F);
	}

	@Override
	protected void handleParticles(World worldIn, BlockPos pos) {
		for (double i = 2; i <= 10; ++i) {
			worldIn.addParticle(new BlockParticleData(ParticleTypes.BLOCK, Blocks.SMOOTH_STONE.getDefaultState()), pos.getX() + .05, pos.getY() + (i / 2), pos.getZ() + .05, 0, .5, 0);
			
			
		}
	}

}
