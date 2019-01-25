package com.QA.pokemonapp.persistance.domain.items;

public class ItemPotion extends Item{
	private int restoreAmount;
	
	public ItemPotion(String itemName, int itemID, String itemDescription, int itemPrice, int restoreAmount) {
		super(itemName, itemID, itemDescription, itemPrice);
		this.setRestoreAmount(restoreAmount);
	}

	public int getRestoreAmount() {
		return restoreAmount;
	}

	public void setRestoreAmount(int restoreAmount) {
		this.restoreAmount = restoreAmount;
	}


}
