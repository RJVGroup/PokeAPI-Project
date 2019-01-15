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

public class Pokemon {
	
	private String name;
	private String type;
	private String status = null;
	
	private int XPGiven;
	private int XP;
	private int level;
	private int currentHP;

	/**
   * The following three arrays contain stat information for each pokemon
   * in the following format
   * @param statList[0] This is the HP
   * @param statList[1] This is the Attack
   * @param statList[2] This is the Defence
   * @param statList[3] This is the Special Attack
   * @param statList[4] This is the Special Defence
   * @param statList[5] This is the Speed
   */
	private int[] statList = new int[5];
	private int[] baseStat = new int[5];
	private int[] IV = new int[5];
	
	private Set<Move> moveList;
	
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

	public void setStatList(int[] statList) {
		this.statList = statList;
	}

	public List<Move> getMoveList() {
		return moveList;
	}

	public void setMoveList(List<Move> moveList) {
		this.moveList = moveList;
	}
	
}
