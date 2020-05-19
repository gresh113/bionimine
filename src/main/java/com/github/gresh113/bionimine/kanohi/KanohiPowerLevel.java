package com.github.gresh113.bionimine.kanohi;

public enum KanohiPowerLevel {
	POWERLESS("Powerless"),
	NOBLE("Noble"),
	GREAT("Great"),
	LEGENDARY("Legendary");
	
	private final String powerLevel;
	
	KanohiPowerLevel(String powerLevelIn) {
		this.powerLevel = powerLevelIn;
	}
	
	public String getPowerLevelString()	{
		return this.powerLevel;
	}
	
	@Override
	public String toString() {
		return this.getPowerLevelString();
	}

}
