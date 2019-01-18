package com.QA.pokemonapp.persistance.domain;

import com.QA.pokemonapp.constantsandenums.EDamageClass;
import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.constantsandenums.ETypes;

public class Move {
	private String moveName;
	private int movePower;
	private int moveAccuracy;
	private EDamageClass damageClass;
	private ETypes moveType;
	private EStatus moveSecondaryEffect;
	private double moveSecondaryChance;
	
	public Move() {
		
	}

	public Move(String moveName, int movePower, int moveAccuracy, EDamageClass damageClass, ETypes moveType,
			EStatus moveSecondaryEffect, double moveSecondaryChance) {
		super();
		this.moveName = moveName;
		this.movePower = movePower;
		this.moveAccuracy = moveAccuracy;
		this.damageClass = damageClass;
		this.moveType = moveType;
		this.moveSecondaryEffect = moveSecondaryEffect;
		this.moveSecondaryChance = moveSecondaryChance;
	}

	public String getMoveName() {
		return moveName;
	}

	public int getMovePower() {
		return movePower;
	}

	public int getMoveAccuracy() {
		return moveAccuracy;
	}

	public EDamageClass getDamageClass() {
		return damageClass;
	}

	public ETypes getMoveType() {
		return moveType;
	}

	public EStatus getMoveSecondaryEffect() {
		return moveSecondaryEffect;
	}

	public double getMoveSecondaryChance() {
		return moveSecondaryChance;
	}
	
	
}
