package com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers;

import com.github.gresh113.bionimine.Bionimine;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.ChargedMeleeHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class FireMeleeHandler extends ChargedMeleeHandler {
    @Override
    public void elementalEntityHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getFireTimer() <= 0){
            Bionimine.LOGGER.info("Fire timer 0");
            target.setFire(10);
            target.attackEntityFrom(DamageSource.IN_FIRE, 5);
        }
    }
}
