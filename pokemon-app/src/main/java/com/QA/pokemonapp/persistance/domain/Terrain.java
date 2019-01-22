package com.QA.pokemonapp.persistance.domain;

public class Terrain {
	
	String name;
	
	Pokemon pokemonEncountered;
	
	public Terrain(String name, Pokemon pokemonEncountered) {
		super();
		this.name = name;
		this.pokemonEncountered = pokemonEncountered;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Pokemon getPokemonEncountered() {
		return pokemonEncountered;
	}

	public void setPokemonEncountered(Pokemon pokemonEncountered) {
		this.pokemonEncountered = pokemonEncountered;
	}
	
	

}
