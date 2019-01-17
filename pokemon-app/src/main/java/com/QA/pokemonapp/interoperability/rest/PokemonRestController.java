package com.QA.pokemonapp.interoperability.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.PokemonGeneratorService;
import com.QA.pokemonapp.persistance.domain.Pokemon;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonRestController {

	@Autowired
	private PokemonGeneratorService pokemonService;
	
	@GetMapping("/{pokemonLevel}/{pokemonName}")
	public Pokemon createPokemon(@PathVariable int pokemonLevel, @PathVariable String pokemonName)
	{
		return 
			pokemonService.createPokemon(pokemonLevel, pokemonName);
	}
	
}
