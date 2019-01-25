package com.QA.pokemonapp.persistance.domain;

public class Terrain {
	
	String name;
	
	EnemyPokemon pokemonEncountered;
	
	public Terrain(String name, EnemyPokemon pokemonEncountered) {
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

	public EnemyPokemon getPokemonEncountered() {
		return pokemonEncountered;
	}

	public void setPokemonEncountered(EnemyPokemon pokemonEncountered) {
		this.pokemonEncountered = pokemonEncountered;
	}
	
	

}
