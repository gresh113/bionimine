package com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great;

import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.KanohiPowerHandler;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class GreatRuruPowerHandler extends KanohiPowerHandler {

	@Override
	public void trigger(int powerLevel, ItemStack stackIn, World world, PlayerEntity playerIn) {

		playerIn.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 0, 1, false, false, false));
		playerIn.addPotionEffect(new EffectInstance(Effects.GLOWING, 0, 1, false, false, false));
		
	}
}

