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

/**
 * The Class PlayerRestController.
 * Contains the REST layer services which allow React to communicate with Spring in ways which affect the player class.
 */
@RestController
@RequestMapping("/api/player")
public class PlayerRestController {
	
	/** The player service. */
	@Autowired
	private PlayerService playerService;
	
	/** The Pokemon generator. */
	@Autowired
	private PokemonGeneratorInterface PokemonGenerator;

	/**
	 * Adds the starter pokemon.
	 * The starter pokemon is automatically added at level 5.
	 * React is used to control the choice of starter pokemon.
	 *
	 * @param chosenPokemon the chosen pokemon
	 * @return true, if player can successfully add pokemon to their party
	 */
	@PostMapping("/add-starter-pokemon/{chosenPokemon}")
	public boolean addStarterPokemon(@PathVariable String chosenPokemon) {
		
		return
		playerService.addToParty(
				PokemonGenerator.createPokemon(5, chosenPokemon));

	}
	
	/**
	 * Adds a pokemon.
	 * This method may be deprecated and built into the useItem pokeball method.
	 *
	 * @param chosenPokemon the chosen pokemon
	 * @param pokemonLevel the pokemon level
	 * @return true, if player can successfully add pokemon to their party
	 */
	@PostMapping("/add-pokemon/{chosenPokemon}/{pokemonLevel}")
	public boolean addPokemon(@PathVariable String chosenPokemon, @PathVariable int pokemonLevel) {
		
		return
		playerService.addToParty(
				PokemonGenerator.createPokemon(pokemonLevel, chosenPokemon));
		
	}
	
	/**
	 * gets the players money.
	 *
	 * @return the money
	 */
	@GetMapping("/show-balance")
	public int getMoney() {
		return
			playerService.getMoney();
	}
	
	/**
	 * Gets the players party of pokemon.
	 *
	 * @return the party
	 */
	@GetMapping("/show-party")
	public List<Pokemon> getParty() {
		return
			playerService.getParty();
	}
	
	/**
	 * Gets the players pokemon from their party at a specific index.
	 * React maintains currently chosen pokemons index, this allows for more efficient REST calls.
	 *
	 * @param pokemonIndex the pokemon index
	 * @return the pokemon at said index
	 */
	@GetMapping("/show-pokemon/{pokemonIndex}")
	public Pokemon getPokemon(@PathVariable int pokemonIndex) {
		return
				playerService.getParty().get(pokemonIndex);
	}
	
	/**
	 * Gets the players bag.
	 *
	 * @return the bag
	 */
	@GetMapping("/show-bag")
	public List<Item> getBag() {
		return
			playerService.getBag();
	}
	
	/**
	 * Move player.
	 * This REST call will produce a new Terrain object which contains the terrain type and an enemy pokemon if one is created.
	 *
	 * @return the terrain which contains terrain type and enemy pokemon
	 */
	@PostMapping("/move")
	public Terrain movePlayer() {
		return
			playerService.move();
	}
}
