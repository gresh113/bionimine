package com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great;

import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.KanohiPowerHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class GreatKakamaPowerHandler extends KanohiPowerHandler {

	@Override
	public void trigger(int powerLevel, ItemStack stackIn, World world, PlayerEntity playerIn) {
		if (powerLevel == 1) {
			playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 1, false, false, false));
		}
		if (powerLevel == 2) {
			playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 5, false, false, false));
		}
		if (powerLevel == 3) {
			playerIn.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 10, false, false, false));
		}
	}

}
