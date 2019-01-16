package com.QA.pokemonapp.constantsandenums;

public enum EPokeball {
	POKE_BALL,
	GREAT_BALL,
	ULTRA_BALL,
	MASTER_BALL;
	
	private String APIAddress;
	
	static {
		POKE_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + POKE_BALL.name().toLowerCase() + "/");
		GREAT_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + GREAT_BALL.name().toLowerCase().replace("_", "-") + "/");
		ULTRA_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + ULTRA_BALL.name().toLowerCase().replace("_", "-") + "/");
		MASTER_BALL.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + MASTER_BALL.name().toLowerCase().replace("_", "-") + "/");
	}

	public String getAPIAddress() {
		return APIAddress;
	}

	public void setAPIAddress(String aPIAddress) {
		APIAddress = aPIAddress;
	}
	
}
