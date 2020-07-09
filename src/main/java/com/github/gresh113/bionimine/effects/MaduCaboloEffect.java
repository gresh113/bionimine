package com.github.gresh113.bionimine.effects;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.registration.PotionRegistration;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.DamageSource;

public class MaduCaboloEffect extends Effect {
	private int color;

	protected MaduCaboloEffect(EffectType typeIn, int liquidColorIn) {
		super(typeIn, liquidColorIn);
		color = liquidColorIn;
	}

	public MaduCaboloEffect() {
		super(EffectType.HARMFUL, 3600);
		color = 3600;
	}

	@Override
	public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
		//if (this == PotionRegistration.MADU.get()) {
			if (entityLivingBaseIn instanceof CreatureEntity) {
				entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0F * amplifier);
			}

		//}

	}

	@Override
	public void affectEntity(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) {
		performEffect(entityLivingBaseIn, amplifier);
	}
	
	@Override
	public int getLiquidColor() {
		return color;
	}

}
