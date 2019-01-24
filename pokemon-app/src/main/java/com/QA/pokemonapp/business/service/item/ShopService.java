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

@Service
public class ShopService {
	
	@Autowired
	private ItemGeneratorInterface itemController;
	
	@Autowired
	private PlayerService playerService;
	
	@Autowired
	Shop shop;
	
	public List<Item> createShop() {
		List<Item> inventory = generateShopInventory();
		
		shop = new Shop(inventory);
		
		return
			shop.getShopInventory();
	}
	
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
	
	public List<Item> getInventory() {
		return
			shop.getShopInventory();
	}
	
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
	
	public int sellItem(int itemIndex, Player player) {
		
		Item item = playerService.getBag().get(itemIndex);
		
	/*	if(itemName.contains("potion")) {
			item = itemController.createPotion(itemName.toUpperCase().replace("-", "_"));
		}
		else if (itemName.contains("ball")) {
			item = itemController.createPokeball(itemName.toUpperCase().replace("-", "_"));
		}*/
		
		int amount = (int)0.5*item.getItemPrice();
		
		playerService.removeFromBag(item);
		playerService.addMoney(amount);
		
		
		return 
			amount;
	}
}
