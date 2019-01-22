package com.QA.pokemonapp.constantsandenums;


public enum EPotion {
	POTION,
	SUPER_POTION,
	HYPER_POTION,
	MAX_POTION;
	
	private String APIAddress;
	
	static {
		POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + POTION.name().toLowerCase() + "/");
		SUPER_POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + SUPER_POTION.name().toLowerCase().replace("_", "-") + "/");
		HYPER_POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + HYPER_POTION.name().toLowerCase().replace("_", "-") + "/");
		MAX_POTION.setAPIAddress(Constants.APIRootAddress + Constants.APIItemAddress + MAX_POTION.name().toLowerCase().replace("_", "-") + "/");
	}

	public String getAPIAddress() {
		return APIAddress;
	}

	public void setAPIAddress(String aPIAddress) {
		APIAddress = aPIAddress;
	}
	
	
}
