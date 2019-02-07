package com.QA.pokemonapp.constantsandenums;

/**
 * The Enum ETerrain.
 * Holds and returns the different terrain tpyees employed in this program and their PokeAPI addresses.
 */
public enum ETerrain {
	
	/** The forest terrain type. */
	FOREST,
	
	/** The field terrain type. */
	FIELD,
	
	/** The mountain terrain type. */
	MOUNTAIN,
	
	/** The pond terrain type. */
	POND,
	
	/** The sea terrain type. */
	SEA;
	

	private String APIAdress;
	
	static {
		FOREST.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + FOREST.name().toLowerCase().replace("_", "-") + "/");
		FIELD.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + FIELD.name().toLowerCase().replace("_", "-") + "/");
		MOUNTAIN.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + MOUNTAIN.name().toLowerCase().replace("_", "-") + "/");
		POND.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + POND.name().toLowerCase().replace("_", "-") + "/");
		SEA.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + SEA.name().toLowerCase().replace("_", "-") + "/");
	}
	
	/**
	 * Gets the terrain name in lower case.
	 *
	 * @return the terrain string
	 */
	public String asLowerCase() {
		return this.name().toLowerCase();
	}

	/**
	 * Gets the API address for the specific terrain type.
	 *
	 * @return the API address
	 */
	public String getAPIAdress() {
		return APIAdress;
	}

	/**
	 * Sets the API address for the specific terrain type.
	 *
	 * @param aPIAdress the new API address
	 */
	public void setAPIAdress(String aPIAdress) {
		APIAdress = aPIAdress;
	}
}
