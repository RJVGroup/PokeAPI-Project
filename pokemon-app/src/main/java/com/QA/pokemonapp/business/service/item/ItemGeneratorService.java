package com.QA.pokemonapp.business.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.QA.pokemonapp.constantsandenums.EPokeball;
import com.QA.pokemonapp.constantsandenums.EPotion;
import com.QA.pokemonapp.interoperability.rest.item.ItemAPIController;
import com.QA.pokemonapp.persistance.domain.items.ItemPokeball;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * The Class ItemGeneratorService.
 * This class gets and processes information pertaining to the different items the player can use in this program.
 */
public class ItemGeneratorService implements ItemGeneratorInterface {
	
	@Autowired
	private ItemAPIController itemController;
	
	private Object itemJson;
	
	/**
	 * Instantiates a new item generator service.
	 * Needed for spring bean instantiation.
	 */
	public ItemGeneratorService() {}
	
	/* Creates a new pokeball.
	 * Gets the information from the PokeAPI.
	 * 
	 */
	@Cacheable("pokeball")
	public ItemPokeball createPokeball(String itemName) {
		
		getPokebalJson(itemName);
		
		return 
			new ItemPokeball(getItemName(), getItemID(), getItemDescription(), getItemPrice(), getCatchRateModifier());
		
	}
	
	/* Creates a new potion.
	 * Gets the information from the PokeAPI.
	 * 
	 */
	@Cacheable("potion")
	public ItemPotion createPotion(String itemName) {
		
			getPotionJson(itemName);
		
		return 
			new ItemPotion(getItemName(), getItemID(), getItemDescription(), getItemPrice(), getRestoreAmount());
		
	}
	
	/**
	 * Gets the pokebal information json.
	 *
	 * @param item the item name
	 * @return the pokebal information json
	 */
	public void getPokebalJson(String item) {
		String itemString = itemController.getItem(EPokeball.valueOf(item));
		itemJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}
	
	/**
	 * Gets the potion information json.
	 *
	 * @param item the item name
	 * @return the potion information json
	 */
	public void getPotionJson(String item) {
		String itemString = itemController.getItem(EPotion.valueOf(item));
		itemJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}
	
	/**
	 * Gets the item name from the json.
	 *
	 * @return the item name
	 */
	public String getItemName() {
		return 
			JsonPath.read(itemJson, "$.name");
	}
	
	/**
	 * Gets the item ID from the json.
	 *
	 * @return the item ID
	 */
	public int getItemID() {
		return 
			JsonPath.read(itemJson, "$.id");
	}
	
	/**
	 * Gets the item description from the json.
	 *
	 * @return the item description
	 */
	public String getItemDescription() {
		return 
			JsonPath.read(itemJson, "$.effect_entries.[0].effect");
	}
	
	/**
	 * Gets the restore amount.
	 * Gets the amount by extracting the numbers from the .
	 *
	 * @return the restore amount
	 */
	public int getRestoreAmount() {
		return
			getDigitsFromEffect().intValue();
	}
	
	/**
	 * Gets the digits from effect.
	 * Extracts only the digits from the description.
	 * These digits give the restore amount or catch rate depending on the item type.
	 *
	 * @return the digits from effect
	 */
	public Double getDigitsFromEffect() {
		String digits =
					getItemDescription().replaceAll("\\D+","");
		
		if(digits.isEmpty()) {
			return 1000.0;
		}
			
		return
			Double.parseDouble(digits);
	}
	
	/**
	 * Gets the catch rate modifier.
	 * Adds a decimal point into the catch rate modifier if there are two numbers as getDigitsFromEffect extracts only digits.
	 *
	 * @return the catch rate modifier
	 */
	public Double getCatchRateModifier() {
		Double digits = getDigitsFromEffect();
		
		if(String.valueOf(digits).length() == 2) {
			return 
					Double.parseDouble(
				(String.valueOf(digits).substring(0) + "." + String.valueOf(digits).substring(1)));	
		} 
		else {
			return digits;
		}
	}

	/**
	 * Gets the item price.
	 *
	 * @return the item price
	 */
	public int getItemPrice() {
		return 
			JsonPath.read(itemJson, "$.cost");
	}

	/**
	 * Sets the item json.
	 * Used for testing purposes.
	 *
	 * @param itemJson the new item json
	 */
	public void setItemJson(Object itemJson) {
		this.itemJson = itemJson;
	}

	
}
