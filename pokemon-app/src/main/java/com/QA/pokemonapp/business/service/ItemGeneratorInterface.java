package com.QA.pokemonapp.business.service;

import com.QA.pokemonapp.persistance.domain.items.Item;

public interface ItemGeneratorInterface {

	Item createPokeball(String itemName);
	
	Item createPotion(String itemName);
}
