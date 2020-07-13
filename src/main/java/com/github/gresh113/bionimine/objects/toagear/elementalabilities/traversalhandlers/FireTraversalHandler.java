package com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireTraversalHandler extends TraversalHandler {

	@Override
	protected void doTraversal(ItemStack stackIn, World worldIn, PlayerEntity player) {
		BlockPos pos = player.func_233580_cy_();
		player.addVelocity(0, 2, 0);
		SoundEvent soundevent = SoundEvents.ITEM_FIRECHARGE_USE;
		worldIn.playMovingSound((PlayerEntity) null, player, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
		for (byte i = 0; i < 10; ++i) {
			pos = player.func_233580_cy_();
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ(), i / 10, -10, i / 10);
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + .5, pos.getY(), pos.getZ(), i / 10, -10, i / 10);
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX() - .5, pos.getY(), pos.getZ(), i / 10, -10, i / 10);
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX() + .5, pos.getY(), pos.getZ() + .5, i / 10, -10, i / 10);
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX() - .5, pos.getY(), pos.getZ() - .5, i / 10, -10, i / 10);
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX(), pos.getY(), pos.getZ() + .5, i / 10, -10, i / 10);
			worldIn.addParticle(ParticleTypes.FLAME, pos.getX() - .5, pos.getY(), pos.getZ() - .5, i / 10, -10, i / 10);

		}
		player.addPotionEffect(new EffectInstance(Effects.SLOW_FALLING, 100, 1, false, false));
		handleEnergy(player);
	}
}
