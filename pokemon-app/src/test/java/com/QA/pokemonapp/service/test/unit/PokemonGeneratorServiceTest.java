package com.QA.pokemonapp.service.test.unit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.QA.pokemonapp.business.service.PokemonGeneratorService;
import com.QA.pokemonapp.business.service.move.MoveInterface;
import com.QA.pokemonapp.interoperability.rest.PokemonPokeAPIController;
import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;


public class PokemonGeneratorServiceTest {

	@InjectMocks
	PokemonGeneratorService pokemonGeneratorService;
	
	@Mock
	private PokemonPokeAPIController pokemonController;
	
	@Mock
	private MoveInterface moveService;
	
	private Object pokemonJson;
	
	@Before
    public void init() throws FileNotFoundException {
        MockitoAnnotations.initMocks(this);
	
        Gson gson = new Gson();
        FileInputStream inputStream = new FileInputStream("C:\\Users\\bbtvi\\Documents\\#Work_LYF\\QA\\Pokemon\\pokemon-app\\src\\test\\java\\mockPokemon.txt");
        String data = inputStream.toString();
        
		pokemonJson = Configuration.defaultConfiguration().jsonProvider().parse(data);
		
	}
	
	@Test
	public void getIdTest() {
		int result = pokemonGeneratorService.getId();
		
		assertThat(result, is((int)JsonPath.read(pokemonJson, "$.id")));
	}
}
