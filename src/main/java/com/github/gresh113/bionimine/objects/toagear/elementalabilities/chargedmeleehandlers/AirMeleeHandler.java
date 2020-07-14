package com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class AirMeleeHandler extends ChargedMeleeHandler {
    @Override
    public void elementalEntityHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.addVelocity(attacker.getLookVec().getX()*3,attacker.getLookVec().getY()*5,attacker.getLookVec().getZ()*3);
        target.attackEntityFrom(DamageSource.FALL, 5);
    }
}