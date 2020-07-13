package com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers;

import com.github.gresh113.bionimine.registration.BlockRegistration;
import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class IceTraversalHandler extends TraversalHandler {
	@Override
	protected void doTraversal(ItemStack stackIn, World worldIn, PlayerEntity player) {
		BlockPos pos = player.func_233580_cy_();
		if (worldIn.getBlockState(pos) == Blocks.AIR.getDefaultState() || worldIn.getBlockState(pos) == BlockRegistration.frost.get().getDefaultState()) {
			for (byte i = 0; i < 7; ++i) {
				pos = pos.offset(player.getAdjustedHorizontalFacing());
				if (worldIn.getBlockState(pos) == Blocks.AIR.getDefaultState())
					worldIn.setBlockState(pos, BlockRegistration.frost.get().getDefaultState());
			}
			Vector3f vec = player.getAdjustedHorizontalFacing().toVector3f();
			vec.mul(3);
			player.addVelocity(vec.getX(), vec.getY(), vec.getZ());
			handleEnergy(player);
		}
	}
}
