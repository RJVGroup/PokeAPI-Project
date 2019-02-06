package com.QA.pokemonapp.constantsandenums;

/**
 * The Enum EPotion.
 * Holds and returns the PokeAPI address for the different potions employed in this program.
 */
public enum EPotion {
	
	/** The potion. */
	POTION,
	
	/** The super potion. */
	SUPER_POTION,
	
	/** The hyper potion. */
	HYPER_POTION,
	
	/** The max potion. */
	MAX_POTION;
	
	private String APIAddress;
	
	static {
		POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + POTION.name().toLowerCase() + "/");
		SUPER_POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + SUPER_POTION.name().toLowerCase().replace("_", "-") + "/");
		HYPER_POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + HYPER_POTION.name().toLowerCase().replace("_", "-") + "/");
		MAX_POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + MAX_POTION.name().toLowerCase().replace("_", "-") + "/");
	}

	/**
	 * Gets the API address for each specifc potion type.
	 *
	 * @return the API address
	 */
	public String getAPIAddress() {
		return APIAddress;
	}

	/**
	 * Sets the API addressfor each specifc potion type.
	 *
	 * @param aPIAddress the new API address
	 */
	public void setAPIAddress(String aPIAddress) {
		APIAddress = aPIAddress;
	}
	
	
}
