package com.QA.pokemonapp.persistance.domain;

public class StatusBurn implements Status{
	static String statusName = "Burn";
	@Override
	public boolean noAttackThisTurn() {
		return false;
	}

	@Override
	public void endOfTurnEffect(Pokemon affected) {
		affected.takeDamageByPercentage(6.25);
	}

}
