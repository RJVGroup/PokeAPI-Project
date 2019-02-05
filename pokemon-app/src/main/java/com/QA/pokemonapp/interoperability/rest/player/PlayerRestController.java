package com.QA.pokemonapp.interoperability.rest.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.PokemonGeneratorInterface;
import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.Terrain;
import com.QA.pokemonapp.persistance.domain.items.Item;

@RestController
@RequestMapping("/api/player")
public class PlayerRestController {
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private PokemonGeneratorInterface PokemonGenerator;

	@PostMapping("/add-starter-pokemon/{chosenPokemon}")
	public boolean addStarterPokemon(@PathVariable String chosenPokemon) {
		
		return
		playerService.addToParty(
				PokemonGenerator.createPokemon(5, chosenPokemon));

	}
	
	@PostMapping("/add-pokemon/{chosenPokemon}/{pokemonLevel}")
	public boolean addPokemon(@PathVariable String chosenPokemon, @PathVariable int pokemonLevel) {
		
		return
		playerService.addToParty(
				PokemonGenerator.createPokemon(pokemonLevel, chosenPokemon));
		
	}
	
	@GetMapping("/show-balance")
	public int getMoney() {
		return
			playerService.getMoney();
	}
	
	@GetMapping("/show-party")
	public List<Pokemon> getParty() {
		return
			playerService.getParty();
	}
	
	@GetMapping("/show-pokemon/{pokemonIndex}")
	public Pokemon getPokemon(@PathVariable int pokemonIndex) {
		return
				playerService.getParty().get(pokemonIndex);
	}
	
	@GetMapping("/show-bag")
	public List<Item> getBag() {
		return
			playerService.getBag();
	}
	
	@PostMapping("/move")
	public Terrain movePlayer() {
		return
			playerService.move();
	}
}
