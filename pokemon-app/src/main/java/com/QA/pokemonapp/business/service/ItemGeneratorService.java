package com.QA.pokemonapp.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.QA.pokemonapp.constantsandenums.EPokeball;
import com.QA.pokemonapp.constantsandenums.EPotion;
import com.QA.pokemonapp.interoperability.rest.ItemAPIController;
import com.QA.pokemonapp.persistance.domain.items.Item;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class ItemGeneratorService implements ItemGeneratorInterface {
	
	@Autowired
	private ItemAPIController itemController;
	
	private Object itemJson;
	
	@Cacheable("item")
	public Item createItem(String itemType, String itemName) {
		
		switch(itemType.toLowerCase()) {
			case "potion" :
				getPotionJson(itemName);
			case "pokeball" :
				getPokebalJson(itemName);
		}
		
		return 
			new Item(getItemName(), getItemID(), getItemDescription(), getItemPrice());
		
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
			JsonPath.read(itemJson, "$.effect_entries.[0].short_effect");
	}
	
	public int getItemPrice() {
		return 
			JsonPath.read(itemJson, "$.cost");
	}

}
