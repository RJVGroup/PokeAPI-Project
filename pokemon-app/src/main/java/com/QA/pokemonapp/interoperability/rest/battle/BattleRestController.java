package com.QA.pokemonapp.interoperability.rest.battle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.BattleManager;
import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.Move;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;

/**
 * The Class BattleRestController.
 * This class exposes methods which pertain to pokemon battles via REST.
 */
@RestController
@RequestMapping("/api/battle")
public class BattleRestController {

	/** The battle manager. */
	@Autowired
	private BattleManager battleManager;
	
	/** The enemy pokemon. */
	@Autowired
	private EnemyPokemon enemyPokemon;
	
	
	/** The player service. */
	@Autowired
	private PlayerService playerService;
	
	/**
	 * Takes a turn using the chosen move from the chosen pokemon against the enemy pokemon.
	 *
	 * @param chosenPokemon the chosen pokemons index from the players party with which to take the move with
	 * @param chosenMove the chosen moves index from the chosen pokemons move list
	 * @return the outcome of the turn. 0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
	 */
	@PostMapping(value = "/turnM/{chosenPokemon}/{chosenMove}")
	public int takeTurnMove(@PathVariable int chosenPokemon, @PathVariable int chosenMove) {
		Pokemon a = playerService.getParty().get(chosenPokemon);
		Move b = a.getMoveList().get(chosenMove);
		battleManager.setEnemyMon();
		return
				battleManager.takeATurn(
						a,
						b,
						b.isTargetSelf());
	}
	
	/**
	 * Takes a turn by using a potion item.
	 * The item can only be a potion used on the players pokemon.
	 *
	 * @param chosenPokemon the chosen pokemons index from the players party
	 * @param chosenItem the chosen items index from the players bag
	 * @return the outcome of the turn. 0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
	 */
	@PostMapping(value = "/turnI/{chosenPokemon}/{chosenItem}")
	public int takeTurnItem(@PathVariable int chosenPokemon, @PathVariable int chosenItem) {
		Pokemon a = playerService.getParty().get(chosenPokemon);
		battleManager.setEnemyMon();
		return
				battleManager.takeATurn(
						a,
						playerService.getBag().get(chosenItem),
						playerService.getBag().get(chosenItem).getClass() == ItemPotion.class);
	}
	
	/**
	 * Attempts to run from the battle.
	 *
	 * @param chosenPokemon the currently equipped pokemons index from the players party
	 * @return the outcome of the turn. 0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
	 */
	@PostMapping(value = "/turnR/{chosenPokemon}")
	public int takeTurnRun(@PathVariable int chosenPokemon) {
		battleManager.setEnemyMon();
		return
				battleManager.takeATurn(playerService.getParty().get(chosenPokemon));
	}
	

	/**
	 * Check enemy.
	 *
	 * @return the enemy pokemon
	 */
	@GetMapping(value = "/getEnemyStatus")
	public EnemyPokemon checkEnemy() {
		return enemyPokemon;
	}
	
	/**
	 * Check friendly.
	 *
	 * @param partyPosition the party position
	 * @return the pokemon
	 */
	@GetMapping(value = "/getFriendlyStatus/{partyPosition}")
	public Pokemon checkFriendly(@PathVariable int partyPosition) {
		return playerService.getParty().get(partyPosition);
	}
	
	/**
	 * Gets the move used.
	 *
	 * @return the move used
	 */
	@GetMapping(value ="/enemyMostRecentMove")
	public Move getMoveUsed() {
		return enemyPokemon.getEnemyMon().getMoveList().get(enemyPokemon.getLastUsedMove());

	}
}
