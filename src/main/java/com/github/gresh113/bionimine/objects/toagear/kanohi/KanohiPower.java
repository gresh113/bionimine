package com.github.gresh113.bionimine.objects.toagear.kanohi;

import javax.annotation.Nullable;

import com.github.gresh113.bionimine.objects.toagear.ArmorPalette;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.KanohiPowerHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great.GreatAkakuPowerHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great.GreatHauPowerHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great.GreatHunaPowerHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great.GreatKakamaPowerHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great.GreatKaukauPowerHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great.GreatMiruPowerHandler;
import com.github.gresh113.bionimine.objects.toagear.kanohi.powerhandlers.great.GreatPakariPowerHandler;

// Huge props to BS01 for all this info
public enum KanohiPower {

	// Rename "MasksOfPower"????? - Colapse in KanohiPowerLevel

	//@formatter:off
	NONE("Powerless", "powerless_kanohi", "", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	GREAT_HAU("Great Hau", "great_hau", "Shielding", true, false,KanohiShape.HAU_GREAT, ArmorPalette.RED, new GreatHauPowerHandler()),
	GREAT_MIRU("Great Miru", "great_miru", "Levitaion", true, false, KanohiShape.MIRU_GREAT, ArmorPalette.GREEN, new GreatMiruPowerHandler()),
	GREAT_AKAKU("Great Akaku", "great_akaku", "X-Ray Vision", true, false, KanohiShape.AKAKU_GREAT, ArmorPalette.WHITE, new GreatAkakuPowerHandler()),
	GREAT_KAUKAU("Great Kaukau", "great_kaukau", "Water Breathing", true, false, KanohiShape.KAUKAU_GREAT, ArmorPalette.BLUE, new GreatKaukauPowerHandler()),
	GREAT_PAKARI("Great Pakari", "great_pakari", "Strength", true, false, KanohiShape.PAKARI_GREAT, ArmorPalette.BLACK, new GreatPakariPowerHandler()),
	GREAT_KAKAMA("Great Kakama", "great_kakama", "Speed", true, false, KanohiShape.KAKAMA_GREAT, ArmorPalette.BROWN, new GreatKakamaPowerHandler()),

	NOBLE_HAU("Noble Hau", "noble_hau", "Shielding", true, true,KanohiShape.SHAPELESS, ArmorPalette.RED, null),
	NOBLE_MIRU("Noble Miru", "noble_miru", "Levitaion", true,true, KanohiShape.SHAPELESS, ArmorPalette.GREEN, null),
	NOBLE_AKAKU("Noble Akaku", "noble_akaku", "X-Ray Vision", true,true, KanohiShape.SHAPELESS, ArmorPalette.WHITE, null),
	NOBLE_KAKAMA("Noble Kakama", "noble_kakama", "Speed", true, true,KanohiShape.SHAPELESS, ArmorPalette.BROWN, null),

	GREAT_HUNA("Great Huna", "great_huna", "Concealment", true, false, KanohiShape.SHAPELESS, ArmorPalette.ORANGE, new GreatHunaPowerHandler()),
	GREAT_RAU("Great Rau", "great_rau", "Translation", true, false, KanohiShape.SHAPELESS, ArmorPalette.LIGHT_BLUE, null),
	GREAT_MAHIKI("Great Mahiki", "great_mahiki", "Illusion", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	GREAT_KOMAU("Great Komau", "great_komau", "Mind Control", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	GREAT_RURU("Great Ruru", "great_ruru", "Night Vision", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	GREAT_MATATU("Great Matatu", "great_matatu", "Telekenisis", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	NOBLE_HUNA("Noble Huna", "noble_huna", "Concealment", true,true, KanohiShape.HUNA_NOBLE, ArmorPalette.ORANGE, new GreatHunaPowerHandler()),
	NOBLE_RAU("Noble Rau", "noble_rau", "Translation", true,true, KanohiShape.RAU_NOBLE, ArmorPalette.LIGHT_BLUE, null),
	NOBLE_MAHIKI("Noble Mahiki", "noble_mahiki", "Illusion", true, true,KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	NOBLE_KOMAU("Noble Komau", "noble_komau", "Mind Control", true,true, KanohiShape.KOMAU_NOBLE, ArmorPalette.TAN, null),
	NOBLE_RURU("Noble Ruru", "noble_ruru", "Night Vision", true,true, KanohiShape.RURU_NOBLE, ArmorPalette.GRAY, null),
	NOBLE_MATATU("Noble Matatu", "noble_matatu", "Telekenisis", true,true, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	CALIX("Great Calix", "calix", "Fate", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	ELDA("Great Elda", "elda", "Detection", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	SULETU("Great Suletu", "suletu", "Sanok", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	SANOK("Great Sanok", "sanok", "Accuracy", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	KADIN("Great Kadin", "kadin", "Flight", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	IDEN("Great Iden", "iden", "Spirit", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	ARTHRON("Great Arthron", "arthron", "Sonar", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	FAXON("Great Faxon", "faxon", "Kindred", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	ZATTH("Great Zatth", "zatth", "Summoning", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	GARAI("Great Garai", "garai", "Garai", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	VOLITAK("Great Volitak", "volitak", "Stealth", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	TRYNA("Great Tryna", "tryna", "Reanimation", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	JUTLIN("Great Jutlin", "jutlin", "Corruption", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	AVSA("Great Avsa", "avsa", "Hunger", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	FELNAS("Great Felnas", "felnas", "Disruption", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	MOHTREK("Great Mohtrek", "mohtrek", "Duplication", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	SHELEK("Great Shelek", "shelek", "Silence", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	CRAST("Great Crast", "crast", "Repulsion", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	PEHKUI("Great Pehkui", "pehkui", "Diminishment", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	KUALSI("Great Kualsi", "kualsi", "Quick Travel", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	GREAT_KIRIL("Great Kiril", "great_kiril", "Regeneration", true, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	NOBLE_KIRIL("Noble Kiril", "noble_kiril", "Regeneration", true, true, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	AVOHKII("Great Avohkii", "avohkii", "Light", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	KRAAHKAN("Great Kraahkan", "kraahkan", "Shadow", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	LIGHT_AND_SHADOW("Great Avohkan", "mask_of_light_and_shadow", "Light and Shadow", false, false, KanohiShape.SHAPELESS,
			ArmorPalette.GRAY, null),

	RUA("Great Rua", "rua", "Wisdom", false, false, KanohiShape.MIRU_GREAT, ArmorPalette.GRAY, null),
	AKI("Great Aki", "aki", "Valor", false, false, KanohiShape.HAU_GREAT, ArmorPalette.GOLD, null),

	RODE("Great Rode", "rode", "Truth", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	OLMAK("Great Olmak", "olmak", "Dimensional Gates", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),

	OLISI("Great Olisi", "olisi", "Alternate Futures", false, false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	

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

	VAHI("Legendary Vahi", "vahi", "Time", true,false, KanohiShape.VAHI, ArmorPalette.GOLD, null),
	IGNIKA("Legendary Ignika", "ignika", "Life", false,false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null),
	CREATION("Legendary Creation", "creation", "Creation", false,false, KanohiShape.SHAPELESS, ArmorPalette.GRAY, null);
	//@formatter:on

	private final String name;
	private final String lowcapsName;
	private final String descriptor;
	private final KanohiShape defaultShape;
	private final ArmorPalette defaultColor;
	private final Boolean craftable;
	private final Boolean isNoble;
	private final KanohiPowerHandler powerHandler;

	private KanohiPower(String nameIn, String lowcapsNameIn, String descriptorIn, Boolean isNobleIn, Boolean craftabilityIn, KanohiShape defaultShapeIn, ArmorPalette defaultColorIn, @Nullable KanohiPowerHandler powerHandlerIn) {
		this.name = nameIn;
		this.lowcapsName = lowcapsNameIn;
		this.descriptor = descriptorIn;
		this.isNoble = isNobleIn;
		this.craftable = craftabilityIn;
		this.defaultShape = defaultShapeIn;
		this.defaultColor = defaultColorIn;
		this.powerHandler = powerHandlerIn;
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

	@Nullable
	public KanohiPowerHandler getPowerHandler() {
		return powerHandler;
	}

	public Boolean isNoble() {
		return isNoble;
	}

}
