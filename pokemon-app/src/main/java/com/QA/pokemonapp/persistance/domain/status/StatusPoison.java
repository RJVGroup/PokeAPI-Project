package com.QA.pokemonapp.persistance.domain.status;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.PlayerPokemonService;
import com.QA.pokemonapp.persistance.domain.Pokemon;

public class StatusPoison implements Status{
	static String statusName = "Poison";
	@Autowired
	private PlayerPokemonService pokemon;
	@Override
	public boolean noAttackThisTurn() {
		return false;
	}

	@Override
	public void endOfTurnEffect(Pokemon affected) {
		pokemon.takeDamageByPercentage(affected, 12.5);
	}
}
