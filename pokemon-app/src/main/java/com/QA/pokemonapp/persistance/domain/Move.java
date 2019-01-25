package com.QA.pokemonapp.persistance.domain;

import com.QA.pokemonapp.constantsandenums.EDamageClass;
import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.constantsandenums.ETypes;

public class Move {
	private String moveName;
	private int movePower;
	private Integer moveAccuracy;
	private EDamageClass damageClass;
	private ETypes moveType;
	private EStatus moveSecondaryEffect;
	private int moveSecondaryChance;
	private Integer maximumNumberOfHits;
	private Integer minimumNumberOfHits;
	private boolean targetSelf;
	
	public Move() {
		
	}

	public Move(String moveName, int movePower, int moveAccuracy, EDamageClass damageClass, ETypes moveType,
			EStatus moveSecondaryEffect, int moveSecondaryChance,
			int maximumNumberOfHits, int minimumNumberOfHits, boolean targetSelf) {
		super();
		this.moveName = moveName;
		this.movePower = movePower;
		this.moveAccuracy = moveAccuracy;
		this.damageClass = damageClass;
		this.moveType = moveType;
		this.moveSecondaryEffect = moveSecondaryEffect;
		this.moveSecondaryChance = moveSecondaryChance;
		this.maximumNumberOfHits = maximumNumberOfHits;
		this.minimumNumberOfHits = minimumNumberOfHits;
		this.targetSelf = targetSelf;
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

	public int getMaximumNumberOfHits() {
		return maximumNumberOfHits;
	}

	public int getMinimumNumberOfHits() {
		return minimumNumberOfHits;
	}

	public boolean isTargetSelf() {
		return targetSelf;
	}

	public void setTargetSelf(boolean targetSelf) {
		this.targetSelf = targetSelf;
	}
	
	
	
}
