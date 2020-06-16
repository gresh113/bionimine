package com.github.gresh113.bionimine.capabilities;

public class ToaEnergy implements IToaEnergy{
	
	private int kanohiEnergy;
	private int elementalEnergy;
	public static int maxKanohiEnergy = 12000;
	public static int maxElementalEnergy = 6000;

	@Override
	public int getKanohiEnergy() {
		return kanohiEnergy;
	}

	@Override
	public int getElementalEnergy() {
		return elementalEnergy;
	}

	@Override
	public void setKanohiEnergy(int energy) {
		kanohiEnergy = energy;
		
	}

	@Override
	public void setElementalEnergy(int energy) {
		elementalEnergy = energy;
		
	}

}
