package com.QA.pokemonapp.interoperability.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.repository.PokemonRepository;

@RestController
@RequestMapping("api/v2")
public class PokemonController {
	
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@GetMapping("/pokemon/{nameOfPokemon}")
	public Pokemon getPokemon(@PathVariable String Pokemon)
	{
		return null;
		
	}
}
