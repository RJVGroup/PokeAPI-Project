package com.QA.pokemonapp.business.service.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.terrain.TerrainInterface;
import com.QA.pokemonapp.persistance.domain.Player;
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
		player.setMoney(player.getMoney() + income);
	}
	
	public boolean addToParty(Pokemon newCatch) {
		if (player.getParty().isEmpty() | player.getParty().size() < 6) {
			player.getParty().add(newCatch);
			return true;
		}
		return false;
	}
	
	public int getMoney() {
		return
			player.getMoney();
	}
	
	public List<Item> getBag() {
		return
			player.getBag();
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
	
	public boolean removeFromBag(Item removeItem) {
		if (player.getBag().contains(removeItem)) {
			player.getBag().remove(removeItem);
			return true;
		}
		return false;
	}
	
	
	public Terrain move() {
		return
			terrainGenerator.getTerrain(player.getParty().stream().mapToInt(Pokemon::getLevel).sum() / player.getParty().size());
	
	}
}
