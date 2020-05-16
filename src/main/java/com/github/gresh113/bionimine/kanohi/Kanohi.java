package com.github.gresh113.bionimine.kanohi;



public class Kanohi {

	public static Kanohi TAHUS_HAU = new Kanohi(KanohiPowerLevel.GREAT, KanohiAbility.HAU, KanohiShape.HAU_GREAT, KanohiType.STANDARD,  KanohiColor.RED);
	public static Kanohi LEWAS_MIRU = new Kanohi(KanohiPowerLevel.GREAT, KanohiAbility.MIRU, KanohiShape.MIRU_GREAT, KanohiType.STANDARD, KanohiColor.GREEN);
	
	private final KanohiPowerLevel power;
	private final KanohiAbility ability;
	private final KanohiType type;
	private final String name;
	//private final Element element;
	private KanohiShape shape;
	private KanohiColor color;
	
	public Kanohi(KanohiPowerLevel powerIn, KanohiAbility abilityIn, KanohiShape shapeIn, KanohiType typeIn, KanohiColor colorIn) {
	      this.power = powerIn;
	      this.ability = abilityIn;	
	      this.shape = shapeIn;
	      this.type = typeIn;
	      //this.element = elementIn;
	      this.color = colorIn;
	      this.name = power.getPowerLevelString() + "_" + ability.getName();
	   }

	public KanohiPowerLevel getPowerLevel() {
		return this.power;

	}

	public KanohiAbility getAbility() {
		return this.ability;
	}

	public KanohiShape getShape() {
		return this.shape;
	}
	public Boolean hasShape() {
		return this.shape != null ? true : false;
	}
	
	public void setShape(KanohiShape shapeIn) {
		this.shape = shapeIn;
	}

	public KanohiType getType() {
		return this.type;
	}

//	public Element getElement() {
//		return this.element;
//	}
	
	public KanohiColor getColor() {
		return this.color;
	}
	public void setColor(KanohiColor colorIn) {
		this.color = colorIn;
	}

	public String getName() {
		return this.name;
	}
	
	public String getFormattedName() {
		String name = this.power.getPowerLevelString() + " " + this.ability.getName();
		return name;
	}
	
	public String getDescriptionText() {
		//String powerString = this.power.toString();
		//String capitalizedPowerString= powerString.substring(0, 1).toUpperCase() + powerString.substring(1);
		String text = this.power.toString() + " Mask of " + this.ability.getDescriptor();
		return text;
	}
}
