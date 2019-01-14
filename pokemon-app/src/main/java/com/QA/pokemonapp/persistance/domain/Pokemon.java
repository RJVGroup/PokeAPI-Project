package com.QA.pokemonapp.persistance.domain;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
	
	private String name;
	private String type;
	private String status = null;
	
	private int XPGiven;
	private int XP;
	private int level;
	private int baseStat;
	private int IV;
	
	// HP, Attack, Defence, Sp.Atk, Sp.Def, Speed
	private List<Integer> statList = new ArrayList<>();
	
	private List<Move> moveList;
	
	
	public void levelUp()
	{
		for(Integer stat : statList)
		{
			stat = stat + (this.baseStat/50 + this.IV/100);
		}
	}
	
	public void useItem(String item)
	{
		
	}
	
	public int takeDamage(int baseDamage)
	{

	}
	
	public int takeDamageByPercentage(float percentageDamage)
	{
		
	}
	
	public String getName() {
		return name;
	}


	public String getType() {
		return type;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatusCondition(String newStatus)
	{
		if(status == null)
		{
			status = newStatus;
		}
	}

	public int getXPGiven() {
		return XPGiven;
	}
	
	public int getXP() {
		return XP;
	}

	public void setXP(int xP) {
		XP = xP;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getIV() {
		return IV;
	}


	public List<Integer> getStatList() {
		return statList;
	}

	public void setStatList(List<Integer> statList) {
		this.statList = statList;
	}

	public List<Move> getMoveList() {
		return moveList;
	}

	public void setMoveList(List<Move> moveList) {
		this.moveList = moveList;
	}
	
}
