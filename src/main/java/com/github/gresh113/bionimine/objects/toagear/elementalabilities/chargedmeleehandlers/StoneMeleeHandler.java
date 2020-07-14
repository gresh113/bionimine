package com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers;

import com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers.EarthTraversalHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers.StoneTraversalHandler;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StoneMeleeHandler extends ChargedMeleeHandler {
    @Override
    public void elementalEntityHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.attackEntityFrom(DamageSource.IN_WALL, 5.0F);
        BlockPos pos = target.func_233580_cy_();
        World world = target.getEntityWorld();
        target.setVelocity(0, 0, 0);
        BlockState downstate = world.getBlockState(pos.down());
        if (StoneTraversalHandler.BLOCKS.contains(downstate)) {
            for (byte i = 0; i < 5; ++i) {
                pos = target.func_233580_cy_();
                target.setPosition(pos.getX(), pos.getY() + 1, pos.getZ());
                world.setBlockState(pos.down(), downstate);
            }
        }
    }
}