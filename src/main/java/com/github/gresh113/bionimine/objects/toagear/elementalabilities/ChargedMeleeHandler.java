package com.github.gresh113.bionimine.objects.toagear.elementalabilities;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;

public abstract class ChargedMeleeHandler extends ToaAbilityHandler{
    private final String name = "Elemental Strike";
    @Override
    public String getAbilityTypeName() {return name;}
    @Override
    public void trigger(ItemStack stackIn, World worldIn, PlayerEntity playerentity) {
        CompoundNBT compoundNBT = stackIn.getOrCreateTag();
        String tag = "charged";
        if (compoundNBT.contains(tag)){
            boolean charged = compoundNBT.getBoolean(tag);
            charged = !charged;
            compoundNBT.putBoolean(tag,charged);
            stackIn.setTag(compoundNBT);
        }
    }

    public abstract void elementalEntityHit(ItemStack stack, LivingEntity target, LivingEntity attacker);

}
