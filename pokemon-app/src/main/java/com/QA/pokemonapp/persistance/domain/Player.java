package com.QA.pokemonapp.persistance.domain;

import java.util.ArrayList;

import com.QA.pokemonapp.persistance.domain.items.Item;

public class Player {
	private int money;
	private ArrayList<Pokemon> party;
	private ArrayList<Item> bag;
	private int[] position = {0,0};
	
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void addMoney(int income) {
		this.money += income;
	}
	public ArrayList<Pokemon> getParty() {
		return party;
	}
	public void setParty(ArrayList<Pokemon> party) {
		this.party = party;
	}
	
	public boolean addToParty(Pokemon newCatch) {
		if (party.size() < 6) {
			this.party.add(newCatch);
			return true;
		}
		return false;
	}
	public boolean removeFromParty(Pokemon release) {
		if (party.size() > 1) {
			this.party.remove(release);
			return true;
		}
		return false;
		
	}
	public ArrayList<Item> getBag() {
		return bag;
	}
	public void setBag(ArrayList<Item> bag) {
		this.bag = bag;
	}
	
	public boolean addToBag(Item newItem) {
		if (bag.size() < 100) {
			this.bag.add(newItem);
			return true;
		}
		return false;
		
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
	
	public void move(char direction) {
		switch(direction) {
		case  'w':
			this.position[0] += 1;
			break;
		case  'a':
			this.position[1] -= 1;
			break;
		case  's':
			this.position[0] -= 1;
			break;
		case  'd':
			this.position[1] += 1;
			break;
		}
		Tile next = TerrainGenerator.getTile(this.position[0], this.position[1]);
		if (isBattle(next)){
			int averageLevel = this.party.stream().mapToInt(Pokemon::getLevel).sum() / this.party.size();
			String species = APIManager.getSpeciesByTerrainTypeAndLevel(next.terrainType, averageLevel);
			BattleManager.begin(PokemonGenerator(species, averageLevel));
		}
	}
}
