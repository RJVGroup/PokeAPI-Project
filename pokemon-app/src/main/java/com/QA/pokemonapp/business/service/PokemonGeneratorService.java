package com.QA.pokemonapp.business.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.interoperability.rest.PokemonController;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.jayway.jsonpath.JsonPath;

@Service
public class PokemonGeneratorService {

	@Autowired
	private PokemonController pokemonController;
	
	
	private String pokemonJsonString;
	private String pokemonSpeciesjsonString;
	
	private int[] iVList = new int[5];
	private int[] baseStatList = new int[5];
	private int[] statList = new int[5];
	
	public Pokemon createPokemon(int level, String name) 
	{
		pokemonJsonString = pokemonController.getPokemonJsonString(name);
		pokemonSpeciesjsonString = pokemonController.getPokemonSpeciesJsonString(name);
		
		getBaseStatList();
		getIVList();
		getStatList(level);
		
		return
			new Pokemon(getName(), getType(), getXPGiven(), level, getCatchRate(),
					statList, baseStatList, iVList);
	}
	
	public String getName()
	{
		return
			JsonPath.read(pokemonJsonString, "$.name");
	}
	public List<String> getType()
	{
		return 
			JsonPath.read(pokemonJsonString, "$...name");
	}
	
	public int getXPGiven()
	{
		return
			JsonPath.read(pokemonJsonString, "$.base_experience");
	}
	
	public String getCatchRate()
	{
		return
			JsonPath.read(pokemonSpeciesjsonString, "$.capture_rate");
	}
	
	//unsure if correct, test first
	public Set<String> getMoveList()
	{
			JsonPath.read(pokemonJsonString, "$....level_learned_at[?")
	}
	
	public int[] getBaseStatList()
	{
		baseStatList[0] = (Integer)JsonPath.read(pokemonJsonString, "$.stats.0.base_stat");
		baseStatList[1] = (Integer)JsonPath.read(pokemonJsonString, "$.stats.1.base_stat");
		baseStatList[2] = (Integer)JsonPath.read(pokemonJsonString, "$.stats.2.base_stat");
		baseStatList[3] = (Integer)JsonPath.read(pokemonJsonString, "$.stats.3.base_stat");
		baseStatList[4] = (Integer)JsonPath.read(pokemonJsonString, "$.stats.4.base_stat");
		baseStatList[5] = (Integer)JsonPath.read(pokemonJsonString, "$.stats.5.base_stat");
		
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
