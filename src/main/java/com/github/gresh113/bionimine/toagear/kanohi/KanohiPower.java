package com.github.gresh113.bionimine.toagear.kanohi;

import com.github.gresh113.bionimine.toagear.ArmorPalette;

// Huge props to BS01 for all this info
public enum KanohiPower {

	// Rename "MasksOfPower"????? - Colapse in KanohiPowerLevel

	NONE("Powerless", "powerless_kanohi", "", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	GREAT_HAU("Great Hau", "great_hau", "Shielding", true, KanohiShape.HAU_GREAT, ArmorPalette.RED),
	GREAT_MIRU("Great Miru", "great_miru", "Levitaion", true, KanohiShape.MIRU_GREAT, ArmorPalette.GREEN),
	GREAT_AKAKU("Great Akaku", "great_akaku", "X-Ray Vision", true, KanohiShape.AKAKU_GREAT, ArmorPalette.WHITE),
	GREAT_KAUKAU("Great Kaukau", "great_kaukau", "Water Breathing", true, KanohiShape.KAUKAU_GREAT, ArmorPalette.BLUE),
	GREAT_PAKARI("Great Pakari", "great_pakari", "Strength", true, KanohiShape.PAKARI_GREAT, ArmorPalette.BLACK),
	GREAT_KAKAMA("Great Kakama", "great_kakama", "Speed", true, KanohiShape.KAKAMA_GREAT, ArmorPalette.BROWN),

	NOBLE_HAU("Noble Hau", "noble_hau", "Shielding", true, KanohiShape.SHAPELESS, ArmorPalette.RED),
	NOBLE_MIRU("Noble Miru", "noble_miru", "Levitaion", true, KanohiShape.SHAPELESS, ArmorPalette.GREEN),
	NOBLE_AKAKU("Noble Akaku", "noble_akaku", "X-Ray Vision", true, KanohiShape.SHAPELESS, ArmorPalette.WHITE),
	NOBLE_KAKAMA("Noble Kakama", "noble_kakama", "Speed", true, KanohiShape.SHAPELESS, ArmorPalette.BROWN),

	GREAT_HUNA("Great Huna", "great_huna", "Concealment", true, KanohiShape.SHAPELESS, ArmorPalette.ORANGE),
	GREAT_RAU("Great Rau", "great_rau", "Translation", true, KanohiShape.SHAPELESS, ArmorPalette.LIGHT_BLUE),
	GREAT_MAHIKI("Great Mahiki", "great_mahiki", "Illusion", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	GREAT_KOMAU("Great Komau", "great_komau", "Mind Control", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	GREAT_RURU("Great Ruru", "great_ruru", "Night Vision", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	GREAT_MATATU("Great Matatu", "great_matatu", "Telekenisis", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	NOBLE_HUNA("Noble Huna", "noble_huna", "Concealment", true, KanohiShape.HUNA_NOBLE, ArmorPalette.ORANGE),
	NOBLE_RAU("Noble Rau", "noble_rau", "Translation", true, KanohiShape.RAU_NOBLE, ArmorPalette.LIGHT_BLUE),
	NOBLE_MAHIKI("Noble Mahiki", "noble_mahiki", "Illusion", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	NOBLE_KOMAU("Noble Komau", "noble_komau", "Mind Control", true, KanohiShape.KOMAU_NOBLE, ArmorPalette.TAN),
	NOBLE_RURU("Noble Ruru", "noble_ruru", "Night Vision", true, KanohiShape.RURU_NOBLE, ArmorPalette.GRAY),
	NOBLE_MATATU("Noble Matatu", "noble_matatu", "Telekenisis", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	CALIX("Great Calix", "calix", "Fate", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	ELDA("Great Elda", "elda", "Detection", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	SULETU("Great Suletu", "suletu", "Sanok", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	SANOK("Great Sanok", "sanok", "Accuracy", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	KADIN("Great Kadin", "kadin", "Flight", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	IDEN("Great Iden", "iden", "Spirit", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	ARTHRON("Great Arthron", "arthron", "Sonar", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	FAXON("Great Faxon", "faxon", "Kindred", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	ZATTH("Great Zatth", "zatth", "Summoning", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	GARAI("Great Garai", "garai", "Garai", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	VOLITAK("Great Volitak", "volitak", "Stealth", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	TRYNA("Great Tryna", "tryna", "Reanimation", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	JUTLIN("Great Jutlin", "jutlin", "Corruption", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	AVSA("Great Avsa", "avsa", "Hunger", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	FELNAS("Great Felnas", "felnas", "Disruption", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	MOHTREK("Great Mohtrek", "mohtrek", "Duplication", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	SHELEK("Great Shelek", "shelek", "Silence", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	CRAST("Great Crast", "crast", "Repulsion", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	PEHKUI("Great Pehkui", "pehkui", "Diminishment", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	KUALSI("Great Kualsi", "kualsi", "Quick Travel", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	GREAT_KIRIL("Great Kiril", "great_kiril", "Regeneration", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	NOBLE_KIRIL("Noble Kiril", "noble_kiril", "Regeneration", true, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	AVOHKII("Great Avohkii", "avohkii", "Light", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	KRAAHKAN("Great Kraahkan", "kraahkan", "Shadow", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	LIGHT_AND_SHADOW("Great Avohkan", "mask_of_light_and_shadow", "Light and Shadow", false, KanohiShape.SHAPELESS,
			ArmorPalette.GRAY),

	RUA("Great Rua", "rua", "Wisdom", false, KanohiShape.MIRU_GREAT, ArmorPalette.GRAY),
	AKI("Great Aki", "aki", "Valor", false, KanohiShape.HAU_GREAT, ArmorPalette.GOLD),

	RODE("Great Rode", "rode", "Truth", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	OLMAK("Great Olmak", "olmak", "Dimensional Gates", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

	OLISI("Great Olisi", "olisi", "Alternate Futures", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),

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

	VAHI("Legendary Vahi", "vahi", "Time", true, KanohiShape.VAHI, ArmorPalette.GOLD),
	IGNIKA("Legendary Ignika", "ignika", "Life", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY),
	CREATION("Legendary Creation", "creation", "Creation", false, KanohiShape.SHAPELESS, ArmorPalette.GRAY);

	private final String name;
	private final String lowcapsName;
	private final String descriptor;
	private final KanohiShape defaultShape;
	private final ArmorPalette defaultColor;
	private final Boolean craftable;

	private KanohiPower(String nameIn, String lowcapsNameIn, String descriptorIn, Boolean craftabilityIn,
			KanohiShape defaultShapeIn, ArmorPalette defaultColorIn) {
		this.name = nameIn;
		this.lowcapsName = lowcapsNameIn;
		this.descriptor = descriptorIn;
		this.craftable = craftabilityIn;
		this.defaultShape = defaultShapeIn;
		this.defaultColor = defaultColorIn;
	}

	public String powerLevel() {
		if (this == VAHI || this == IGNIKA || this == CREATION) {
			return name.substring(0, 9);
		}
		return name.substring(0, 5);

	}

	public String getUppercaseName() {
		return this.name;
	}

	public String getLowercaseName() {
		return this.lowcapsName;
	}

	public String getDescriptor() {
		if (this == NONE) {
			return "Powerless Mask";
		}
		String laterText = descriptor;
		return this.powerLevel() + " Mask of " + laterText;
	}

	@Override
	public String toString() {
		return this.getLowercaseName();
	}

	public KanohiShape getDefaultShape() {
		return this.defaultShape;
	}

	public ArmorPalette getDefaultPalette() {
		return this.defaultColor;
	}
	
	public boolean isCraftable() {
		return craftable;
	}

}
