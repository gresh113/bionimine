package com.github.gresh113.bionimine.objects.toagear.elementalabilities;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.AOEAttackHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.AirAOEHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.EarthAOEHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.FireAOEHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.IceAOEHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.StoneAOEHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.WaterAOEHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.AirProjectileHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.EarthProjectileHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.ElementalProjectileHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.FireProjectileHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.IceProjectileHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.StoneProjectileHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.WaterProjectileHandler;

import net.minecraft.util.IStringSerializable;

public enum ToaAbilityHolder implements IStringSerializable {
	NONE("none", new FireProjectileHandler(), new FireAOEHandler()), 
	FIRE("fire", new FireProjectileHandler(), new FireAOEHandler()), 
	ICE("ice", new IceProjectileHandler(), new IceAOEHandler()),
	AIR("air", new AirProjectileHandler(), new AirAOEHandler()),
	WATER("ice", new WaterProjectileHandler(), new WaterAOEHandler()),
	EARTH("earth", new EarthProjectileHandler(), new EarthAOEHandler()),
	STONE("stone", new StoneProjectileHandler(), new StoneAOEHandler());

	private ElementalProjectileHandler projectileHandler;

	
	//private TraversalHandler traversalHandler;
	//private ChargedMeleeHandler meleeHandler
	private AOEAttackHandler aoeHandler;
	
	private String name;
	private ToaAbilityHandler[] abilityTypeList = new ToaAbilityHandler[4];
		
	
	private ToaAbilityHolder(String nameIn, ElementalProjectileHandler projectileHandlerIn, AOEAttackHandler aoeHandlerIn) {
		projectileHandler = projectileHandlerIn;
		abilityTypeList[0] = projectileHandler;
		//traversalHandler = traversalHandlerIn;
		//abilityTypeList[1] = traversalHandler;
		//meleeHandler = meleeHandlerIn;
		//abilityTypeList[2] = meleeHandler;
		aoeHandler = aoeHandlerIn;
		abilityTypeList[3] = aoeHandler;
		name = nameIn;
	}

	public ElementalProjectileHandler getProjectileHandler() {
		return projectileHandler;
	}

	public ToaAbilityHandler[] getAbilityArray() {
		return abilityTypeList;
	}

	@Override
	public String getString() {
		return name;
	}

}
