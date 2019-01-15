package com.QA.pokemonapp.persistance.domain.items;

import com.QA.pokemonapp.persistance.domain.Pokemon;

public class ItemPokeball extends Item{
	private int catchModifier;

	public ItemPokeball(String itemName, int itemID, String itemDescription, int itemPrice, int inCatchModifier) {
		super(itemName, itemID, itemDescription, itemPrice);
		this.catchModifier = inCatchModifier;
	}

	public int getCatchModifier() {
		return catchModifier;
	}

	public void setCatchModifier(int catchModifier) {
		this.catchModifier = catchModifier;
	}

	@Override
	public boolean useItem(Pokemon target) {
		double modifiedCatchRate = ((3 * target.getHP() - 2 * target.getCurrentHP()) * target.getCatchRate() * catchModifier)/ 3 * target.getHP();
		modifiedCatchRate = target.getStatus() == null ? modifiedCatchRate : modifiedCatchRate * 1.5;
		double b = Math.round((Math.random() * 255));
		Player.removeItem(this);
		return b >= modifiedCatchRate;
	}
}