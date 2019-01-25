package com.QA.pokemonapp.business.service.terrain;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.business.service.PokemonGeneratorInterface;
import com.QA.pokemonapp.constantsandenums.ETerrain;
import com.QA.pokemonapp.constantsandenums.GetRandomFromEnum;
import com.QA.pokemonapp.interoperability.rest.terrain.TerrainPokeAPIController;
import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.Terrain;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

@Service
public class TerrainGenerator implements TerrainInterface{

	@Autowired
	private TerrainPokeAPIController terrainController;
	@Autowired
	private PokemonGeneratorInterface pokemonService;
	
	private Object terrainJson;
	
	public TerrainGenerator() {}
	
	public Terrain getTerrain(int level) {
		
		getTerrainJson(
				GetRandomFromEnum.generateTerrainType().name());
		
		return
			new Terrain(getTerrainName(), getWildPokemonEncounter(level));
	}
	
	@Cacheable("terrain")
	public void getTerrainJson(String terrain) {
		String itemString = terrainController.getTerrainJsonString(ETerrain.valueOf(terrain));
		terrainJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}
	
	public String getTerrainName() {
		return
			JsonPath.read(terrainJson, "$.name");
	}
	
	public EnemyPokemon getWildPokemonEncounter(int level) {
		List<String> pokemonList = 
				JsonPath.read(terrainJson, "$.pokemon_encounters.[*].pokemon_species.name");
		
		Random random = new Random();
		
		return
			pokemonService.createEnemyPokemon(level,
					pokemonList.get(random.nextInt(pokemonList.size())));
	}
	
}
