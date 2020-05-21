package com.github.gresh113.bionimine.kanohi;

import java.awt.Color;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

public enum KanohiPalette implements IStringSerializable {
	GRAY("gray", new Color(143,143,143), new Color(97,97,97), new Color(83,83,83), new Color(68,68,68),new Color(45,45,45)),
	RED("red", new Color(255,95,105), new Color(255,28,43), new Color(221,23,37), new Color(180,19,30), new Color(120,13,20)),
	GREEN("green", new Color(58,185,124), new Color(0,173,87), new Color(3,149,81), new Color(11,113,68), new Color(0,98,60)),
	BLACK("black", new Color(95,95,99), new Color(41,41,43), new Color(30,30,31), new Color(17,17,17), new Color(0,0,0)),
	WHITE ("white", new Color(255,255,255), new Color(251,255,255), new Color(223,238,238), new Color(190,201,202), new Color(145,152,153)),
	BLUE("blue", new Color(80,130,217), new Color(24,94,217), new Color(20,82,188), new Color(16,66,153), new Color(11,44,102)),
	BROWN("brown", new Color(96,64,40), new Color(89,42,9), new Color(75,34,5), new Color(58,26,2), new Color(33,12,0)),
	GOLD("gold", new Color(195,122,3), new Color(158,99,12), new Color(113,68,0), new Color(89,55,0), new Color(36,18,0)),
	
	ORANGE("orange", Color.ORANGE, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE),
	;
	
	//new Color(,,)
	private final String name;
	//private final int NBTTag;
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
	
	KanohiPalette(String nameIn, Color colorIn_1, Color colorIn_2, Color colorIn_3, Color colorIn_4, Color colorIn_5) {
		this.name = nameIn;
		//this.NBTTag = NBTtagIn;
		this.color1 = colorIn_1;
		this.color2 = colorIn_2;
		this.color3 = colorIn_3;
		this.color4 = colorIn_4;
		this.color5 = colorIn_5;
		
	}
	
	public static KanohiPalette getPaletteFromName(String nameIn) {
	      for (KanohiPalette palette : KanohiPalette.values()) {
	        if (palette.name.equals(nameIn)) {return palette;}
	      }
	      return KanohiPalette.GRAY;
	    }
	
	public static KanohiPalette fromNBT(CompoundNBT compoundNBT, String tagname)
    {
      String paletteName = "gray";  // Default
      if (compoundNBT != null && compoundNBT.contains(tagname)) {
        paletteName = compoundNBT.getString(tagname);
      }
      KanohiPalette palette = getPaletteFromName(paletteName);
      return palette;
    }   
    
    public void putIntoNBT(CompoundNBT compoundNBT, String tagname)
    {
      compoundNBT.putString(tagname, name);
    }

  }

