package com.QA.pokemonapp.constantsandenums;

import com.QA.pokemonapp.persistance.domain.status.Status;
import com.QA.pokemonapp.persistance.domain.status.StatusBurn;
import com.QA.pokemonapp.persistance.domain.status.StatusFaint;
import com.QA.pokemonapp.persistance.domain.status.StatusFreeze;
import com.QA.pokemonapp.persistance.domain.status.StatusParalysis;
import com.QA.pokemonapp.persistance.domain.status.StatusPoison;
import com.QA.pokemonapp.persistance.domain.status.StatusSleep;

public enum EStatus {
	FREEZE,
	BURN,
	POISON,
	PARALYSIS,
	SLEEP,
	FAINT;
	
	private Status details;
	
	static {
		FREEZE.setDetails(new StatusFreeze());
		BURN.setDetails(new StatusBurn());
		POISON.setDetails(new StatusPoison());
		PARALYSIS.setDetails(new StatusParalysis());
		SLEEP.setDetails(new StatusSleep());
		FAINT.setDetails(new StatusFaint());
	}

	public Status getDetails() {
		return details;
	}

	public void setDetails(Status details) {
		this.details = details;
	}
}
