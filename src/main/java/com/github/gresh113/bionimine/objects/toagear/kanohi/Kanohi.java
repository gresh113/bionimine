package com.github.gresh113.bionimine.objects.toagear.kanohi;

import com.github.gresh113.bionimine.objects.toagear.ArmorPalette;

public class Kanohi {

	public static Kanohi TAHUS_HAU = new Kanohi(KanohiPower.GREAT_HAU, KanohiType.STANDARD);
	
	private final KanohiPower power;
	private final KanohiType type;
	private final String name;
	//private final Element element;
	//private KanohiPalette palette;
	
	public Kanohi(KanohiPower powerIn, KanohiType typeIn) {
	     // this.power = powerIn;
	      this.power = powerIn;	
	      this.type = typeIn;
	      //this.element = elementIn;
	      //this.palette = paletteIn;
	      this.name = power.getUppercaseName();
	   }

	public KanohiPower getPower() {
		return this.power;
		}

	public KanohiShape getDefaultShape() {
		return this.power.getDefaultShape();
		}
	public Boolean hasDefaultShape() {
		return this.power.getDefaultShape() != null ? true : false;
		}
	
	public ArmorPalette getDefaultPalette() {
		return this.power.getDefaultPalette();
		}
	public Boolean hasDefaultPalette() {
		return this.power.getDefaultPalette() != null ? true : false;
		}

	public KanohiType getType() {
		return this.type;
		}
	
	//public KanohiPalette getPalette() {return this.palette;}
	//public void setPalette(KanohiPalette paletteIn) {this.palette = paletteIn;}

	public String getName() {
		return this.power.getLowercaseName();
	}
	
	public String getFormattedName() {
		return name;
	}
	
	public String getDescriptionText() {
		return this.power.getDescriptor();
	}
}
