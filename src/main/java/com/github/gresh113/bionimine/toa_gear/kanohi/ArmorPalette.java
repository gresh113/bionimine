package com.github.gresh113.bionimine.toa_gear.kanohi;

import java.awt.Color;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

public enum ArmorPalette implements IStringSerializable {
	GRAY("gray", new Color(143,143,143), new Color(97,97,97), new Color(83,83,83), new Color(68,68,68),new Color(45,45,45)),
	RED("red", new Color(255,95,105), new Color(255,28,43), new Color(221,23,37), new Color(180,19,30), new Color(120,13,20)),
	GREEN("green", new Color(58,185,124), new Color(0,173,87), new Color(3,149,81), new Color(11,113,68), new Color(0,98,60)),
	BLACK("black", new Color(95,95,99), new Color(41,41,43), new Color(30,30,31), new Color(17,17,17), new Color(0,0,0)),
	WHITE ("white", new Color(255,255,255), new Color(251,255,255), new Color(223,238,238), new Color(190,201,202), new Color(145,152,153)),
	BLUE("blue", new Color(80,130,217), new Color(24,94,217), new Color(20,82,188), new Color(16,66,153), new Color(11,44,102)),
	BROWN("brown", new Color(96,64,40), new Color(89,42,9), new Color(75,34,5), new Color(58,26,2), new Color(33,12,0)),
	GOLD("gold", new Color(195,122,3), new Color(158,99,12), new Color(113,68,0), new Color(89,55,0), new Color(36,18,0)),
	ORANGE("orange", new Color(255,172,94), new Color(255,138,28), new Color(221,118,23), new Color(180,97,19), new Color(120,65,13)),
	YELLOW("yellow", new Color(255,217,94), new Color(255,202,28), new Color(221,174,23), new Color(180,142,19), new Color(120,95,13)),
	LIGHT_BLUE("light_blue", Color.decode("#83C2FF"), Color.decode("#4DA5FF"), Color.decode("#4998EC"), Color.decode("#4585CA"), Color.decode("#406B98")),
	PRUPLE("purple", new Color(62,42,115), new Color(41,13,113), new Color(36,10,99), new Color(29,9,81), new Color(19,6,54)),
	TEAL("teal", new Color(14,149,138), Color.decode("#009166"), Color.decode("#007C5E"), Color.decode("#005E4D"), Color.decode("#005145")),
	LIME_GREEN("lime_green", Color.decode("#37CA5F"), Color.decode("#17C435"), Color.decode("#17AB38"), Color.decode("#178736"), Color.decode("#177834")),
	SAND_BLUE("sand_blue", Color.decode("#70A4C3"), Color.decode("#478EB9"), Color.decode("#4282A5"), Color.decode("#3C6F8E"), Color.decode("#335569")),
	BURNT_ORANGE("burnt_orange", Color.decode("#F7A029"), Color.decode("#EA7D10"), Color.decode("#BE650F"), Color.decode("#874A0E"), Color.decode("#492C11")),
	TAN("tan", Color.decode("#EDCF95"), Color.decode("#F2CE77"), Color.decode("#D0B266"), Color.decode("#7A6A42"), Color.decode("#54432B")),
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
	
	ArmorPalette(String nameIn, Color colorIn_1, Color colorIn_2, Color colorIn_3, Color colorIn_4, Color colorIn_5) {
		this.name = nameIn;
		//this.NBTTag = NBTtagIn;
		this.color1 = colorIn_1;
		this.color2 = colorIn_2;
		this.color3 = colorIn_3;
		this.color4 = colorIn_4;
		this.color5 = colorIn_5;
		
	}
	
	public static ArmorPalette getPaletteFromName(String nameIn) {
	      for (ArmorPalette palette : ArmorPalette.values()) {
	        if (palette.name.equals(nameIn)) {return palette;}
	      }
	      return ArmorPalette.GRAY;
	    }
	
	public static ArmorPalette fromNBT(CompoundNBT compoundNBT, String tagname)
    {
      String paletteName = "gray";  // Default
      if (compoundNBT != null && compoundNBT.contains(tagname)) {
        paletteName = compoundNBT.getString(tagname);
      }
      ArmorPalette palette = getPaletteFromName(paletteName);
      return palette;
    }   
    
    public void putIntoNBT(CompoundNBT compoundNBT, String tagname)
    {
      compoundNBT.putString(tagname, name);
    }

  }

