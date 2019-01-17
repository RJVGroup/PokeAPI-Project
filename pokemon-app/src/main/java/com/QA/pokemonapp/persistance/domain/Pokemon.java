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

import java.util.List;

import com.QA.pokemonapp.constantsandenums.ETypes;
import com.QA.pokemonapp.persistance.domain.status.Status;

public class Pokemon {
	
	private String name;
	private List<ETypes> types;
	
	private Status status = null;
	
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
	private int[] statList = new int[6];
	private int[] baseStatList = new int[6];
	private int[] iVList = new int[6];
	
	private List<String> moveList;
	
	public Pokemon() {}
	
	public Pokemon(String name, List<ETypes> types, int xPGiven, int level,
			int catchRate, int[] statList, int[] baseStat, int[] iV, List<String> moveList) {
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
		this.baseStatList = baseStat;
		this.iVList = iV;
		this.moveList = moveList;
	}
	
	public int[] getBaseStatList() {
		return baseStatList;
	}

	public void setBaseStatList(int[] baseStatList) {
		this.baseStatList = baseStatList;
	}

	public int[] getiVList() {
		return iVList;
	}

	public void setiVList(int[] iVList) {
		this.iVList = iVList;
	}

	public int[] getStatList() {
		return statList;
	}

	public int getHP()
	{
		return statList[5];
	}
	public void setHP(int newHP)
	{
		statList[5] = newHP;
	}
	
	public int getAttack()
	{
		return statList[4];
	}
	
	public int getDefence()
	{
		return statList[3];
	}
	
	public int getSpAttack()
	{
		return statList[2];
	}
	
	public int getSpDefence()
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

	public List<ETypes> getTypes() {
		return types;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatusCondition(Status newStatus)
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

	public List<String> getMoveList() {
		return moveList;
	}

	public void setMoveList(List<String> moveList) {
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
