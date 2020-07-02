package com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireAOEHandler extends AOEAttackHandler {
	
	@Override
	protected void doAttackDamage(Entity entityIn) {
		if (!entityIn.func_230279_az_()) {
			entityIn.func_241209_g_(entityIn.getFireTimer() + 1);
			if (entityIn.getFireTimer() == 0) {
				entityIn.attackEntityFrom(DamageSource.IN_FIRE, 2.0F);
				entityIn.setFire(8);
			}
		}
	}

	@Override
	protected void handleParticles(World worldIn, BlockPos pos) {
		for (double i = 2; i <= 6; ++i) {
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + .05, pos.getY() + (i / 2), pos.getZ() + .05, 0, .5, 0);
		}
		
	}

}
