package com.github.gresh113.bionimine.kanoka;

public enum KanokaPower {
	BAMBOO_DISK(0,"bamboo_disk"),
	RECONSTITUTE(1,"reconstitute_at_random"),
	FREEZE(2,"freeze"),
	WEAKEN(3,"weaken"),
	REMOVE_POISON(4,"remove_poison"),
	ENGLARGE(5,"enlarge"),
	SHRINK(6,"shrink"),
	REGENERATE(7,"regenerate"),
	TELEPORT(8,"teleport");
	
	private int powerNumber;
	private String name;

	KanokaPower(int numberIn, String stringIn) {
		this.powerNumber = numberIn;
		this.name = stringIn;
	}
	
	public String getName() {return this.name;}
	public int getPower() {return this.powerNumber;}

}
