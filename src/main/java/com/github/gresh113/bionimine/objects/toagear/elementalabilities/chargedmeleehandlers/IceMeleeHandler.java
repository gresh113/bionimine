package com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers;

import com.github.gresh113.bionimine.Bionimine;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;

public class IceMeleeHandler extends ChargedMeleeHandler {
    @Override
    public void elementalEntityHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.attackEntityFrom(DamageSource.GENERIC, 5.0F);
        target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 60, 1, false, true));

    }
}
