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
	private Shop shop;
	
	public Shop createShop() {
		return
			new Shop(generateShopInventory());
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
	
	public boolean buyItem(int itemIndex, Player player){
		
		Item item = shop.getShopInventory().get(itemIndex);
		
		if (player.getMoney()<item.getItemPrice()) {
			return false;
			} 
		else {
			playerService.addToBag(item);
			playerService.addMoney(-1*item.getItemPrice());
			return true;
			}
	}
	
	public void sellItem(String itemType, String itemName, Player player) {
		
		Item item = null;
		
		if(itemType.equals("potion")) {
			item = itemController.createPotion(itemName);
		}
		else if (itemType.equals("pokeball")) {
			item = itemController.createPokeball(itemName);
		}

		playerService.addMoney((int)0.5*item.getItemPrice());
		player.removeFromBag(item);
	}
}
