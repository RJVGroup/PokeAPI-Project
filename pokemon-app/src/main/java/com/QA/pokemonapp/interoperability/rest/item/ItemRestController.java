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

@RestController
@RequestMapping("/api/item")
public class ItemRestController {
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private EnemyPokemon enemyPokemon;
	
	@PostMapping("/pokeball/{pokeballIndex}")
	public boolean usePokeball(@PathVariable int pokeballIndex) {
		return 
			itemService.usePokeball(
					enemyPokemon, 
					(ItemPokeball) playerService.getBag().get(pokeballIndex));
			
		
	}
	
	@PostMapping("/potion/{potionIndex}/{chosenPokemonIndex}")
	public boolean usePotion(@PathVariable int potionIndex, @PathVariable int chosenPokemonIndex) {
		return 
			itemService.usePotion(
					playerService.getParty().get(chosenPokemonIndex),
					(ItemPotion) playerService.getBag().get(potionIndex));
		
	}

}
