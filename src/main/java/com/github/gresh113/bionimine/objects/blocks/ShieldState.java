package com.github.gresh113.bionimine.objects.blocks;

import net.minecraft.util.IStringSerializable;

public enum ShieldState implements IStringSerializable{
	BLUE("blue"),
	RED("red");

	private String name;
	ShieldState(String string) {
		name = string;
	}

//	@Override
//	public String getName() {
//		return name;
//	}

	@Override
	public String func_176610_l() {
		return name;
	}

}
