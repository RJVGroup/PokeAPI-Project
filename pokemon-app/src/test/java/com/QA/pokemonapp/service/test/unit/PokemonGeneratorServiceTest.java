package com.QA.pokemonapp.service.test.unit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.QA.pokemonapp.business.service.PokemonGeneratorService;
import com.QA.pokemonapp.business.service.move.MoveInterface;
import com.QA.pokemonapp.constantsandenums.ETypes;
import com.QA.pokemonapp.interoperability.rest.PokemonPokeAPIController;
import com.QA.pokemonapp.persistance.domain.Move;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;


public class PokemonGeneratorServiceTest {

	@InjectMocks
	PokemonGeneratorService pokemonGeneratorService;
	
	@Mock
	private PokemonPokeAPIController pokemonController;
	
	@Mock
	private MoveInterface moveService;
	
	@Mock
	private Object pokemonJson;
	@Mock
	private Object pokemonSpeciesJson;
	
	@Before
    public void init() throws FileNotFoundException, JSONException {
        MockitoAnnotations.initMocks(this);
	
        InputStream inputStream = new FileInputStream("C:\\Users\\bbtvi\\Documents\\#Work_LYF\\QA\\Pokemon\\pokemon-app\\src\\test\\java\\mockPokemon.txt");
        JsonElement element = new JsonParser().parse(new InputStreamReader(inputStream));
		pokemonJson = Configuration.defaultConfiguration().jsonProvider().parse(element.getAsJsonObject().toString());
		
		pokemonGeneratorService.setPokemonJson(pokemonJson);
		
		InputStream inputStream1 = new FileInputStream("C:\\Users\\bbtvi\\Documents\\#Work_LYF\\QA\\Pokemon\\pokemon-app\\src\\test\\java\\mockPokemonSpecies.txt");
		JsonElement element1 = new JsonParser().parse(new InputStreamReader(inputStream1));
		pokemonSpeciesJson = Configuration.defaultConfiguration().jsonProvider().parse(element1.getAsJsonObject().toString());
		
		pokemonGeneratorService.setPokemonSpeciesJson(pokemonSpeciesJson);
	}
	
	@Test
	public void getIdTest() {
		int result = pokemonGeneratorService.getId();
		
		assertThat(result, is((int)JsonPath.read(pokemonJson, "$.id")));
	}
	
	@Test
	public void getNameTest() {
		String result = pokemonGeneratorService.getName();
		
		assertThat(result, is((String)JsonPath.read(pokemonJson, "$.name")));
	}
	
	@Test
	public void getTypeTest() {
		int testIndex = 0;
		
		List<ETypes> result = pokemonGeneratorService.getType();
		List<String> actual = JsonPath.read(pokemonJson, "$.types[*].type.name");
		
		assertTrue(result.get(testIndex).name()
				.equalsIgnoreCase(actual.get(testIndex)));
	}

	@Test
	public void getXPGivenTest() {
		int result = pokemonGeneratorService.getXPGiven();
		
		assertThat(result, is((int)JsonPath.read(pokemonJson, "$.base_experience")));
	}
	
	@Test
	public void getCatchRateTest() {
		int result = pokemonGeneratorService.getCatchRate();
		
		assertThat(result, is((int)JsonPath.read(pokemonSpeciesJson, "$.capture_rate")));
	}
	
	@Test
	public void getMoveListTest() {
		int testIndex = 0;
		int level = 5;
		
		List<Move> result = pokemonGeneratorService.getMoveList(level);
		
		JSONArray actualList = JsonPath.read(pokemonJson, "$.moves[*][?(@.version_group_details[-1].level_learned_at <=" + level + 
				" && @.version_group_details[-1].move_learn_method.name == 'level-up' "
				+ " && @.version_group_details[-1].version_group.name == 'red-blue')]");
		List<String> actual = JsonPath.parse(actualList.toJSONString()).read("$[0:4].move.name");

		
		assertThat(result.get(testIndex).getMoveName(), is(actual.get(testIndex)));
	}
	

	@Test
	public void createBaseStatListTest() {
		int testIndex = 0;
		
		int[] result = pokemonGeneratorService.createBaseStatList();
		
		assertThat(result[testIndex], is((int)JsonPath.read(pokemonJson, "$.stats.[" + testIndex + "].base_stat")));
	}
	
	@Test
	public void createIVListTest() {
		int testIndex = 0;
		
		int[] result = pokemonGeneratorService.createIVList();
		
		assertTrue(result[testIndex] < 15);
	}
	
	@Test
	public void createStatListTest() {
		int testIndex = 5;
		int level = 5;
		int[] testStatList = {1,1,1,1,1,1};
		
		pokemonGeneratorService.setiVList(testStatList);
		pokemonGeneratorService.setBaseStatList(testStatList);
		
		int[] result = pokemonGeneratorService.createStatList(level);
		
		int actual = (Integer)( ((pokemonGeneratorService.getBaseStatList()[testIndex]+pokemonGeneratorService.getiVList()[testIndex]) *2*level) /100 )+level+10;
		
		assertThat(result[testIndex], is(actual));
	}
}
