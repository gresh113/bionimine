package com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers;

import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WaterTraversalHandler extends TraversalHandler {

	//Pretty much copy-pasted from the trident riptide enchantment code
	@Override
	protected void doTraversal(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
		if (playerentity.isWet()) {
			float f7 = playerentity.rotationYaw;
			float f = playerentity.rotationPitch;
			float f1 = -MathHelper.sin(f7 * ((float) Math.PI / 180F)) * MathHelper.cos(f * ((float) Math.PI / 180F));
			float f2 = -MathHelper.sin(f * ((float) Math.PI / 180F));
			float f3 = MathHelper.cos(f7 * ((float) Math.PI / 180F)) * MathHelper.cos(f * ((float) Math.PI / 180F));
			float f4 = MathHelper.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
			float f5 = 3.0F * ((1.0F + (float) 2) / 4.0F);
			f1 = f1 * (f5 / f4);
			f2 = f2 * (f5 / f4);
			f3 = f3 * (f5 / f4);
			playerentity.addVelocity((double) f1, (double) f2, (double) f3);
			playerentity.startSpinAttack(20);
			if (playerentity.func_233570_aj_()) {
				//float f6 = 1.1999999F;
				playerentity.move(MoverType.SELF, new Vector3d(0.0D, (double) 1.1999999F, 0.0D));
			}
			SoundEvent soundevent = SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE;
			worldIn.playMovingSound((PlayerEntity) null, playerentity, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
			handleEnergy(playerentity);
		}

	}

}
