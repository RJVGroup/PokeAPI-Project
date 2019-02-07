package com.QA.pokemonapp.constantsandenums;

import com.QA.pokemonapp.persistance.domain.status.Status;
import com.QA.pokemonapp.persistance.domain.status.StatusBurn;
import com.QA.pokemonapp.persistance.domain.status.StatusFaint;
import com.QA.pokemonapp.persistance.domain.status.StatusFreeze;
import com.QA.pokemonapp.persistance.domain.status.StatusParalysis;
import com.QA.pokemonapp.persistance.domain.status.StatusPoison;
import com.QA.pokemonapp.persistance.domain.status.StatusSleep;

/**
 * The Enum EStatus.
 * Contains the different status effects employed in this program.
 * Used by the battle manager to set effects based upon the different moves used by the pokemon.
 */
public enum EStatus {
	
	/** The freeze status. */
	FREEZE,
	
	/** The burn status. */
	BURN,
	
	/** The poison status. */
	POISON,
	
	/** The paralysis status. */
	PARALYSIS,
	
	/** The sleep status. */
	SLEEP,
	
	/** The faint status. */
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

	/**
	 * Gets the details of the specific status.
	 *
	 * @return the details
	 */
	public Status getDetails() {
		return details;
	}

	/**
	 * Sets the details of the specific status.
	 *
	 * @param details the new details
	 */
	public void setDetails(Status details) {
		this.details = details;
	}
}
