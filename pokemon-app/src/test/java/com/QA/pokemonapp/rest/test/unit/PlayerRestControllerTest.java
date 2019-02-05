package com.QA.pokemonapp.rest.test.unit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.QA.pokemonapp.business.service.PokemonGeneratorInterface;
import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.interoperability.rest.player.PlayerRestController;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.Terrain;
import com.QA.pokemonapp.persistance.domain.items.Item;

public class PlayerRestControllerTest {

	@InjectMocks
	private PlayerRestController playerRestController;
	
	@Mock
	private PlayerService playerService;
	
	@Mock
	private PokemonGeneratorInterface PokemonGenerator;
	
	private Pokemon pokemon;
	private String pokemonName;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        
		int[] mockStatList = new int[6];
		pokemonName = "charmander";
		pokemon = new Pokemon(pokemonName, 1, null, 50, 5, 100, mockStatList, mockStatList, mockStatList, null);
		
	}
	
	@Test
	public void addStarterPokemonTest() {
		
		when(PokemonGenerator.createPokemon(5, pokemonName)).thenReturn(pokemon);
		when(playerService.addToParty(pokemon)).thenReturn(true);
		
		boolean result = playerRestController.addStarterPokemon(pokemonName);
		
		assert(result);
	}
	
	@Test
	public void addPokemonTest() {
		when(PokemonGenerator.createPokemon(5, pokemonName)).thenReturn(pokemon);
		when(playerService.addToParty(pokemon)).thenReturn(true);
		
		boolean result = playerRestController.addPokemon(pokemonName, 5);
		
		assert(result);
	}
	
	@Test
	public void getMoneyTest() {
		int money = 1000;
		when(playerService.getMoney()).thenReturn(money);
		
		int result = playerRestController.getMoney();
		
		assertThat(result, is(money));
	}
	
	@Test
	public void getPartyTest() {
		List<Pokemon> pokemonList = new ArrayList<Pokemon>();
		pokemonList.add(pokemon);
		
		when(playerService.getParty()).thenReturn(pokemonList);
		
		List<Pokemon> result = playerRestController.getParty();
		
		assertThat(result, is(pokemonList));
	}
	
	@Test
	public void getPokemonTest() {
		int pokemonIndex = 0;
		
		List<Pokemon> pokemonList = new ArrayList<Pokemon>();
		pokemonList.add(pokemon);
		
		when(playerService.getParty()).thenReturn(pokemonList);
	
		Pokemon result = playerRestController.getPokemon(pokemonIndex);
		
		assertThat(result, is(pokemonList.get(pokemonIndex)));
	}
	
	@Test
	public void getBag() {
		Item item = new Item("item", 1, "description", 50);
		List<Item> bag  = new ArrayList<Item>();
		bag.add(item);
		
		when(playerService.getBag()).thenReturn(bag);
		
		List<Item> result = playerRestController.getBag();
		
		assertThat(result, is(bag));
	}
	
	@Test
	public void movePlayerTest() {
		Terrain terrain = new Terrain("type", null);
		
		when(playerService.move()).thenReturn(terrain);
		
		Terrain result = playerRestController.movePlayer();
		
		assertThat(result, is(terrain));
	}
	
}
