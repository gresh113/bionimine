package com.github.gresh113.bionimine.kanohi;



public class Kanohi {

	public static Kanohi TAHUS_HAU = new Kanohi(KanohiPowerLevel.GREAT, KanohiAbility.HAU, KanohiShape.HAU_GREAT, KanohiType.STANDARD);
	//public static Kanohi LEWAS_MIRU = new Kanohi(KanohiPowerLevel.GREAT, KanohiAbility.MIRU, KanohiShape.MIRU_GREAT, KanohiType.STANDARD);
	
	private final KanohiPowerLevel power;
	private final KanohiAbility ability;
	private final KanohiType type;
	private final String name;
	//private final Element element;
	private KanohiShape defaultShape;
	//private KanohiPalette palette;
	
	public Kanohi(KanohiPowerLevel powerIn, KanohiAbility abilityIn, KanohiShape shapeIn, KanohiType typeIn) {
	      this.power = powerIn;
	      this.ability = abilityIn;	
	      this.defaultShape = shapeIn;
	      this.type = typeIn;
	      //this.element = elementIn;
	      //this.palette = paletteIn;
	      this.name = power.getPowerLevelString() + "_" + ability.getName();
	   }

	public KanohiPowerLevel getPowerLevel() {
		return this.power;

	}

	public KanohiAbility getAbility() {
		return this.ability;
	}

	public KanohiShape getDefaultShape() {
		return this.defaultShape;
	}
	public Boolean hasDefaultShape() {
		return this.defaultShape != null ? true : false;
	}

	public KanohiType getType() {
		return this.type;
	}
	
	//public KanohiPalette getPalette() {return this.palette;}
	//public void setPalette(KanohiPalette paletteIn) {this.palette = paletteIn;}

	public String getName() {
		return (this.power == KanohiPowerLevel.POWERLESS) ? "powerless_kanohi" : this.name;
	}
	
	public String getFormattedName() {
		String name = this.power.getPowerLevelString() + " " + this.ability.getName();
		return (this.power == KanohiPowerLevel.POWERLESS) ? "powerless_kanohi" : name;
	}
	
	public String getDescriptionText() {
		//String powerString = this.power.toString();
		//String capitalizedPowerString= powerString.substring(0, 1).toUpperCase() + powerString.substring(1);
		String name = this.power.toString();
		String laterText = 
				(this.power != KanohiPowerLevel.POWERLESS) ? 
						(" Mask of " + this.ability.getDescriptor()) : 
						(" Mask");
		return name + laterText;
	}
}
