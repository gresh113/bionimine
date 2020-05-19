package com.github.gresh113.bionimine.kanoka;

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
	
}
