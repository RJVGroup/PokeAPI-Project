package com.QA.pokemonapp.interoperability.rest.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.item.ItemService;
import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.items.ItemPokeball;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;

/**
 * The Class ItemRestController.
 * This class exposes REST functions which pertain to the use of items.
 */
@RestController
@RequestMapping("/api/item")
public class ItemRestController {
	
	/** The item service. */
	@Autowired
	private ItemService itemService;
	
	/** The player service. */
	@Autowired
	private PlayerService playerService;
	
	/** The enemy pokemon. */
	@Autowired
	private EnemyPokemon enemyPokemon;
	
	/**
	 * Uses pokeball against the enemy pokemon.
	 * Only one enemy pokemon is present in the program at a time so no enemy needs to be specified.
	 *
	 * @param pokeballIndex the pokeball's index within the players bag
	 * @return true, if pokeball has successfully caught the enemy pokemon
	 */
	@PostMapping("/pokeball/{pokeballIndex}")
	public boolean usePokeball(@PathVariable int pokeballIndex) {
		return 
			itemService.usePokeball(
					enemyPokemon, 
					(ItemPokeball) playerService.getBag().get(pokeballIndex));
			
		
	}
	
	/**
	 * Uses a potion on a chosen pokemon in the palyers party.
	 *
	 * @param potionIndex the potions index in the players bag
	 * @param chosenPokemonIndex the chosen pokemons index in the players party
	 * @return true, if the potion can be applied to the pokemon. The potion cannot be applied is pokemon has fainted.
	 */
	@PostMapping("/potion/{potionIndex}/{chosenPokemonIndex}")
	public boolean usePotion(@PathVariable int potionIndex, @PathVariable int chosenPokemonIndex) {
		return 
			itemService.usePotion(
					playerService.getParty().get(chosenPokemonIndex),
					(ItemPotion) playerService.getBag().get(potionIndex));
		
	}

}
