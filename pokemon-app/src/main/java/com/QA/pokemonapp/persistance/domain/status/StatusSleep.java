package com.QA.pokemonapp.persistance.domain.status;

import com.QA.pokemonapp.persistance.domain.Pokemon;

public class StatusSleep implements Status{
	static String statusName = "Sleep";
	@Override
	public boolean noAttackThisTurn() {
		return true;
	}

	@Override
	public void endOfTurnEffect(Pokemon affected) {
		if(Math.random() >= 0.6) {
			affected.setStatusCondition(null);
		}
	}
}
