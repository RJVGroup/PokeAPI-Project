package com.QA.pokemonapp.business.service;

import com.QA.pokemonapp.persistance.domain.Pokemon;

public interface PokemonGeneratorInterface {

	Pokemon createPokemon(int level, String name);
}
