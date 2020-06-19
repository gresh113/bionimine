package com.github.gresh113.bionimine.state.properties;

import net.minecraft.util.IStringSerializable;

public enum MaskType implements IStringSerializable{
	NONE("none"),
	hau("hau"),
	miru("miru"),
	kaukau("kaukau"),
	kakama("kakama"),
	akaku("akaku"),
	pakari("pakari");

	private final String name;
	
	private MaskType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
