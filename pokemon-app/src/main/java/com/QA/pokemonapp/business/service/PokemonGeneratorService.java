/**
* <h1>PokemonGeneratorService Class<h1>
* This PokemonGeneratorService class controls the creation on new pokemon. 
* This class extends an interface to enable caching of pokemon.
* The main method of data extraction uses the JsonUnmarshaller
* @see https://github.com/elasticpath/json-unmarshaller
* for more information
*
* @author  Vincent Yeadon
* @version 1.0
* @since   2019-01-17 
*/

package com.QA.pokemonapp.business.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.business.service.move.MoveInterface;
import com.QA.pokemonapp.constantsandenums.ETypes;
import com.QA.pokemonapp.interoperability.rest.PokemonPokeAPIController;
import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.Move;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@Service
public class PokemonGeneratorService implements PokemonGeneratorInterface{

	@Autowired
	private PokemonPokeAPIController pokemonController;
	
	@Autowired
	private MoveInterface moveService;
	
	@Autowired
	private EnemyPokemon enemyPokemon;
	
	private Object pokemonJson;
	private Object pokemonSpeciesJson;
	
   /**
   * The following three arrays contain stat information for each pokemon
   * in the following format
   * @param statList[0] This is the Speed
   * @param statList[1] This is the Special Defence
   * @param statList[2] This is the Special Attack
   * @param statList[3] This is the Defence
   * @param statList[4] This is the Attack
   * @param statList[5] This is the HP
   */
	private int[] iVList = new int[6];
	private int[] baseStatList = new int[6];
	private int[] statList = new int[6];
	
	public PokemonGeneratorService() {
	}

	/**
	 * This method creates a pokemon based on its level and name.
	 * Multiple methods are called in this method to get each line of information.
	 * This method uses the cacheable annotation to save 
	 * @param level is used to generate the different pokemon stats
	 * @param name is used to dictate which pokemon type to generate
	 */
	@Cacheable("pokemon")
	public Pokemon createPokemon(int level, String name) 
	{
		getPokemonJson(name);
		
		if(getId() > 151)
			return null;
		
		getBaseStatList();
		getIVList();
		getStatList(level);
		
		return
			new Pokemon(getName(), getId(), getType(), getXPGiven(), level, getCatchRate(),
					statList, baseStatList, iVList, getMoveList(level));
	}
	
	@Cacheable("pokemon")
	public EnemyPokemon createEnemyPokemon(int level, String name) 
	{
		enemyPokemon.setEnemyMon((createPokemon(level, name)));
		return
			enemyPokemon;
	}
	
	/**
	 * This method gets the moves available to learn when a pokemon levels up
	 * @param level the level the pokemon has level up to
	 * @return a list of the moves is returned
	 */
	public void getPokemonJson(String name)
	{
		String pokemonString = pokemonController.getPokemonJsonString(name);
		String pokemonSpeciesString = pokemonController.getPokemonSpeciesJsonString(name);
		
		pokemonJson = Configuration.defaultConfiguration().jsonProvider().parse(pokemonString);
		pokemonSpeciesJson = Configuration.defaultConfiguration().jsonProvider().parse(pokemonSpeciesString);
	}
	
	public int getId() {
		return
			JsonPath.read(pokemonJson, "$.id");
	}
	
	
	public String getName()
	{
		return
			JsonPath.read(pokemonJson, "$.name");
	}
	public List<ETypes> getType()
	{
		List<String> TypeList = 
			JsonPath.read(pokemonJson, "$.types[*].type.name");
		
		return
			TypeList
				.stream()
				.map(type -> ETypes.valueOf(type.toUpperCase()))
				.collect(Collectors.toList());
		
	}
	
	public int getXPGiven()
	{
		return
			JsonPath.read(pokemonJson, "$.base_experience");
	}
	
	public int getCatchRate()
	{
		return
			(Integer)JsonPath.read(pokemonSpeciesJson, "$.capture_rate");
	}
	
	
	public List<Move> getMoveList(int level)
	{
		JSONArray listOfValidMoves = 
		JsonPath.read(pokemonJson, "$.moves[*][?(@.version_group_details[-1].level_learned_at <=" + level + 
												" && @.version_group_details[-1].move_learn_method.name == 'level-up' "
												+ " && @.version_group_details[-1].version_group.name == 'red-blue')]");
		
		List<String> moveList = 
			JsonPath.parse(listOfValidMoves.toJSONString()).read("$[0:4].move.name");
	
		return
				moveList
					.stream()
					.map(move -> moveService.createMove(move))
					.collect(Collectors.toList());
		
	}
	
	public int[] getBaseStatList()
	{
		
		baseStatList[0] = (Integer)JsonPath.read(pokemonJson, "$.stats.[0].base_stat");
		baseStatList[1] = (Integer)JsonPath.read(pokemonJson, "$.stats.[1].base_stat");
		baseStatList[2] = (Integer)JsonPath.read(pokemonJson, "$.stats.[2].base_stat");
		baseStatList[3] = (Integer)JsonPath.read(pokemonJson, "$.stats.[3].base_stat");
		baseStatList[4] = (Integer)JsonPath.read(pokemonJson, "$.stats.[4].base_stat");
		baseStatList[5] = (Integer)JsonPath.read(pokemonJson, "$.stats.[5].base_stat");
		
		return baseStatList;
	}
	
	public int[] getIVList()
	{
		Random rand = new Random();
		
		iVList[0] = rand.nextInt(15) + 1;
		iVList[1] = rand.nextInt(15) + 1;
		iVList[2] = rand.nextInt(15) + 1;
		iVList[3] = rand.nextInt(15) + 1;
		iVList[4] = rand.nextInt(15) + 1;
		iVList[5] = rand.nextInt(15) + 1;
		
		return iVList;
	}
	
	public int[] getStatList(int level)
	{
		
		for(int i=0;i<5;i++)
		{
			statList[i] = (Integer)( ((baseStatList[i]+iVList[i]) *2*level) /100 )+5;
		}
		
		statList[5] = (Integer)( ((baseStatList[5]+iVList[5]) *2*level) /100 )+level+10;	
		
		return statList;
	}
}
