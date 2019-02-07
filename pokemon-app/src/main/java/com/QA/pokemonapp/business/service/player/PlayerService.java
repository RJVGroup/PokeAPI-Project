package com.QA.pokemonapp.business.service.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.terrain.TerrainInterface;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.Terrain;
import com.QA.pokemonapp.persistance.domain.items.Item;

/**
 * The Class PlayerService.
 * This class gets and processes information pertaining to the player.
 */
public class PlayerService {
	

	@Autowired
	private Player player;
	
	@Autowired
	private TerrainInterface terrainGenerator;
	
	/**
	 * Instantiates a new player service.
	 * Needed for spring bean instantiation.
	 */
	public PlayerService() {}
	
	/**
	 * Adds money to the players balance.
	 *
	 * @param income the amount to be added to the players balance
	 */
	public void addMoney(int income) {
		player.setMoney(player.getMoney() + income);
	}
	
	/**
	 * Adds a new pokemon to the players party.
	 * Player can only have a maximum of 6 pokemon in their party.
	 *
	 * @param newCatch the new pokemon to be added to the party
	 * @return true, if can successfully add pokemon to party
	 */
	public boolean addToParty(Pokemon newCatch) {
		if (player.getParty().isEmpty() | player.getParty().size() < 6) {
			player.getParty().add(newCatch);
			return true;
		}
		return false;
	}
	
	/**
	 * Gets the players money.
	 *
	 * @return the money
	 */
	public int getMoney() {
		return
			player.getMoney();
	}
	
	/**
	 * Gets the players bag.
	 * The bag contains a list of items.
	 *
	 * @return the bag
	 */
	public List<Item> getBag() {
		return
			player.getBag();
	}
	
	/**
	 * Gets the players party.
	 * The party contains pokemon the player has chosen or caught.
	 *
	 * @return the party
	 */
	public List<Pokemon> getParty() {
		return
				player.getParty();
	}
	
	/**
	 * Removes a pokemon from the players party.
	 *
	 * @param release the pokemon to be released
	 * @return true, if successful
	 */
	public boolean removeFromParty(Pokemon release) {
		if (player.getParty().size() > 1) {
			player.getParty().remove(release);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds an item to the players bag.
	 * The player has a max bag size of 100.
	 *
	 * @param newItem the new item to be added
	 * @return true, if item is successfully added
	 */
	public boolean addToBag(Item newItem) {
		if (player.getBag().size() < 100) {
			player.getBag().add(newItem);
			return true;
		}
		return false;
	}
	
	/**
	 * Removes an item from the players bag.
	 *
	 * @param removeItem the item to remove
	 * @return true, if successful
	 */
	public boolean removeFromBag(Item removeItem) {
		if (player.getBag().contains(removeItem)) {
			player.getBag().remove(removeItem);
			return true;
		}
		return false;
	}
	
	
	/**
	 * Moves the player.
	 * This method creates a random new terrain type which can contain a random enemy pokemon.
	 *
	 * @return the terrain
	 */
	public Terrain move() {
		return
			terrainGenerator.getTerrain(player.getParty().stream().mapToInt(Pokemon::getLevel).sum() / player.getParty().size());
	
	}
	}
