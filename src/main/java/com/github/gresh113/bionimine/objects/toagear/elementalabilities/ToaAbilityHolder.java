package com.github.gresh113.bionimine.objects.toagear.elementalabilities;

import com.github.gresh113.bionimine.objects.toagear.elementalabilities.aoehandlers.*;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers.AirMeleeHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers.FireMeleeHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.chargedmeleehandlers.WaterMeleeHandler;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.projectilehandlers.*;
import com.github.gresh113.bionimine.objects.toagear.elementalabilities.traversalhandlers.*;
import net.minecraft.util.IStringSerializable;

public enum ToaAbilityHolder implements IStringSerializable {

    NONE("none", new FireProjectileHandler(), new FireAOEHandler(), new EarthTraversalHandler(), new FireMeleeHandler()),
    FIRE("fire", new FireProjectileHandler(), new FireAOEHandler(), new FireTraversalHandler(), new FireMeleeHandler()),
    ICE("ice", new IceProjectileHandler(), new IceAOEHandler(), new IceTraversalHandler(), new FireMeleeHandler()),
    AIR("air", new AirProjectileHandler(), new AirAOEHandler(), new AirTraversalHandler(), new AirMeleeHandler()),
    WATER("water", new WaterProjectileHandler(), new WaterAOEHandler(), new WaterTraversalHandler(), new WaterMeleeHandler()),
    EARTH("earth", new EarthProjectileHandler(), new EarthAOEHandler(), new EarthTraversalHandler(), new FireMeleeHandler()),
    STONE("stone", new StoneProjectileHandler(), new StoneAOEHandler(), new StoneTraversalHandler(), new FireMeleeHandler());


    private ElementalProjectileHandler projectileHandler;

    private TraversalHandler traversalHandler;
    private ChargedMeleeHandler meleeHandler;
    private AOEAttackHandler aoeHandler;

    private String name;
    private ToaAbilityHandler[] abilityTypeList = new ToaAbilityHandler[4];


    private ToaAbilityHolder(String nameIn, ElementalProjectileHandler projectileHandlerIn, AOEAttackHandler aoeHandlerIn, TraversalHandler traversalHandlerIn, ChargedMeleeHandler meleeHandlerIn) {
        projectileHandler = projectileHandlerIn;
        abilityTypeList[0] = projectileHandler;
        traversalHandler = traversalHandlerIn;
        abilityTypeList[1] = traversalHandler;
        meleeHandler = meleeHandlerIn;
        abilityTypeList[2] = meleeHandler;
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
