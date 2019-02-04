package com.QA.pokemonapp.service.test.unit;

import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.QA.pokemonapp.business.service.PokemonGeneratorService;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PokemonGeneratorServiceTests {

	PokemonGeneratorService pokemonGenerator;

	Gson gson = new Gson();

	@Test
	public void testGetPokemonJson() throws FileNotFoundException {
		
		JsonReader reader = new JsonReader(new FileReader("C:\\Users\\bbtvi\\Documents\\#Work_LYF\\QA\\Pokemon\\pokemon-app\\src\\test\\java\\com\\QA\\pokemonapp\\service\\test\\unit\\mockPokemon.txt\\"));
		
		Object data = gson.fromJson(reader, Object.class);
		
		pokemonGenerator.setPokemonJson(data);

		assertTrue(pokemonGenerator.getPokemonJson() != null);
	}

}