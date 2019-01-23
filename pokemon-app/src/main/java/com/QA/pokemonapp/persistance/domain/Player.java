package com.QA.pokemonapp.persistance.domain;

import java.util.ArrayList;

import com.QA.pokemonapp.persistance.domain.items.Item;

public class Player {
	private int money = 0;
	private ArrayList<Pokemon> party = new ArrayList<Pokemon>();
	private ArrayList<Item> bag = new ArrayList<Item>();
	private int[] position = {0,0};
	
	public Player(){}
	
	public Player(int money, ArrayList<Pokemon> party, ArrayList<Item> bag, int[] position) {
		super();
		this.money = money;
		this.party = party;
		this.bag = bag;
		this.position = position;
	}

	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public ArrayList<Pokemon> getParty() {
		return party;
	}
	public void setParty(ArrayList<Pokemon> party) {
		this.party = party;
	}
	
	public ArrayList<Item> getBag() {
		return bag;
	}
	public void setBag(ArrayList<Item> bag) {
		this.bag = bag;
	}
	

	public void removeFromBag(Item drop) {
		this.bag.remove(drop);
	}
	public int[] getPosition() {
		return position;
	}
	public void setPosition(int[] position) {
		this.position = position;
	}
	

}
