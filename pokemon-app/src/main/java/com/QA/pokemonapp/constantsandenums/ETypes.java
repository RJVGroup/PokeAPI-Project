package com.QA.pokemonapp.constantsandenums;

/**
 * The Enum ETypes.
 * Holds and returns the PokeAPI address for the different pokemon types employed by this program.
 */
public enum ETypes {

	/** The normal pokemon type. */
	NORMAL,
	/** The ground pokemon type. */
	GROUND,
	/** The rock pokemon type. */
	ROCK,
	/** The fire pokemon type. */
	FIRE,
	/** The water pokemon type. */
	WATER,
	/** The grass pokemon type. */
	GRASS,
	/** The electric pokemon type. */
	ELECTRIC,
	/** The fighting pokemon type. */
	FIGHTING,
	/** The ghost pokemon type. */
	GHOST,
	/** The psychic pokemon type. */
	PSYCHIC,
	/** The bug pokemon type. */
	BUG,
	/** The poison pokemon type. */
	POISON,
	/** The dragon pokemon type. */
	DRAGON,
	/** The flying pokemon type. */
	FLYING,
	/** The ice pokemon type. */
	ICE,
	/** The dark pokemon type. */
	DARK,
	/** The steel pokemon type. */
	STEEL,
	/** The fairy pokemon type. */
	FAIRY,
	/** The shadow pokemon type. */
	SHADOW;

	private String APIAddress;

	static {
		NORMAL.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + NORMAL.name().toLowerCase() + "/");
		GROUND.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + GROUND.name().toLowerCase() + "/");
		ROCK.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + ROCK.name().toLowerCase() + "/");
		FIRE.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + FIRE.name().toLowerCase() + "/");
		WATER.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + WATER.name().toLowerCase() + "/");
		GRASS.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + GRASS.name().toLowerCase() + "/");
		ELECTRIC.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + ELECTRIC.name().toLowerCase() + "/");
		FIGHTING.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + FIGHTING.name().toLowerCase() + "/");
		GHOST.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + GHOST.name().toLowerCase() + "/");
		PSYCHIC.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + PSYCHIC.name().toLowerCase() + "/");
		BUG.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + BUG.name().toLowerCase() + "/");
		POISON.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + POISON.name().toLowerCase() + "/");
		DRAGON.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + DRAGON.name().toLowerCase() + "/");
		FLYING.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + FLYING.name().toLowerCase() + "/");
		ICE.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + ICE.name().toLowerCase() + "/");
		DARK.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + DARK.name().toLowerCase() + "/");
		STEEL.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + STEEL.name().toLowerCase() + "/");
		FAIRY.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + FAIRY.name().toLowerCase() + "/");
		SHADOW.setAPIAddress(Constants.APIRootAddress + Constants.APITypeAddress + SHADOW.name().toLowerCase() + "/");
	}

	/**
	 * Gets the API address for the specified pokemon type.
	 *
	 * @return the API address
	 */
	public String getAPIAddress() {
		return APIAddress;
	}

	/**
	 * Sets the API address for the specified pokemon type.
	 *
	 * @param aPIAddress the new API address
	 */
	public void setAPIAddress(String aPIAddress) {
		APIAddress = aPIAddress;
	}
}
