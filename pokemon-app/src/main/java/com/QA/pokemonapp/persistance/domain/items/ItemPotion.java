package com.QA.pokemonapp.persistance.domain.items;

import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.Pokemon;

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
	@Override
	public boolean useItem(Pokemon target) {
		if (target.getStatus() == EStatus.FAINT.getDetails()) {
			return false;
		}
		
		int newHealth = Math.min(target.getCurrentHP() + restoreAmount,target.getHP());
		target.setCurrentHP(newHealth);
		Player.removeFromBag(this);
		return true;
	}

}
