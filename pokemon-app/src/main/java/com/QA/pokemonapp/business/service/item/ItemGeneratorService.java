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

public class ItemGeneratorService implements ItemGeneratorInterface {
	
	@Autowired
	private ItemAPIController itemController;
	
	private Object itemJson;
	
	public ItemGeneratorService() {}
	
	@Cacheable("pokeball")
	public ItemPokeball createPokeball(String itemName) {
		
		getPokebalJson(itemName);
		
		return 
			new ItemPokeball(getItemName(), getItemID(), getItemDescription(), getItemPrice(), getCatchRateModifier());
		
	}
	@Cacheable("potion")
	public ItemPotion createPotion(String itemName) {
		
			getPotionJson(itemName);
		
		return 
			new ItemPotion(getItemName(), getItemID(), getItemDescription(), getItemPrice(), getRestoreAmount());
		
	}
	
	public void getPokebalJson(String item) {
		String itemString = itemController.getItem(EPokeball.valueOf(item));
		itemJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}
	public void getPotionJson(String item) {
		String itemString = itemController.getItem(EPotion.valueOf(item));
		itemJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}
	
	public String getItemName() {
		return 
			JsonPath.read(itemJson, "$.name");
	}
	
	public int getItemID() {
		return 
			JsonPath.read(itemJson, "$.id");
	}
	
	public String getItemDescription() {
		return 
			JsonPath.read(itemJson, "$.effect_entries.[0].effect");
	}
	
	public int getRestoreAmount() {
		return
			getDigitsFromEffect().intValue();
	}
	
	public Double getDigitsFromEffect() {
		String digits =
					getItemDescription().replaceAll("\\D+","");
		
		if(digits.isEmpty()) {
			return 1000.0;
		}
			
		return
			Double.parseDouble(digits);
	}
	
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

	public int getItemPrice() {
		return 
			JsonPath.read(itemJson, "$.cost");
	}

	public void setItemJson(Object itemJson) {
		this.itemJson = itemJson;
	}

	
}
