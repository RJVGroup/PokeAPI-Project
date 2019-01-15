package com.QA.pokemonapp.persistance.domain;

import com.QA.pokemonapp.persistance.domain.items.Item;
import com.QA.pokemonapp.persistance.domain.Player;
import java.util.ArrayList;
import java.util.Random;

public class Shop {
	
	public void buyItem(Item item){
		Player.addToBag(item);
		Player.addmoney(-1*item.getItemPrice());
	}
	
	public void sellItem(Item item) {
		Player.addMoney(0.5*item.getItemPrice());
		Player.removeFromBag(item);
	}
	
	
}

