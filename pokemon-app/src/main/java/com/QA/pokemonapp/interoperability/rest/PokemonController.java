package com.QA.pokemonapp.interoperability.rest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PokemonController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getPokemonJsonString(String pokemon)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		return restTemplate.exchange(
	    		"https://pokeapi.co/pokemon/" + pokemon, HttpMethod.GET, entity, String.class).getBody();
	}
	

	public String getPokemonSpeciesJsonString(@PathVariable String pokemon)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
	    return restTemplate.exchange(
	    		"https://pokeapi.co/pokemon-species/" + pokemon, HttpMethod.GET, entity, String.class).getBody();
	}
}
