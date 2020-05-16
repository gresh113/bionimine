package com.github.gresh113.bionimine.kanohi;

import net.minecraft.util.IStringSerializable;

public enum KanohiShape implements IStringSerializable {
	
	HAU_GREAT("great_hau",0.00F),
	MIRU_GREAT("great_miru",0.02F),
	AKAKU_GREAT("great_akaku",0.03F),
	KAUKAU_GREAT("great_kaukau",0.04F),
	PAKARI_GREAT("great_pakari",0.05F),
	KAKAMA_GREAT("great_kakama",0.06F),
	
	HAU_NUVA("nuva_hau",0.07F),
	MIRU_NUVA("nuva_miru",0.08F),
	AKAKU_NUVA("nuva_akaku",0.09F),
	KAUKAU_NUVA("nuva_kaukau",0.10F),
	PAKARI_NUVA("nuva_pakari",0.11F),
	KAKAMA_NUVA("nuva_kakama",0.12F),
	
	HAU_NOBLE("",0.13F),
	MIRU_NOBLE("",0.14F),
	AKAKU_NOBLE("",0.15F),
	KAKAMA_NOBLE("",0.16F),
	
	HUNA_GREAT("",0.17F),
	RAU_GREAT("",0.18F),
	MAHIKI_GREAT("",0.19F),
	KOMAU_GREAT("",0.20F),
	RURU_GREAT("",0.21F),
	MATATU_GREAT("",0.22F),
	
	HUNA_NOBLE("",0.23F),
	RAU_NOBLE("",0.24F),
	MAHIKI_NOBLE("",0.25F),
	KOMAU_NOBLE("",0.26F),
	RURU_NOBLE("",0.27F),
	MATATU_NOBLE("",0.28F),
	
	KIRIL_NOBLE("",0.29F),
	
	CALIX_ORGANIC("",0.30F),
	ELDA_ORGANIC("",0.31F),
	SULETU_ORGANIC("",0.32F),
	SANOK_ORGANIC("",0.33F),
	KADIN_ORGANIC("",0.34F),
	IDEN_ORGANIC("",0.35F),
	
	ARTHRON("",0.36F),
	FAXON("",0.37F),
	ZATTH("",0.38F),
	GARAI("",0.39F),
	VOLITAK("",0.40F),
	TRYNA("",0.41F),
	
	JUTLIN_MAKUTA("",0.41F),
	AVSA_MAKUTA("",0.43F),
	FELNAS_MAKUTA("",0.44F),
	MOHTREK_MAKUTA("",0.45F),
	SHELEK_MAKUTA("",0.46F),
	CRAST_MAKUTA("",0.47F),
	
	JUTLIN_NOBLE("",0.48F),
	AVSA_NOBLE("",0.49F),
	SHELEK_NOBLE("",0.50F),

	KUALSI("",0.51F),
	EMULATION("",0.52F),
	
	AVOHKII("",0.53F),
	KRAAHKAN("",0.54F),
	LIGHT_AND_SHADOW("",0.55F),
	
	RODE("",0.56F),
	OLMAK("",0.57F),
	OLISI("",0.58F),
	
	POSSIBILITIES("",0.59F),
	ELEMENTAL_ENERGY("",0.60F),
	MUTATION("",0.61F),
	SCAVENNING("",0.62F),
	
	VAHI("",0.63F),
	IGNIKA("",0.64F),
	CREATION("",0.65F);

	
	private final String name;
	private Float predicateValue;
	
	KanohiShape(String nameIn, float predicateValueIn) {
		this.name = nameIn;
		this.predicateValue = predicateValueIn;
	}
	
	@Override
	public String getName() {
		return name;
	}
	public Float getPredicate() {
		return predicateValue;
	}
	
}
