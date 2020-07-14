package com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class WaterMeleeHandler extends ChargedMeleeHandler {
    @Override
    public void elementalEntityHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.setAir(0);
        target.attackEntityFrom(DamageSource.DROWN, 5);
    }
}