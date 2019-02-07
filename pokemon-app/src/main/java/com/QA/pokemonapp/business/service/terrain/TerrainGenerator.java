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

/**
 * The Class TerrainGenerator.
 * This class gets and processes information pertaining to the terrain types and enemy pokemon generation.
 */
@Service
public class TerrainGenerator implements TerrainInterface{

	@Autowired
	private TerrainPokeAPIController terrainController;
	
	@Autowired
	private PokemonGeneratorInterface pokemonService;
	
	private Object terrainJson;
	
	/**
	 * Instantiates a new terrain generator.
	 * Used for spring bean instantiation.
	 */
	public TerrainGenerator() {}
	
	/* Gets a new random terrain and a random wild pokemon.
	 * Wild pokemon are not always generated.
	 */
	public Terrain getTerrain(int level) {
		
		getTerrainJson(
				GetRandomFromEnum.generateTerrainType().name());
		
		return
			new Terrain(getTerrainName(), getWildPokemonEncounter(level));
	}
	
	/* Gets the terrain information json.
	 * Gets the json from thePokeAPI.
	 */
	@Cacheable("terrain")
	public void getTerrainJson(String terrain) {
		String itemString = terrainController.getTerrainJsonString(ETerrain.valueOf(terrain));
		terrainJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}
	
	/**
	 * Gets the terrain name from the terrain information json.
	 *
	 * @return the terrain name
	 */
	public String getTerrainName() {
		return
			JsonPath.read(terrainJson, "$.name");
	}
	
	/**
	 * Gets the wild pokemon encounter.
	 * Gets a list of wild pokemon encounters from the terrain formation json and chooses a random pokemon.
	 * Only pokemon whose id is < 152 is generated, controlled by createEnemyPokemon() method.
	 *
	 * @param level the level
	 * @return the wild pokemon encounter
	 */
	public EnemyPokemon getWildPokemonEncounter(int level) {
		List<String> pokemonList = 
				JsonPath.read(terrainJson, "$.pokemon_encounters.[*].pokemon_species.name");
		
		Random random = new Random();
		
		return
			pokemonService.createEnemyPokemon(level,
					pokemonList.get(random.nextInt(pokemonList.size())));
	}
	
}
