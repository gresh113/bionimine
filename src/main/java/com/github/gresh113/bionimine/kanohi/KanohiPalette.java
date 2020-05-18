package com.github.gresh113.bionimine.kanohi;

import java.awt.Color;
import java.util.Optional;


import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

public enum KanohiPalette implements IStringSerializable {
	GRAY(0,"red", Color.GRAY, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE),
	RED(1,"red", new Color(255,95,105), new Color(255,28,43), new Color(221,23,37), new Color(180,19,30), new Color(120,13,20)),
	ORANGE(2,"orange", Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE),
	GREEN(3,"green", new Color(58,185,124), new Color(0,173,87), new Color(3,149,81), new Color(11,113,68), new Color(0,98,60)),
	BLUE(4, "blue", Color.BLUE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE),
	BROWN(5, "brown", Color.GRAY, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE),
	BLACK(6, "black", Color.BLACK, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE),
	WHITE(7, "white", Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE);
	
	//new Color(,,)
	private final String name;
	private final int NBTTag;
	private final Color color1;
	private final Color color2;
	private final Color color3;
	private final Color color4;
	private final Color color5;

	@Override
	public String getName() {
		return name;
	}
	public Color getColorLayer1() {
		return color1;
	}
	public Color getColorLayer2() {
		return color2;
	}
	public Color getColorLayer3() {
		return color3;
	}
	public Color getColorLayer4() {
		return color4;
	}
	Color getColorLayer5() {
		return color5;
	}
	
	//public Color getRenderColor() {return color;}
	
	KanohiPalette(int NBTtagIn, String nameIn, Color colorIn_1, Color colorIn_2, Color colorIn_3, Color colorIn_4, Color colorIn_5) {
		this.name = nameIn;
		this.NBTTag = NBTtagIn;
		this.color1 = colorIn_1;
		this.color2 = colorIn_2;
		this.color3 = colorIn_3;
		this.color4 = colorIn_4;
		this.color5 = colorIn_5;
		
	}
	
	public static KanohiPalette fromNBT(CompoundNBT compoundNBT, String tagname)
    {
      byte colorID = 0;  // default in case of error
      if (compoundNBT != null && compoundNBT.contains(tagname)) {
        colorID = compoundNBT.getByte(tagname);
      }
      Optional<KanohiPalette> color = getColorFromID(colorID);
      return color.orElse(GRAY); // default is gray
    }
	
	public void putIntoNBT(CompoundNBT compoundNBT, String tagname)
    {
      compoundNBT.putByte(tagname, (byte) NBTTag);
    }

    private static Optional<KanohiPalette> getColorFromID(byte ID) {
      for (KanohiPalette flavour : KanohiPalette.values()) {
        if (flavour.NBTTag == ID) return Optional.of(flavour);
      }
      return Optional.empty();
    }

  }

