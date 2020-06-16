package com.github.gresh113.bionimine.toa_gear.elemental_abilities;
import com.github.gresh113.bionimine.toa_gear.elemental_abilities.projectile_handlers.AirProjectileHandler;
import com.github.gresh113.bionimine.toa_gear.elemental_abilities.projectile_handlers.ElementalProjectileHandler;
import com.github.gresh113.bionimine.toa_gear.elemental_abilities.projectile_handlers.FireProjectileHandler;
import com.github.gresh113.bionimine.toa_gear.elemental_abilities.projectile_handlers.IceProjectileHandler;

import net.minecraft.util.IStringSerializable;

public enum ToaAbilityHolder implements IStringSerializable {
	NONE("none", new FireProjectileHandler()), 
	FIRE("fire", new FireProjectileHandler()), 
	ICE("ice", new IceProjectileHandler()),
	AIR("air", new AirProjectileHandler());

	private ElementalProjectileHandler projectileHandler;

	//private ForwardAttackHandler forwardHandler;
	//private TraversalHandler traversalHandler;
	//private ChargedMeleeHandler meleeHandler
	//private AOEAtackHandler aoeHandler;
	
	private String name;
	private ToaAbilityHandler[] abilityTypeList = new ToaAbilityHandler[4];
		
	
	private ToaAbilityHolder(String nameIn, ElementalProjectileHandler projectileHandlerIn) {
		projectileHandler = projectileHandlerIn;
		abilityTypeList[0] = projectileHandler;
		//forwardHandler = forwardHanlderIn;
		//abilityTypeList[1] = forwardHandler;
		//traversalHandler = traversalHandlerIn;
		//abilityTypeList[2] = traversalHandler;
		//meleeHandler = meleeHandlerIn;
		//abilityTypeList[3] = meleeHandler;
		//aoeHandler = aoeHandlerIn;
		//abilityTypeList[4] = aoeHandler;
		name = nameIn;
	}

	@Override
	public String getName() {
		return name;
	}

	public ElementalProjectileHandler getProjectileHandler() {
		return projectileHandler;
	}

	public ToaAbilityHandler[] getAbilityArray() {
		return abilityTypeList;
	}

}
