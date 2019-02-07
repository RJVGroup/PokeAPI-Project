package com.QA.pokemonapp.business.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.ItemPokeball;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;


/**
 * The Class ItemService.
 * This class gets and processes information pertaining to the different items.
 */
@Service
public class ItemService {
	
	@Autowired
	private Player player;

	/**
	 * Use pokeball.
	 * All logic in this method is taken from the 1st generation pokeball use calculations.
	 *
	 * @param ePokemon the enemy pokemon to use the pokeball against
	 * @param pokeball the pokeball item
	 * @return true, if pokeball has successfully caught enemy pokemon
	 */
	public boolean usePokeball(EnemyPokemon ePokemon, ItemPokeball pokeball) {
		Pokemon pokemon = ePokemon.getEnemyMon();
		double modifiedCatchRate = ((3 * pokemon.getHP() - 2 * pokemon.getCurrentHP()) * pokemon.getCatchRate() * pokeball.getCatchModifier())/ 3 * pokemon.getHP();
		modifiedCatchRate = pokemon.getStatus() == null ? modifiedCatchRate : modifiedCatchRate * 1.5;
		double b = Math.round((Math.random() * 255));
		player.removeFromBag(pokeball);
		return b <= modifiedCatchRate;
	}
	
	/**
	 * Use potion on specified pokemon in the players party.
	 * Potions cannot be used on a pokemon that has fainted.
	 *
	 * @param target the target pokemon in the players party
	 * @param potion the potion to be used
	 * @return true, if successful
	 */
	public boolean usePotion(Pokemon target, ItemPotion potion) {
		if (target.getStatus() == EStatus.FAINT) {
			return false;
		}
		
		int newHealth = Math.min(target.getCurrentHP() + potion.getRestoreAmount(),target.getHP());
		target.setCurrentHP(newHealth);
		player.removeFromBag(potion);
		return true;
	}
}
