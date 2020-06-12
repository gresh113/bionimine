package com.github.gresh113.bionimine.capabilities;

public class ToaEnergy implements IToaEnergy{
	
	private int kanohiEnergy;
	private byte elementalEnergy;

	@Override
	public int getKanohiEnergy() {
		return kanohiEnergy;
	}

	@Override
	public byte getElementalEnergy() {
		return elementalEnergy;
	}

	@Override
	public void setKanohiEnergy(int energy) {
		kanohiEnergy = energy;
		
	}

	@Override
	public void setElementalEnergy(byte energy) {
		elementalEnergy = energy;
		
	}

}
