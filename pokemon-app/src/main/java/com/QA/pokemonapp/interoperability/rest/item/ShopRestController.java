package com.QA.pokemonapp.interoperability.rest.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.item.ShopService;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.items.Item;

/**
 * The Class ShopRestController.
 * Exposes REST methods which pertain to interaction with the shop.
 */
@RestController
@RequestMapping("/api/shop")
public class ShopRestController {
	
	/** The shop service. */
	@Autowired
	private ShopService shopService;
	
	/** The player. */
	@Autowired
	private Player player;
	
	/**
	 * Creates the shop.
	 * Returns a randomly generated list of items containing pokeballs and potions.
	 *
	 * @return the list
	 */
	@GetMapping("/generate")
	public List<Item> createShop() {
		return
			shopService.createShop();
	}
	
	/**
	 * Gets the shop inventory.
	 * Used to re-render the shop after an item has been bought.
	 *
	 * @return the shops inventory
	 */
	@GetMapping("/get-inventory")
	public List<Item> getInventory() {
		return
			shopService.getInventory();
	}
	
	/**
	 * Buy an item from the shop.
	 * This method returns true if the price of the item is less than the players current balance. 
	 *
	 * @param itemIndex the items index in the shop inventory
	 * @return true, if the player can successfully purchase an item.
	 */
	@PostMapping("/buy-item/{itemIndex}")
	public boolean buyItem(@PathVariable int itemIndex) {
		return
			shopService.buyItem(itemIndex, player);
	}

	/**
	 * Sell an item from the players bag.
	 * The items selling price is half of the items buy price.
	 *
	 * @param itemIndex the items index within the players bag
	 * @return the amount the item has sold for
	 */
	@PostMapping("sell-item/{itemIndex}")
	public int sellItem(@PathVariable int itemIndex) {
		return
			shopService.sellItem(itemIndex, player);
	}
}
