package com.github.gresh113.bionimine.toa_gear;

import net.minecraft.util.IStringSerializable;

public enum Elements implements IStringSerializable{
	NONE("none"),
	FIRE("fire"),
	AIR("air"),
	WATER("water"),
	EARTH("earth"),
	STONE("stone"),
	ICE("ice"),
	LIGHT("light"),
	SHADOW("shadow"),
	SONICS("sonics"),
	GRAVITY("gravity"),
	PLASMA("plasma"),
	MAGNETISM("magnetism"),
	THE_GREEN("the_green"),
	LIGHTNING("lightning"),
	IRON("iron"),
	PSIONICS("psionics");
	
	private String name;
	Elements(String nameIn) {
		this.name = nameIn;
	}
	@Override
	public String getName() {
		return name;
	}
	
	

}
