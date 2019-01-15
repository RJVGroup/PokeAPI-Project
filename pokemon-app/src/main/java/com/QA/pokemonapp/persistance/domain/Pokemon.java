/**
* <h1>Pokemon Class<h1>
* This pokemon class contains the variables and methods which will be used 
* for a pokemon object
*
* @author  Vincent Yeadon
* @version 1.0
* @since   2019-01-15 
*/

package com.QA.pokemonapp.persistance.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pokemon {
	
	private String name;
	private List<String> types;
	
	private String status = null;
	
	@JsonProperty(value="base_experience")
	private int XPGiven;
	private int XP;
	private int level;
	private int currentHP;
	private int catchRate;

	/**
   * The following three arrays contain stat information for each pokemon
   * in the following format
   * @param statList[0] This is the Speed
   * @param statList[1] This is the Special Defence
   * @param statList[2] This is the Special Attack
   * @param statList[3] This is the Defence
   * @param statList[4] This is the Attack
   * @param statList[5] This is the HP
   */
	private int[] statList = new int[5];
	private int[] baseStat = new int[5];
	private int[] IV = new int[5];
	
	private Set<String> moveList;
	
	public Pokemon() {}
	
	public Pokemon(String name, List<String> types, int xPGiven, int level,
			int catchRate, int[] statList, int[] baseStat, int[] iV, Set<String> moveList) {
		super();
		this.name = name;
		this.types = types;
		this.status = null;
		XPGiven = xPGiven;
		XP = (int) Math.pow((level + 1), 3);
		this.level = level;
		this.currentHP = statList[5];
		this.catchRate = catchRate;
		this.statList = statList;
		this.baseStat = baseStat;
		IV = iV;
		this.moveList = moveList;
	}



	public void checkIfLevelUp(int currentLevel, int XP)
	{
		if(XP >= Math.pow((currentLevel + 1), 3))
		{
			levelUp();
			this.level += 1;
		}
	}
	
	public void levelUp()
	{
		for(int i=0; i<6; i++)
		{
			this.statList[i] = this.statList[i]*(this.baseStat[i]/50 + this.IV[i]/100);
		}
	}
	
	public void useItem(String item)
	{
		
	}
	
	public int takeDamage(int baseDamage)
	{
		
	}
	
	public int takeDamageByPercentage(double d)
	{
		
	}
	
	public int getHP()
	{
		return statList[5];
	}
	public int setHP()
	{
		return statList[5];
	}
	
	public int getAttack()
	{
		return statList[4];
	}
	
	public int setAttack()
	{
		return statList[4];
	}
	
	public int getDefence()
	{
		return statList[3];
	}
	public int setDefence()
	{
		return statList[3];
	}
	
	public int getSpAttack()
	{
		return statList[2];
	}
	public int setSpAttack()
	{
		return statList[2];
	}
	
	public int getSpDefence()
	{
		return statList[1];
	}
	public int setSpDefence()
	{
		return statList[1];
	}
	
	public int getSpeed()
	{
		return statList[0];
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

	public void setStatList(int[] statList) {
		this.statList = statList;
	}

	public List<Move> getMoveList() {
		return moveList;
	}

	public void setMoveList(List<Move> moveList) {
		this.moveList = moveList;
	}

	public int getCurrentHP() {
		return this.currentHP;
	}
	
	public void setCurrentHP(int newHP) {
		this.currentHP = newHP;
	}

	public int getCatchRate() {
		return this.catchRate;
	}
	
	public void setCatchRate(int catchRate) {
		this.catchRate = catchRate;
	}
	
}
