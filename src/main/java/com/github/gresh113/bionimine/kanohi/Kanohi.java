package com.github.gresh113.bionimine.kanohi;

import net.minecraft.item.DyeColor;

public class Kanohi {

	public static Kanohi TAHUS_HAU = new Kanohi(KanohiPowerLevel.GREAT, KanohiAbility.HAU, KanohiShape.HAU_GREAT, KanohiType.STANDARD,  DyeColor.RED);
	public static Kanohi LEWAS_MIRU = new Kanohi(KanohiPowerLevel.GREAT, KanohiAbility.MIRU, KanohiShape.MIRU_GREAT, KanohiType.STANDARD, DyeColor.GREEN);
	
	private final KanohiPowerLevel power;
	private final KanohiAbility ability;
	private final KanohiShape shape;
	private final KanohiType type;
	//private final Element element;
	private final DyeColor color;
	private final String name;
	
	public Kanohi(KanohiPowerLevel powerIn, KanohiAbility abilityIn, KanohiShape shapeIn, KanohiType typeIn, DyeColor colorIn) {
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

	public KanohiType getType() {
		return this.type;
	}

//	public Element getElement() {
//		return this.element;
//	}
	
	public DyeColor getColor() {
		return this.color;
	}

	public String getName() {
		return this.name;
	}
	
	public String getDescriptionText() {
		//String powerString = this.power.toString();
		//String capitalizedPowerString= powerString.substring(0, 1).toUpperCase() + powerString.substring(1);
		String text = this.power.toString() + " Mask of " + this.ability.getDescriptor();
		return text;
	}
}
