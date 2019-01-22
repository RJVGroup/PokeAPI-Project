package com.QA.pokemonapp.persistance.domain.items;

import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.Pokemon;

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

	@Override
	public boolean useItem(Pokemon target) {
		double modifiedCatchRate = ((3 * target.getHP() - 2 * target.getCurrentHP()) * target.getCatchRate() * catchModifier)/ 3 * target.getHP();
		modifiedCatchRate = target.getStatus() == null ? modifiedCatchRate : modifiedCatchRate * 1.5;
		double b = Math.round((Math.random() * 255));
		Player.removeFromBag(this);
		return b <= modifiedCatchRate;
	}
}
