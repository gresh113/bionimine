package com.github.gresh113.bionimine.kanoka;

import com.github.gresh113.bionimine.kanohi.KanohiPalette;

import net.minecraft.nbt.CompoundNBT;

public enum KanokaMetru {
	TA_METRU(1, "ta-metru"),
	GA_METRU(2, "ga-metru"),
	PO_METRU(3, "po-metru"),
	KO_METRU(4, "ko-metru"),
	LE_METRU(5, "le-metru"),
	ONU_METRU(6, "onu-metru");
	
	private int metruNumber;
	private String name;
	
	KanokaMetru(int numberIn, String nameIn) {
		this.metruNumber = numberIn;
		this.name = nameIn;
	}
	
	public static KanokaMetru fromNBT(CompoundNBT compoundNBT, String tagname)
    {
      int num = 1;  // Default
      if (compoundNBT != null && compoundNBT.contains(tagname)) {
       num = compoundNBT.getInt(tagname);
      }
      KanokaMetru metru = getMetruFromNumber(num);
      return metru;
    }   
    
    public static KanokaMetru getMetruFromNumber(int num) {
    	for (KanokaMetru metru : KanokaMetru.values()) {
	        if (metru.metruNumber == num) {return metru;}
	      }
	      return KanokaMetru.TA_METRU;
	}

	public void putIntoNBT(CompoundNBT compoundNBT, String tagname)
    {
      compoundNBT.putInt(tagname, metruNumber);
    }
	
}
