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

	@PostMapping("/choose-starter/{chosenPokemon}")
	public List<Pokemon> addStarterPokemon(@PathVariable String chosenPokemon) {
		
		playerService.addToParty(
				PokemonGenerator.createPokemon(5, chosenPokemon));
		return 
			playerService.getParty();
	}
	
	@GetMapping("/show-party")
	public List<Pokemon> getParty() {
		return
			playerService.getParty();
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
