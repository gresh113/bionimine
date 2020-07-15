package com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers;

import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.xml.bind.Element;

public class EarthTraversalHandler extends TraversalHandler {



	@Override
	protected void doTraversal(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		BlockPos pos = playerentity.func_233580_cy_();
		BlockState downstate = worldIn.getBlockState(pos.down());
		if (Elements.isEarth(downstate)) {
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
