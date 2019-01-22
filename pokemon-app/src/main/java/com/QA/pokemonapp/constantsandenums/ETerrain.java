package com.QA.pokemonapp.constantsandenums;

public enum ETerrain {
	FOREST,
	FIELD,
	MOUNTAIN,
	POND,
	SEA;
	
	public String asLowerCase() {
		return this.name().toLowerCase();
	}
}
