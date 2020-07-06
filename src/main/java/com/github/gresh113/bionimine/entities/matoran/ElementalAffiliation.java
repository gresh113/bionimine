package com.github.gresh113.bionimine.entities.matoran;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class ElementalAffiliation extends ForgeRegistryEntry<ElementalAffiliation>{
	
	private String name;
	
	public ElementalAffiliation(String nameIn) {
		this.name = nameIn;
		
	}
	
	public String getName() {
		return name;
	}
}
