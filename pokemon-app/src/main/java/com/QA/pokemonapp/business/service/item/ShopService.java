package com.QA.pokemonapp.business.service.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.constantsandenums.GetRandomFromEnum;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.Shop;
import com.QA.pokemonapp.persistance.domain.items.Item;

/**
 * The Class ShopService.
 * This class gets and processes information pertaining to the shop.
 */
@Service
public class ShopService {
	
	@Autowired
	private ItemGeneratorInterface itemController;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private Shop shop;
	
	/**
	 * Creates the shop.
	 * The shop contains a list of four randomly generated pokeballs and/or potions.
	 * The pokeball and potion types are given as enums.
	 *
	 * @return the shop inventory list
	 */
	public List<Item> createShop() {
		List<Item> inventory = generateShopInventory();
		
		shop = new Shop(inventory);
		
		return
			shop.getShopInventory();
	}
	
	/**
	 * Generate shop inventory.
	 * Randomly generates four items of pokeballs or potions.
	 * Pokeball and potion types are also randomly generated taken from the their respective enum Types.
	 *
	 * @return the list of randomly generated items
	 */
	public List<Item> generateShopInventory() {
		
		List<Item> shopInventory = new ArrayList<Item>();
		Random random = new Random();
		
		int count = 0;
		
		do {
			if (random.nextBoolean()) {
				shopInventory.add(
					itemController.createPotion(
							GetRandomFromEnum.generatePotionType().name()));
				count++;
			} 
			else {
				shopInventory.add(
					itemController.createPokeball(
							GetRandomFromEnum.generatePokeballType().name()));
				count++;

			} 
		} while (count < 4);
		
		return shopInventory;
	}
	
	/**
	 * Gets the shop inventory.
	 *
	 * @return the inventory
	 */
	public List<Item> getInventory() {
		return
			shop.getShopInventory();
	}
	
	/**
	 * Buy a specified item from the shop.
	 * Items can only be purchased if players balance > item cost
	 *
	 * @param itemIndex the item index from the shop inventory
	 * @param player the player, used to get the players balance
	 * @return true, if the player can successfully buy the item
	 */
	public boolean buyItem(int itemIndex, Player player){
		
		Item item =	shop.getShopInventory().get(itemIndex);
		
		if (player.getMoney() < item.getItemPrice()) {
			return false;
		} else {
			playerService.addToBag(item);
			playerService.addMoney(-1 * item.getItemPrice());
			return true;
		}
	}
	
	/**
	 * Sells a palyers item to the shop.
	 *
	 * @param itemIndex the item index from the players bag
	 * @param player the player, used to add money to the players balance
	 * @return the item sell amount
	 */
	public int sellItem(int itemIndex, Player player) {
		
		Item item = playerService.getBag().get(itemIndex);
		
		int sellPrice = item.getItemPrice()/2;

		playerService.removeFromBag(item);
		playerService.addMoney(sellPrice);
		
		
		return 
			sellPrice;
	}
}
