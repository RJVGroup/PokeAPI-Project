package com.QA.pokemonapp.persistance.domain;

import com.QA.pokemonapp.persistance.domain.status.Status;

public class String {
	private String moveName;
	private int moveID;
	private int moveBasePower;
	private int moveBaseAccuracy;
	private String moveDamageClass;
	private int priority;
	private String moveType;
	private int statusEffectChance;
	private Status statusEffect;
	
	public String() {
		
	}
	
	public String(String moveName, int moveID, int moveBasePower, int moveBaseAccuracy, String moveDamageClass,
			int priority, String moveType, int statusEffectChance, Status statusEffect) {
		super();
		this.moveName = moveName;
		this.moveID = moveID;
		this.moveBasePower = moveBasePower;
		this.moveBaseAccuracy = moveBaseAccuracy;
		this.moveDamageClass = moveDamageClass;
		this.priority = priority;
		this.moveType = moveType;
		this.statusEffectChance = statusEffectChance;
		this.statusEffect = statusEffect;
	}

	public String getMoveName() {
		return moveName;
	}
	public void setMoveName(String moveName) {
		this.moveName = moveName;
	}
	public int getMoveID() {
		return moveID;
	}
	public void setMoveID(int moveID) {
		this.moveID = moveID;
	}
	public int getMoveBasePower() {
		return moveBasePower;
	}
	public void setMoveBasePower(int moveBasePower) {
		this.moveBasePower = moveBasePower;
	}
	public int getMoveBaseAccuracy() {
		return moveBaseAccuracy;
	}
	public void setMoveBaseAccuracy(int moveBaseAccuracy) {
		this.moveBaseAccuracy = moveBaseAccuracy;
	}
	public String getMoveDamageClass() {
		return moveDamageClass;
	}
	public void setMoveDamageClass(String moveDamageClass) {
		this.moveDamageClass = moveDamageClass;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getMoveType() {
		return moveType;
	}
	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}
	public int getStatusEffectChance() {
		return statusEffectChance;
	}
	public void setStatusEffectChance(int statusEffectChance) {
		this.statusEffectChance = statusEffectChance;
	}
	public Status getStatusEffect() {
		return statusEffect;
	}
	public void setStatusEffect(Status statusEffect) {
		this.statusEffect = statusEffect;
	}
}
