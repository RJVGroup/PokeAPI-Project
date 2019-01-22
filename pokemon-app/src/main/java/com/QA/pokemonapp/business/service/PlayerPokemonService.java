/**
 * <h1>PlayerPokemonService Class<h1>
 * this class is responsible for interactions with pokemon held in the players party
 */

package com.QA.pokemonapp.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.interoperability.rest.PokemonPokeAPIController;
import com.QA.pokemonapp.persistance.domain.Move;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.Item;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@Service
public class PlayerPokemonService {
	
	@Autowired
	private PokemonPokeAPIController pokemonController;
	
	private Object pokemonJson;
	
	/**
	 * This method increases a pokemons XP by a specified amount.
	 * The method then calls another method to see if the pokemon has levelled up
	 * @param pokemon the pokemon gaining XP
	 * @param XPGained the amount of XP gained
	 * @return a boolean is returned dictating whether or not the pokemon has levelled up
	 */
	public boolean gainXP(Pokemon pokemon,int XPGained)
	{
		pokemon.setXP(pokemon.getXP()+XPGained);
		
		return
		checkIfLevelUp(pokemon, pokemon.getLevel(), pokemon.getXP());
	}

	/**
	 * This method compares the current XP against the level curve.
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
	 * so the level to be levelled too is used instead of incrementing the level.
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
	public void addMoveOnLevelUp(Pokemon pokemon, List<Move> currentMoveList, Move moveToBeAdded)
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
	public void addMoveOnLevelUp(Pokemon pokemon, List<Move> currentMoveList, Move moveToBeAdded, Move moveToExchangeWith)
	{
		currentMoveList.remove(moveToExchangeWith);
		currentMoveList.add(moveToBeAdded);
		pokemon.setMoveList(currentMoveList);
	}
	
	/**
	 * This method uses an item on a pokemon. said item has the ability to change the status of the pokemon
	 * @param pokemon
	 * @param item
	 */
	public void useItem(Pokemon pokemon, Item item)
	{
		item.useItem(pokemon);
	}
	
	/**
	 * This method reduces a pokemons health by a specified amount
	 * @param pokemon
	 * @param baseDamage
	 */
	public void takeDamage(Pokemon pokemon, int baseDamage)
	{
		pokemon.setCurrentHP(pokemon.getCurrentHP()-baseDamage);
		
	}
	
	/**
	 * This method reduces a pokemons health by percentage of its maximum HP
	 * @param pokemon
	 * @param damage
	 */
	public void takeDamageByPercentage(Pokemon pokemon, double damage)
	{
		pokemon.setCurrentHP((int)(pokemon.getCurrentHP()-pokemon.getCurrentHP()*damage));

	}
	
	/**
	 * This method downloads the relevant information about the pokemon from the PokeAPI
	 * @param name the name of the pokemons information to be gotten
	 */
	public void getPokemonJson(String name)
	{
		String pokemonString = pokemonController.getPokemonJsonString(name);
		
		pokemonJson = Configuration.defaultConfiguration().jsonProvider().parse(pokemonString);
	}

	/**
	 * This method gets the moves available to learn when a pokemon levels up
	 * @param level the level the pokemon has level up to
	 * @return a list of the moves is returned
	 */
	public List<String> getMoveListForLevelUp(int level)
	{
		JSONArray listOfValidMoves = 
				JsonPath.read(pokemonJson, "$.moves[*][?(@.version_group_details[0].level_learned_at =" + level + 
						" && @.version_group_details[0].move_learn_method.name == 'level-up')]");
		
		return
				JsonPath.parse(listOfValidMoves.toJSONString()).read("$[*].move.name");
	}
}
