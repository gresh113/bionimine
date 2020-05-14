package com.github.gresh113.bionimine.state.properties;

import net.minecraft.util.IStringSerializable;

public enum Mask implements IStringSerializable{
	NONE("none"),
	hau("hau"),
	miru("miru"),
	kaukau("kaukau"),
	kakama("kakama"),
	pakari("pakari");

	private final String name;
	
	private Mask(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

}
