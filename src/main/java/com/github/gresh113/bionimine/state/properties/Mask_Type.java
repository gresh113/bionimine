package com.github.gresh113.bionimine.state.properties;

import net.minecraft.util.IStringSerializable;

public enum Mask_Type implements IStringSerializable{
	NONE("none"),
	hau("hau"),
	miru("miru"),
	kaukau("kaukau"),
	kakama("kakama"),
	pakari("pakari");

	private final String name;
	
	private Mask_Type(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
