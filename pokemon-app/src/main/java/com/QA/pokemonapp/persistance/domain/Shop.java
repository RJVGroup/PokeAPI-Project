package com.QA.pokemonapp.persistance.domain;

import java.util.List;

import com.QA.pokemonapp.persistance.domain.items.Item;

public class Shop {
	
	List<Item> shopInventory;
	
	public Shop(List<Item> inventory)
	{
		this.shopInventory = inventory;
	}
	
	public List<Item> getShopInventory() {
		return shopInventory;
	}

	public void setShopInventory(List<Item> shopInventory) {
		this.shopInventory = shopInventory;
	}
}

