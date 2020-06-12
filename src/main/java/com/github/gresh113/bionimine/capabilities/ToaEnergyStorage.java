package com.github.gresh113.bionimine.capabilities;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class ToaEnergyStorage implements IStorage<IToaEnergy>{
	@Override
	public INBT writeNBT(Capability<IToaEnergy> capability, IToaEnergy instance, Direction side) {
		CompoundNBT tag = new CompoundNBT();
		tag.putInt("KanohiEnergy", instance.getKanohiEnergy());
		tag.putByte("ElementalEnergy", instance.getElementalEnergy());
		return tag;
	}

	@Override
	public void readNBT(Capability<IToaEnergy> capability, IToaEnergy instance, Direction side, INBT nbt) {
		CompoundNBT tag = (CompoundNBT) nbt;
		byte eEnergy = tag.getByte("ElementalEnergy");
		int kEnergy = tag.getInt("KanohiEnergy");
		instance.setElementalEnergy(eEnergy);
		instance.setKanohiEnergy(kEnergy);
		
	}

}
