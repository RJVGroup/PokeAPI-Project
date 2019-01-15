package com.QA.pokemonapp.persistance.domain;

public class StatusParalysis implements Status{
	static String statusName = "Paralysis";
	@Override
	public boolean noAttackThisTurn() {
		return Math.random() <= 0.25;
	}

	@Override
	public void endOfTurnEffect(Pokemon affected) {
		//Paralysis does nothing at the end of turn...
	}

}
