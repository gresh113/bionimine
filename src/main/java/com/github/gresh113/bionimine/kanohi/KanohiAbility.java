package com.github.gresh113.bionimine.kanohi;


// Huge props to BS01 for all this info
public enum KanohiAbility {
	NONE("Powerless","",true),
	HAU("Hau","Shielding",true),
	MIRU("Miru","Levitaion",true),
	AKAKU("Akaku", "X-Ray Vision",true),
	KAUKAU("Kaukau", "Water Breathing",true),
	PAKARI("Pakari", "Strength",true),
	KAKAMA("Kakama","Speed",true),
	
	HUNA("Huna","Concealment",true),
	RAU("Rau","Translation",true),
	MAHIKI("Mahiki","Illusion",true),
	KOMAU("Komau","Mind Control",true),
	RURU("Ruru","Night Vision",true),
	MATATU("Matatu","Telekenisis",true),
	
	CALIX("Calix","Fate",false),
	ELDA("Elda","Detection",false),
	SULETU("Suletu","Sanok",false),
	SANOK("Sanok","Accuracy",false),
	KADIN("Kadin","Flight",false),
	IDEN("Iden","Spirit",false),
	
	ARTHRON("Arthron","Sonar",false),
	FAXON("Faxon","Kindred",false),
	ZATTH("Zatth","Summoning",false),
	GARAI("Garai","Garai",false),
	VOLITAK("Volitak","Stealth",false),
	TRYNA("Tryna","Reanimation", false),
	
	JUTLIN("Jutlin","Corruption", true),
	AVSA("Avsa","Hunger", false),
	FELNAS("Felnas","Disruption", false),
	MOHTREK("Mohtrek","Duplication", false),
	SHELEK("Shelek","Silence", false),
	CRAST("Crast","Repulsion", false),
	
	PEHKUI("Pehkui","Diminishment", true),
	KUALSI("Kualsi","Quick Travel", true),
	KIRIL("Kiril","Regeneration", true),
	
	
	AVOHKII("Avohkii","Light", false),
	KRAAHKAN("Kraahkan","Shadow", false),
	LIGHT_AND_SHADOW("Avohkan","Light and Shadow", false),
	
	RUA("Rua","Wisdom", false),
	AKI("Aki","Valor", false),
	
	RODE("Rode","Truth", false),
	OLMAK("Olmak","Olmak", false),
	
	OLISI("Olisi","Alternate Futures", false),
	
//	CLAIRVOYANCE("","Clairvoyance", false),
//	EMULATION("","Emulation", false),
//	GROWTH("","Growth", true),
//	RAHI_CONTROL("","Rahi Control", false),
//	PSYCHOMETRY("","Psychometry", false),
//	INTANGIBILITY("","Intangibility", false),
//	POSSIBILITIES("","Possibilities", false),
//	ELEMENTAL_ENERGY("","Elemental Energy", false),
//	MUTATION("","Mutation", false),
//	SCAVENGING("","Scavenging", false),
//	CHARISMA("","Charisma", false),
	
	
	VAHI("Vahi","Time", true),
	IGNIKA("Ignika","Life", false),
	CREATION("Creation","Creation", false);
	
	private final String name;
	private final String descriptor;
	private final Boolean craftable;
	
	private KanohiAbility(String nameIn, String descriptorIn, Boolean craftabilityIn) {
	      this.name = nameIn;
	      this.descriptor = descriptorIn;
	      this.craftable = craftabilityIn;
	   }
	
	public String getName() {
		return this.name;
	}
	
	public String getDescriptor() {
		return this.descriptor;
	}
	
	@Override
	public String toString() {
		return this.getName();
	}
	

}
