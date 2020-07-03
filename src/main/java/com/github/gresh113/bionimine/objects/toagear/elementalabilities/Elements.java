package com.github.gresh113.bionimine.objects.toagear.elementalabilities;

import net.minecraft.util.IStringSerializable;

public enum Elements implements IStringSerializable {
	//@formatter:off
	NONE("none", 0, ToaAbilityHolder.NONE), 
	FIRE("fire", 1, ToaAbilityHolder.FIRE), 
	AIR("air", 2, ToaAbilityHolder.AIR), 
	WATER("water", 3, ToaAbilityHolder.WATER), 
	EARTH("earth", 4, ToaAbilityHolder.EARTH), 
	STONE("stone", 5, ToaAbilityHolder.STONE), 
	ICE("ice", 6, ToaAbilityHolder.ICE), 
	
	LIGHT("light", 7, ToaAbilityHolder.NONE), 
	SHADOW("shadow", 8, ToaAbilityHolder.NONE), 
	
	SONICS("sonics", 9, ToaAbilityHolder.NONE), 
	GRAVITY("gravity", 10, ToaAbilityHolder.NONE), 
	PLASMA("plasma", 11, ToaAbilityHolder.NONE), 
	MAGNETISM("magnetism", 12, ToaAbilityHolder.NONE), 
	THE_GREEN("the_green", 13, ToaAbilityHolder.NONE), 
	LIGHTNING("lightning", 14, ToaAbilityHolder.NONE), 
	IRON("iron", 15, ToaAbilityHolder.NONE), 
	PSIONICS("psionics", 16, ToaAbilityHolder.NONE);
	//@formatter:on

	private String name;
	private int id;
	private ToaAbilityHolder abilityHolder;

	Elements(String nameIn, int idIn, ToaAbilityHolder abilityHolderIn) {
		this.name = nameIn;
		this.id = idIn;
		this.abilityHolder = abilityHolderIn;
	}

//	@Override
//	public String getName() {
//		return name;
//	}

	public int getID() {
		return id;
	}

	public static Elements getElementFromID(int idIn) {
		for (Elements element : Elements.values()) {
			if (element.getID() == idIn)
				return element;
		}
		return Elements.NONE;
	}

	public ToaAbilityHolder getAbilityHolder() {
		return abilityHolder;
	}

	@Override
	public String func_176610_l() {
		return name;
	}

}
