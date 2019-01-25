package com.QA.pokemonapp.persistance.domain.items;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.persistance.domain.Pokemon;

public class Item {
	private String itemName;
	private int itemID;
	private String itemDescription;
	private int itemPrice;
	
	@Autowired
	protected PlayerService player;
	
	public Item() { }
	
	public Item(String itemName, int itemID, String itemDescription, int itemPrice) {
		super();
		this.itemName = itemName;
		this.itemID = itemID;
		this.itemDescription = itemDescription;
		this.itemPrice = itemPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}

}
