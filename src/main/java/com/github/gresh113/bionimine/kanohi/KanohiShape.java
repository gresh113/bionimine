package com.github.gresh113.bionimine.kanohi;


import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IStringSerializable;

public enum KanohiShape implements IStringSerializable {
	
	HAU_GREAT("great_hau",0.00f),
	SHAPELESS("shapeless",0.01f), // Hopefully not accessible in ordinary gameplay
	MIRU_GREAT("great_miru",0.02f),
	AKAKU_GREAT("great_akaku",0.03f),
	KAUKAU_GREAT("great_kaukau",0.04f),
	PAKARI_GREAT("great_pakari",0.05f),
	KAKAMA_GREAT("great_kakama",0.06f),
	
	HAU_NUVA("nuva_hau",0.07f),
	MIRU_NUVA("nuva_miru",0.08f),
	AKAKU_NUVA("nuva_akaku",0.09f),
	KAUKAU_NUVA("nuva_kaukau",0.10f),
	PAKARI_NUVA("nuva_pakari",0.11f),
	KAKAMA_NUVA("nuva_kakama",0.12f),
	
	HAU_NOBLE("noble_hau",0.13f),
	MIRU_NOBLE("noble_miru",0.14f),
	AKAKU_NOBLE("noble_akaku",0.15f),
	KAKAMA_NOBLE("noble_kakama",0.16f),
	
	HUNA_GREAT("great_huna",0.17f),
	RAU_GREAT("great_rau",0.18f),
	MAHIKI_GREAT("great_mahiki",0.19f),
	KOMAU_GREAT("great_komau",0.20f),
	RURU_GREAT("great_ruru",0.21f),
	MATATU_GREAT("great_matatu",0.22f),
	
	HUNA_NOBLE("noble_huna",0.23f),
	RAU_NOBLE("noble_rau",0.24f),
	MAHIKI_NOBLE("noble_mahiki",0.25f),
	KOMAU_NOBLE("noble_komau",0.26f),
	RURU_NOBLE("noble_ruru",0.27f),
	MATATU_NOBLE("noble_matatu",0.28f),
	
	KIRIL_NOBLE("noble_kiril",0.29f),
	
	CALIX_ORGANIC("organic_calix",0.30f),
	ELDA_ORGANIC("organic_elda",0.31f),
	SULETU_ORGANIC("organic_suletu",0.32f),
	SANOK_ORGANIC("organic_sanok",0.33f),
	KADIN_ORGANIC("organic_kadin",0.34f),
	IDEN_ORGANIC("organic_iden",0.35f),
	
	ARTHRON("arthron",0.36f),
	FAXON("faxon",0.37f),
	ZATTH("zatth",0.38f),
	GARAI("garai",0.39f),
	VOLITAK("volitak",0.40f),
	TRYNA("tryna",0.41f),
	
	JUTLIN_MAKUTA("makuta_jutlin",0.41f),
	AVSA_MAKUTA("makuta_avsa",0.43f),
	FELNAS_MAKUTA("makuta_felnas",0.44f),
	MOHTREK_MAKUTA("makuta_mohtrek",0.45f),
	SHELEK_MAKUTA("makuta_shelek",0.46f),
	CRAST_MAKUTA("makuta_crast",0.47f),
	
	JUTLIN_NOBLE("noble_jutlin",0.48f),
	AVSA_NOBLE("noble_avsa",0.49f),
	SHELEK_NOBLE("",0.50f),

	KUALSI("kualsi",0.51f),
	EMULATION("emulation",0.52f),
	
	AVOHKII("avohkii",0.53f),
	KRAAHKAN("kraahkan",0.54f),
	LIGHT_AND_SHADOW("light_and_shadow",0.55f),
	
	RODE("rode",0.56f),
	OLMAK("olmak",0.57f),
	OLISI("olisi",0.58f),
	
	POSSIBILITIES("possibilities",0.59f),
	ELEMENTAL_ENERGY("elemental_energy",0.60f),
	MUTATION("mutation",0.61f),
	SCAVENGING("sacavenging",0.62f),
	
	VAHI("vahi",0.63f),
	IGNIKA("ignika",0.64f),
	CREATION("creation",0.65f),
	
	MIRU_SKY("sky_miru",0.67f),
	AKAKU_SKY("sky_akaku",0.68f),
	KAKAMA_SKY("sky_kakama",0.69f),
	HAU_SWAMP("swamp_hau",0.70f),
	KAUKAU_SWAMP("swamp_kaukau",0.71f),
	PAKARI_SWAMP("swamp_pakari",0.72f);

	
	private final String name;
	private final float predicateValue;
	
	KanohiShape(String nameIn, float predicateValueIn) {
		this.name = nameIn;
		this.predicateValue = predicateValueIn;
	}
	
	@Override
	public String getName() {
		return name;
	}
	public float getPredicate() {
		return predicateValue;
	}
	public float getPropertyOverrideValue() {return predicateValue;}
	
	public static KanohiShape getShapeFromName(String nameIn) {
	      for (KanohiShape shape : KanohiShape.values()) {
	        if (shape.name.equals(nameIn)) {return shape;}
	      }
	      return KanohiShape.HAU_GREAT;
	    }
	
	public static KanohiShape fromNBT(CompoundNBT compoundNBT, String tagname)
    {
      String shapeName = "great_hau";  // default in case of error
      if (compoundNBT != null && compoundNBT.contains(tagname)) {
        shapeName = compoundNBT.getString(tagname);
      }
      KanohiShape shape = getShapeFromName(shapeName);
      return shape;
    }

    /**
     * Write this enum to NBT
     * @param compoundNBT
     * @param tagname
     */
    public void putIntoNBT(CompoundNBT compoundNBT, String tagname)
    {
      compoundNBT.putString(tagname, name);
    }
}
