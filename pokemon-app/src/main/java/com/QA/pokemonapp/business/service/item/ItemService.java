package com.QA.pokemonapp.business.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.ItemPokeball;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;

// github has broken needed to remerge this file

@Service
public class ItemService {
	
	@Autowired
	private Player player;

	public boolean usePokeball(EnemyPokemon pokemon, ItemPokeball pokeball) {
		double modifiedCatchRate = ((3 * pokemon.getHP() - 2 * pokemon.getCurrentHP()) * pokemon.getCatchRate() * pokeball.getCatchModifier())/ 3 * pokemon.getHP();
		modifiedCatchRate = pokemon.getStatus() == null ? modifiedCatchRate : modifiedCatchRate * 1.5;
		double b = Math.round((Math.random() * 255));
		player.removeFromBag(pokeball);
		return b <= modifiedCatchRate;
	}
	
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
