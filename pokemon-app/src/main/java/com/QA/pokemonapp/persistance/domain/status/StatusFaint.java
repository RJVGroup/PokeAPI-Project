package com.QA.pokemonapp.persistance.domain.status;

import com.QA.pokemonapp.persistance.domain.Pokemon;

public class StatusFaint implements Status{
	static String statusName = "Faint";
	@Override
	public boolean noAttackThisTurn() {
		return true;
	}

	@Override
	public void endOfTurnEffect(Pokemon affected) {
		if(Math.random() >= 0.9) {
			affected.setStatusCondition(null);
		}
	}
}
