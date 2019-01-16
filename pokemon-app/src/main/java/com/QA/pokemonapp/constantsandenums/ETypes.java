package com.QA.pokemonapp.constantsandenums;

public enum ETypes {
	NORMAL, GROUND, ROCK, FIRE, WATER, GRASS, ELECTRIC, FIGHTING, GHOST, PSYCHIC, BUG, POISON, DRAGON, FLYING, ICE;

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
	}

	public String getAPIAddress() {
		return APIAddress;
	}

	public void setAPIAddress(String aPIAddress) {
		APIAddress = aPIAddress;
	}
}
