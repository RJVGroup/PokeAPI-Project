package com.QA.pokemonapp.constantsandenums;

public enum EDamageClass {
	PHYSICAL,
	SPECIAL,
	STATUS;
	
	private int attackStat;
	private int defenceStat;
	
	static {
		PHYSICAL.attackStat = 4;
		PHYSICAL.defenceStat = 3;
		SPECIAL.attackStat = 2;
		SPECIAL.defenceStat = 1;
	}

	public int getAttackStat() {
		return attackStat;
	}

	public int getDefenceStat() {
		return defenceStat;
	}	
	
}
