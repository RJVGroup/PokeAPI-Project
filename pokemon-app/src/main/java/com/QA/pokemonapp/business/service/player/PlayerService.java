package com.QA.pokemonapp.business.service.player;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.BattleManager;
import com.QA.pokemonapp.business.service.PokemonGeneratorService;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.Item;

public class PlayerService {
	
	@Autowired
	Player player;
	
	PlayerService() {}
	
	public void addMoney(int income) {
		player.setMoney(income + player.getMoney());
	}
	
	public boolean addToParty(Pokemon newCatch) {
		if (player.getParty().size() < 6) {
			player.getParty().add(newCatch);
			return true;
		}
		return false;
	}
	
	public boolean removeFromParty(Pokemon release) {
		if (player.getParty().size() > 1) {
			player.getParty().remove(release);
			return true;
		}
		return false;
		
	}
	
		public boolean addToBag(Item newItem) {
		if (player.getBag().size() < 100) {
			player.getBag().add(newItem);
			return true;
		}
		return false;
		
	}
	
			public void move(char direction) {
		switch(direction) {
		case  'w':
			player.getPosition()[0] += 1;
			break;
		case  'a':
			player.getPosition()[1] -= 1;
			break;
		case  's':
			player.getPosition()[0] -= 1;
			break;
		case  'd':
			player.getPosition()[1] += 1;
			break;
		}
		Tile next = TerrainGenerator.getTile(this.position[0], this.position[1]);
		if (isBattle(next)){
			int averageLevel = this.party.stream().mapToInt(Pokemon::getLevel).sum() / this.party.size();
			String species = APIManager.getSpeciesByTerrainTypeAndLevel(next.terrainType, averageLevel);
			BattleManager.begin(PokemonGeneratorService.createPokemon(averageLevel, species));
		}
	}
	}
}
