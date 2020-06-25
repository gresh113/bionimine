package com.github.gresh113.bionimine.entities.matoran;

import net.minecraftforge.registries.ForgeRegistryEntry;

public class MatoranElement extends ForgeRegistryEntry<MatoranElement>{
	
	private String name;
	
	public MatoranElement(String nameIn) {
		this.name = nameIn;
		
	}
	
	public String getName() {
		return name;
	}
}
