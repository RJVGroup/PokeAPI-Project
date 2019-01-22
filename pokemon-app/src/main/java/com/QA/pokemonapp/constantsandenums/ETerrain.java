package com.QA.pokemonapp.constantsandenums;

public enum ETerrain {
	FOREST,
	FIELD,
	MOUNTAIN,
	POND,
	SEA;
	
	private String APIAdress;
	
	static {
		FOREST.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + FOREST.name().toLowerCase().replace("_", "-") + "/");
		FIELD.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + FIELD.name().toLowerCase().replace("_", "-") + "/");
		MOUNTAIN.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + MOUNTAIN.name().toLowerCase().replace("_", "-") + "/");
		POND.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + POND.name().toLowerCase().replace("_", "-") + "/");
		SEA.setAPIAdress(Constants.APIRootAddress + Constants.APITerrainAddress + SEA.name().toLowerCase().replace("_", "-") + "/");
	}
	
	public String asLowerCase() {
		return this.name().toLowerCase();
	}

	public String getAPIAdress() {
		return APIAdress;
	}

	public void setAPIAdress(String aPIAdress) {
		APIAdress = aPIAdress;
	}
}
