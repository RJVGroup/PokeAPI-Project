package com.QA.pokemonapp.business.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.QA.pokemonapp.constantsandenums.EPokeball;
import com.QA.pokemonapp.constantsandenums.EPotion;
import com.QA.pokemonapp.interoperability.rest.ItemAPIController;
import com.QA.pokemonapp.persistance.domain.items.Item;
import com.QA.pokemonapp.persistance.domain.items.ItemPokeball;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class ItemGeneratorService implements ItemGeneratorInterface {
	
	@Autowired
	private ItemAPIController itemController;
	
	private Object itemJson;
	
	public ItemGeneratorService() {}
	
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
			JsonPath.read(itemJson, "$.effect_entries.[0].short_effect");
	}
	
	public String getItemLongDescription() {
		return 
				JsonPath.read(itemJson, "$.effect_entries.[0].effect");
	}
	
	public int getCatchRateModifier() {
		
		Random random = new Random();

		Double modifier = 0.0;
		
		switch(getItemName()) {
		case "poke-ball" :
			modifier = getDigitsFromEffect()*random.nextInt(255);
		case "great-ball" :
			modifier = getDigitsFromEffect()*random.nextInt(200);
		case "ultra-ball" :
			modifier = getDigitsFromEffect()*random.nextInt(150);
		case "master-ball" :
			return 0;
		
		}
		return modifier.intValue();
		
	}
	
	
	public int getRestoreAmount() {
		return
			getDigitsFromEffect().intValue();
	}
	
	public Double getDigitsFromEffect() {
		return
				Double.parseDouble(
						getItemDescription().replaceAll("[^0-9?!\\.]","").substring(0, 2));
	}

	public int getItemPrice() {
		return 
			JsonPath.read(itemJson, "$.cost");
	}

}
