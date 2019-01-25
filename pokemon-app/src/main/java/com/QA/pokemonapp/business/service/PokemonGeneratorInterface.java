package com.QA.pokemonapp.business.service;

import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.Pokemon;

public interface PokemonGeneratorInterface {

	Pokemon createPokemon(int level, String name);
	
	EnemyPokemon createEnemyPokemon(int level, String name);
}
