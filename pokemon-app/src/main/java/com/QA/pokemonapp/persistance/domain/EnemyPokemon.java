/**
* <h1>Pokemon Class<h1>
* This pokemon class contains the variables and methods which will be used 
* for a pokemon object
*
* @author  Vincent Yeadon
* @version 1.0
* @since   2019-01-15 
*/

package com.QA.pokemonapp.persistance.domain;

import org.springframework.context.annotation.Scope;

@Scope("session")
public class EnemyPokemon {
	
	private Pokemon enemyMon;
	
	public EnemyPokemon() {
		
	}

	public EnemyPokemon(Pokemon enemyMon) {
		super();
		this.enemyMon = enemyMon;
	}

	public Pokemon getEnemyMon() {
		return enemyMon;
	}

	public void setEnemyMon(Pokemon enemyMon) {
		this.enemyMon = enemyMon;
	}
	
}
