package com.QA.pokemonapp.persistance.domain.items;

public class ItemPokeball extends Item{
	private Double catchModifier;

	public ItemPokeball(String itemName, int itemID, String itemDescription, int itemPrice, Double inCatchModifier) {
		super(itemName, itemID, itemDescription, itemPrice);
		this.catchModifier = inCatchModifier;
	}

	public Double getCatchModifier() {
		return catchModifier;
	}

	public void setCatchModifier(Double catchModifier) {
		this.catchModifier = catchModifier;
	}

}
