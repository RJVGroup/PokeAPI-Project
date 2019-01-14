package com.QA.pokemonapp.persistance.domain;

public class StatusPoison implements Status{
	static String statusName = "Poison";
	@Override
	public boolean noAttackThisTurn() {
		return false;
	}

	@Override
	public void endOfTurnEffect(Pokemon affected) {
		affected.takeDamageByPercentage(12.5);
	}
}
