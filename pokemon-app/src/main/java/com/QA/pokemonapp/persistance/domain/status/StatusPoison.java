package com.QA.pokemonapp.persistance.domain.status;

import com.QA.pokemonapp.persistance.domain.Pokemon;

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
