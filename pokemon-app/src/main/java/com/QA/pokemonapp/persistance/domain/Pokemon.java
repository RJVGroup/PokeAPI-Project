package com.QA.pokemonapp.persistance.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Pokemon {
	
	private String name;
	private String type;
	private String status = null;
	
	private int XPGiven;
	private int XP;
	private int level;
	private int currentHP;
	
	// HP, Attack, Defence, Sp.Atk, Sp.Def, Speed
	private int[] statList = new int[5];
	private int[] baseStat = new int[5];
	private int[] IV = new int[5];
	
	private Set<Move> moveList;
	
	
	public void levelUp()
	{

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
	
	public int getHP()
	{
		return statList[0];
	}
	
	public int getAttack()
	{
		return statList[1];
	}
	
	public int getDefence()
	{
		return statList[2];
	}
	
	public int getSpAttack()
	{
		return statList[3];
	}
	
	public int getSpDefence()
	{
		return statList[4];
	}
	
	public int getSpeed()
	{
		return statList[5];
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
		this.status = newStatus;
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
