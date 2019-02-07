package com.QA.pokemonapp.constantsandenums;

/**
 * The Enum EDamageClass.
 * Holds information about different damage types.
 * Provides index constants for locating different stat types from the stat lists.
 */
public enum EDamageClass {
	
	/** The physical damage type. */
	PHYSICAL,
	
	/** The special the damage type. */
	SPECIAL,
	
	/** The status. */
	STATUS;
	
	private int attackStat;
	
	private int defenceStat;
	
	static {
		PHYSICAL.attackStat = 4;
		PHYSICAL.defenceStat = 3;
		SPECIAL.attackStat = 2;
		SPECIAL.defenceStat = 1;
	}

	/**
	 * Gets the attack stat index within the stat lists.
	 *
	 * @return the attack stat index
	 */
	public int getAttackStat() {
		return attackStat;
	}

	/**
	 * Gets the defence stat index within the stat lists.
	 *
	 * @return the defence stat index
	 */
	public int getDefenceStat() {
		return defenceStat;
	}	
	
}
