package com.github.gresh113.bionimine.kanohi;

import java.awt.Color;
import java.util.Optional;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

public enum KanohiColor implements IStringSerializable {
	RED(0,"red", Color.RED),
	ORANGE(1,"orange", Color.ORANGE),
	GREEN(2,"green", Color.GREEN),
	BLUE(3, "blue", Color.BLUE),
	BROWN(4, "brown", Color.GRAY),
	BLACK(5, "black", Color.BLACK),
	WHITE(6, "white", Color.WHITE);
	
	private final String name;
	private final int NBTTag;
	private final Color color;

	@Override
	public String getName() {
		return name;
	}
	public Color getColor() {
		return color;
	}
	
	KanohiColor(int NBTtagIn, String nameIn, Color colorIn) {
		this.name = nameIn;
		this.NBTTag = NBTtagIn;
		this.color = colorIn;
		
	}

}
