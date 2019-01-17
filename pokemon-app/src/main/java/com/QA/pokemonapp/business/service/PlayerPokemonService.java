package com.QA.pokemonapp.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.interoperability.rest.PokemonPokeAPIController;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.status.Status;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

public class PlayerPokemonService {
	
	@Autowired
	private PokemonPokeAPIController pokemonController;
	
	private Object pokemonJson;
	private Object pokemonSpeciesJson;
	
	
	public boolean gainXP(Pokemon pokemon,int XPGained)
	{
		pokemon.setXP(pokemon.getXP()+XPGained);
		
		return
		checkIfLevelUp(pokemon, pokemon.getLevel(), pokemon.getXP());
	}

	/**
	 * This method comapares the current XP against the level curve.
	 * If theXP is high enough it calls the level up function
	 * @param currentLevel is the pokemons current level
	 * @param XP is the pokemons current XP
	 * @return 
	 */
	public boolean checkIfLevelUp(Pokemon pokemon, int currentLevel, int XP)
	{
		if(pokemon.getXP() >= Math.pow((currentLevel + 1), 3))
		{
			levelUp(pokemon, (int)Math.cbrt(pokemon.getXP()));
			return true;
		}
		return false;
	}
	
	/**
	 * This method levels a pokemon up to a specified level.
	 * It is possible to gain two levels in a battle 
	 * so the level to be leveled too is used instead of incrementing the level.
	 * After the pokemons stats are increased a check is made to see whether the pokemon
	 * can learn a new move, if it can a boolean value is returned.
	 * @param level is the level to which the pokemon will be changed to
	 */
	public void levelUp(Pokemon pokemon, int level)
	{
		pokemon.setLevel(level);
		
		for(int i=0;i<5;i++)
		{
			pokemon.getStatList()[i] = (Integer)( ((pokemon.getBaseStatList()[i]+pokemon.getiVList()[i]) *2*level) /100 )+5;
		}
		
		pokemon.getStatList()[5] = (Integer)( ((pokemon.getBaseStatList()[5]+pokemon.getiVList()[5]) *2*level) /100 )+level+10;	
		
	}
	
	/**
	 * This method is used to add a new move to the moveList.
	 * @param currentMoveList
	 * @param moveToBeAdded
	 */
	public void addMoveOnLevelUp(Pokemon pokemon, List<String> currentMoveList, String moveToBeAdded)
	{
		currentMoveList.add(moveToBeAdded);
		pokemon.setMoveList(currentMoveList);
	}
	
	/**
	 * This method overloads the previous method and is used to swap out a move
	 * if the chosen pokemon has four moves
	 * @param currentMoveList
	 * @param moveToBeAdded
	 * @param moveToExchangeWith
	 */
	public void addMoveOnLevelUp(Pokemon pokemon, List<String> currentMoveList, String moveToBeAdded, String moveToExchangeWith)
	{
		currentMoveList.remove(moveToExchangeWith);
		currentMoveList.add(moveToBeAdded);
		pokemon.setMoveList(currentMoveList);
	}
	
	public void useItem(Pokemon pokemon, Status item)
	{
		pokemon.setStatusCondition(item);
	}
	
	public void takeDamage(Pokemon pokemon, int baseDamage)
	{
		pokemon.setHP(pokemon.getHP()-baseDamage);
		
	}
	
	public void takeDamageByPercentage(Pokemon pokemon, double damage)
	{
		pokemon.setHP((int)(pokemon.getHP()-pokemon.getHP()*damage));

	}
	
	public void getPokemonJson(String name)
	{
		String pokemonString = pokemonController.getPokemonJsonString(name);
		String pokemonSpeciesString = pokemonController.getPokemonSpeciesJsonString(name);
		
		pokemonJson = Configuration.defaultConfiguration().jsonProvider().parse(pokemonString);
		pokemonSpeciesJson = Configuration.defaultConfiguration().jsonProvider().parse(pokemonSpeciesString);
	}

	public List<String> getMoveListForLevelUp(int level)
	{
		JSONArray listOfValidMoves = 
				JsonPath.read(pokemonJson, "$.moves[*][?(@.version_group_details[0].level_learned_at =" + level + 
						" && @.version_group_details[0].move_learn_method.name == 'level-up')]");
		
		return
				JsonPath.parse(listOfValidMoves.toJSONString()).read("$[*].move.name");
	}
}
