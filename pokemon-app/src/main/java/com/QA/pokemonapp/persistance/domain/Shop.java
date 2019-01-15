package com.QA.pokemonapp.persistance.domain;

import com.QA.pokemonapp.persistance.domain.items.Item;
import com.QA.pokemonapp.persistance.domain.Player;
import java.util.ArrayList;
import java.util.Random;

public class Shop {
	
	public boolean buyItem(Item item){
		if (Player.getMoney<item.getItemPrice()) {return false;} else {Player.addToBag(item);
		Player.addMoney(-1*item.getItemPrice());return true;}
	}
	
	public void sellItem(Item item) {
		Player.addMoney(0.5*item.getItemPrice());
		Player.removeFromBag(item);
	}
	
	
}

