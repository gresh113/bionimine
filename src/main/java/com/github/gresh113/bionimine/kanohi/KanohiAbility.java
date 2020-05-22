package com.github.gresh113.bionimine.kanohi;


// Huge props to BS01 for all this info
public enum KanohiAbility {
	NONE("Powerless","", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	HAU("Hau","Shielding", true, KanohiShape.HAU_GREAT, KanohiPalette.RED),
	MIRU("Miru","Levitaion", true, KanohiShape.MIRU_GREAT, KanohiPalette.GREEN),
	AKAKU("Akaku", "X-Ray Vision", true, KanohiShape.AKAKU_GREAT, KanohiPalette.WHITE),
	KAUKAU("Kaukau", "Water Breathing", true, KanohiShape.KAUKAU_GREAT, KanohiPalette.BLUE),
	PAKARI("Pakari", "Strength", true, KanohiShape.PAKARI_GREAT, KanohiPalette.BLACK),
	KAKAMA("Kakama","Speed", true, KanohiShape.KAKAMA_GREAT, KanohiPalette.BROWN),
	
	HUNA("Huna","Concealment", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	RAU("Rau","Translation", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	MAHIKI("Mahiki","Illusion", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	KOMAU("Komau","Mind Control", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	RURU("Ruru","Night Vision", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	MATATU("Matatu","Telekenisis", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	CALIX("Calix","Fate", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	ELDA("Elda","Detection", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	SULETU("Suletu","Sanok", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	SANOK("Sanok","Accuracy", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	KADIN("Kadin","Flight", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	IDEN("Iden","Spirit", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	ARTHRON("Arthron","Sonar", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	FAXON("Faxon","Kindred", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	ZATTH("Zatth","Summoning", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	GARAI("Garai","Garai", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	VOLITAK("Volitak","Stealth", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	TRYNA("Tryna","Reanimation", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	JUTLIN("Jutlin","Corruption", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	AVSA("Avsa","Hunger", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	FELNAS("Felnas","Disruption", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	MOHTREK("Mohtrek","Duplication", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	SHELEK("Shelek","Silence", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	CRAST("Crast","Repulsion", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	PEHKUI("Pehkui","Diminishment", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	KUALSI("Kualsi","Quick Travel", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	KIRIL("Kiril","Regeneration", true, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	
	AVOHKII("Avohkii","Light", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	KRAAHKAN("Kraahkan","Shadow", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	LIGHT_AND_SHADOW("Avohkan","Light and Shadow", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	RUA("Rua","Wisdom", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	AKI("Aki","Valor", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	RODE("Rode","Truth", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	OLMAK("Olmak","Olmak", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
	OLISI("Olisi","Alternate Futures", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	
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
	
	
	VAHI("Vahi","Time", true, KanohiShape.VAHI, KanohiPalette.GOLD),
	IGNIKA("Ignika","Life", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY),
	CREATION("Creation","Creation", false, KanohiShape.SHAPELESS, KanohiPalette.GRAY);
	
	private final String name;
	private final String descriptor;
	private final KanohiShape defaultShape;
	private final KanohiPalette defaultColor;	
	private final Boolean craftable;
	
	private KanohiAbility(String nameIn, String descriptorIn, Boolean craftabilityIn, KanohiShape defaultShapeIn, KanohiPalette defaultColorIn) {
	    this.name = nameIn;
	    this.descriptor = descriptorIn;
	    this.craftable = craftabilityIn;
	    this.defaultShape = defaultShapeIn;
	    this.defaultColor = defaultColorIn;
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
	
	public KanohiShape getDefaultShape() {
		return this.defaultShape;
		}
	
	public KanohiPalette getDefaultPalette() {
		return this.defaultColor;
		}
	

}
