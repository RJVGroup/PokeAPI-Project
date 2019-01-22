package com.QA.pokemonapp.persistance.repository;

import com.QA.pokemonapp.persistance.domain.Pokemon;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long>{

}
