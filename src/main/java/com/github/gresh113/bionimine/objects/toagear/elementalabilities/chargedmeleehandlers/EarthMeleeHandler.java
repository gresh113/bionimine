package com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers;

import com.github.gresh113.bionimine.objects.toagear.elementalabilities.Elements;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EarthMeleeHandler extends ChargedMeleeHandler {
    @Override
    public void elementalEntityHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.attackEntityFrom(DamageSource.IN_WALL, 5.0F);
        BlockPos pos = target.func_233580_cy_();
        World world = target.getEntityWorld();
        target.setVelocity(0,0,0);
        if (!world.isRemote()) {
            for (byte i = 1; i < 4; ++i) {
                BlockState downstate = world.getBlockState(pos.down(i));
                if (Elements.isEarth(downstate)){
                    world.destroyBlock(pos.down(i), true);
                } else break;
            }
        }
    }
}