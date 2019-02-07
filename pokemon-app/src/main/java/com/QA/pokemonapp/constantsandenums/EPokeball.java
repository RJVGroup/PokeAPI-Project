package com.QA.pokemonapp.constantsandenums;

/**
 * The Enum EPokeball.
 * Holds and returns the PokeAPI address for the different pokeballs employed within the program.
 * Used by the API consumption layers.
 */
public enum EPokeball {
	
	/** The poke ball. */
	POKE_BALL,
	
	/** The great ball. */
	GREAT_BALL,
	
	/** The ultra ball. */
	ULTRA_BALL,
	
	/** The master ball. */
	MASTER_BALL;
	
	private String APIAddress;
	
	static {
		POKE_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + POKE_BALL.name().toLowerCase().replace("_", "-") + "/");
		GREAT_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + GREAT_BALL.name().toLowerCase().replace("_", "-") + "/");
		ULTRA_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + ULTRA_BALL.name().toLowerCase().replace("_", "-") + "/");
		MASTER_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + MASTER_BALL.name().toLowerCase().replace("_", "-") + "/");
	}

	/**
	 * Gets the API address for the specified pokeball.
	 *
	 * @return the API address
	 */
	public String getAPIAddress() {
		return APIAddress;
	}

	/**
	 * Sets the API address for the specific pokeball.
	 *
	 * @param aPIAddress the new API address
	 */
	public void setAPIAddress(String aPIAddress) {
		APIAddress = aPIAddress;
	}
	
}
