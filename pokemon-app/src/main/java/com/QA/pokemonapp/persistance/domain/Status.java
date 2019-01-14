package com.QA.pokemonapp.persistance.domain;

public interface Status {
	//Interface of Status types for use by collections
	public boolean noAttackThisTurn();
	//returns whether the pokemon can attack this turn (No for freeze/sleep, 75% during Paralysis)
	public void endOfTurnEffect(Pokemon affected);
	//details end of turn effect of status (damage for burn/poison, chance of status removal for sleep/freeze)
}
