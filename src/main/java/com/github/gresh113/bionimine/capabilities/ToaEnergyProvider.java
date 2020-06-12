package com.github.gresh113.bionimine.capabilities;

import com.github.gresh113.bionimine.Bionimine;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ToaEnergyProvider implements ICapabilitySerializable<INBT>{
	@CapabilityInject(IToaEnergy.class)
	public static final Capability<IToaEnergy> TOA_ENERGY = null;
	
	private LazyOptional<IToaEnergy> instance = LazyOptional.of(TOA_ENERGY::getDefaultInstance);
	
	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction sideIn) {
		return TOA_ENERGY.orEmpty(cap, instance);
	}

	@Override
	public INBT serializeNBT() {
		return TOA_ENERGY.getStorage().writeNBT(TOA_ENERGY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null);
	
	}

	@Override
	public void deserializeNBT(INBT nbt) {
		TOA_ENERGY.getStorage().readNBT(TOA_ENERGY, instance.orElseThrow(() -> new IllegalArgumentException("LazyOptional must not be empty!")), null, nbt);
	}
	
	
}
