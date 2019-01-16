package com.QA.pokemonapp.business.service;


import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.constantsandenums.ETypes;
import com.QA.pokemonapp.interoperability.rest.PokemonController;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

@Service
public class PokemonGeneratorService {

	@Autowired
	private PokemonController pokemonController;
	
	
	private Object pokemonJson;
	private Object pokemonSpeciesJson;
	
	String pokemonString;
	String pokemonSpeciesString;
	
	private int[] iVList = new int[6];
	private int[] baseStatList = new int[6];
	private int[] statList = new int[6];
	
	public PokemonGeneratorService() {
	}

	public Pokemon createPokemon(int level, String name) 
	{
		getPokemonJson(name);
		
		getBaseStatList();
		getIVList();
		getStatList(level);
		
		return
			new Pokemon(getName(), getType(), getXPGiven(), level, getCatchRate(),
					statList, baseStatList, iVList, getMoveList(level));
	}
	
	public void getPokemonJson(String name)
	{
		String pokemonString = pokemonController.getPokemonJsonString(name);
		String pokemonSpeciesString = pokemonController.getPokemonSpeciesJsonString(name);
		
		pokemonJson = Configuration.defaultConfiguration().jsonProvider().parse(pokemonString);
		pokemonSpeciesJson = Configuration.defaultConfiguration().jsonProvider().parse(pokemonSpeciesString);
	}
	
	
	public String getName()
	{
		return
			JsonPath.read(pokemonJson, "$.name");
	}
	public List<ETypes> getType()
	{
		return 
			JsonPath.read(pokemonJson, "$.types[*].type.name");
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
	
	
	public List<String> getMoveList(int level)
	{
		List<String> listOfValidMoves = 
		JsonPath.read(pokemonJson, "$.moves[*][?(@.version_group_details[0].level_learned_at <=" + level + 
												" && @.version_group_details[0].move_learn_method.name == 'level-up')]");
		
		return
			JsonPath.parse(listOfValidMoves.toString()).read("$[*].move.name[:3]");
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
