package com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers;

import com.google.common.collect.ImmutableList;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StoneTraversalHandler extends TraversalHandler {
	public static ImmutableList<BlockState> BLOCKS = ImmutableList.of( //@formatter:off
			Blocks.STONE.getDefaultState()
			,Blocks.COBBLESTONE.getDefaultState()
			,Blocks.MOSSY_COBBLESTONE.getDefaultState()
			,Blocks.SMOOTH_STONE.getDefaultState()
			,Blocks.STONE_BRICKS.getDefaultState()
			,Blocks.MOSSY_STONE_BRICKS.getDefaultState()
			
			,Blocks.SAND.getDefaultState()
			,Blocks.SANDSTONE.getDefaultState()
			,Blocks.SMOOTH_SANDSTONE.getDefaultState()
			,Blocks.CUT_SANDSTONE.getDefaultState()
			,Blocks.CHISELED_SANDSTONE.getDefaultState()
			
			,Blocks.RED_SAND.getDefaultState()
			,Blocks.RED_SANDSTONE.getDefaultState()
			,Blocks.SMOOTH_RED_SANDSTONE.getDefaultState()
			,Blocks.CHISELED_RED_SANDSTONE.getDefaultState()
			,Blocks.CUT_RED_SANDSTONE.getDefaultState()
			
			,Blocks.GRAVEL.getDefaultState()
			
			,Blocks.ANDESITE.getDefaultState()
			,Blocks.GRANITE.getDefaultState()
			,Blocks.DIORITE.getDefaultState()
			,Blocks.POLISHED_ANDESITE.getDefaultState()
			,Blocks.POLISHED_GRANITE.getDefaultState()
			,Blocks.POLISHED_DIORITE.getDefaultState()
			
			,Blocks.BLACKSTONE.getDefaultState()
			,Blocks.CHISELED_POLISHED_BLACKSTONE.getDefaultState()
			,Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getDefaultState()
			,Blocks.POLISHED_BLACKSTONE.getDefaultState()
			); //@formatter:on


	@Override
	protected void doTraversal(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		BlockPos pos = playerentity.func_233580_cy_();
		BlockState downstate = worldIn.getBlockState(pos.down());
		if (BLOCKS.contains(downstate)) {
			for (byte i = 0; i < 5; ++i) {
				pos = playerentity.func_233580_cy_();
				playerentity.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
				worldIn.setBlockState(pos.down(), downstate);
			}
			// Circle of particles
//			BlockParticleData particles = new BlockParticleData(ParticleTypes.BLOCK, Blocks.DIRT.getDefaultState());
//			pos = playerentity.func_233580_cy_();
//			for (byte j = 0; j < 36; j++) {
//				worldIn.addParticle(particles, pos.getX(), pos.getY(), pos.getZ(), xSpeed, ySpeed, zSpeed);
//			}
			handleEnergy(playerentity);
		}
	}

}
