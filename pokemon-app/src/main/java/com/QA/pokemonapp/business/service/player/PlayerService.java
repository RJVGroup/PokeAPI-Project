package com.QA.pokemonapp.business.service.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.terrain.TerrainInterface;
import com.QA.pokemonapp.business.service.type.Player;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.Terrain;
import com.QA.pokemonapp.persistance.domain.items.Item;

public class PlayerService {
	
	@Autowired
	Player player;
	
	@Autowired
	TerrainInterface terrainGenerator;
	
	public PlayerService() {}
	
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
	
	public List<Pokemon> getParty() {
		return
				player.getParty();
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
	
	public Terrain move() {
		return
		terrainGenerator.getTerrain(player.getParty().stream().mapToInt(Pokemon::getLevel).sum() / player.getParty().size());
	
	}
}
