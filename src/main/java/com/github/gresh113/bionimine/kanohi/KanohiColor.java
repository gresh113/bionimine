package com.github.gresh113.bionimine.kanohi;

import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.IStringSerializable;

public enum KanohiColor implements IStringSerializable{
		WHITE("white", 16383998, MaterialColor.SNOW),
	   BLUE("blue", 3949738, MaterialColor.BLUE),
	   BROWN("brown", 8606770, MaterialColor.BROWN),
	   GREEN("green", 6192150, MaterialColor.GREEN),
	   RED("red", 11546150, MaterialColor.RED),
	   BLACK("black", 1908001, MaterialColor.BLACK);
	
	private final String name;
	private final int colorValue;
	private final MaterialColor material;

	KanohiColor(String nameIn, int colorValueIn, MaterialColor materialIn) {
		this.name = nameIn;
		this.colorValue = colorValueIn;
		this.material = materialIn;
	}

	public String getName() {
		return name;
	}
	public int getColor() {
		return colorValue;
	}
	public MaterialColor getMaterialColor() {
		return material;
	}
	   

}
