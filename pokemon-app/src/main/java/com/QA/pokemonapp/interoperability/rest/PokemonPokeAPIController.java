/**
* <h1>PokemonController Class<h1>
* This class controls interaction with the PokeAPI, specifccally the retrival of 
* the pokemnon and pokemon species classes
*
* @author  Vincent Yeadon
* @version 1.0
* @since   2019-01-16 
*/

package com.QA.pokemonapp.interoperability.rest;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.constantsandenums.Constants;

@Controller
public class PokemonPokeAPIController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * This method retrieves the pokemon information from the PokeAPI
	 * based on the pokemons name.
	 * @param pokemon name is fed into method as a string
	 * @return The JSON response body is returned as a string to be easily processed in the service PokemonGeneratorService class
	 */
	public String getPokemonJsonString(String pokemon)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		return restTemplate.exchange(
	    		Constants.APIRootAddress + Constants.APIPokemonAddress + pokemon + "/", HttpMethod.GET, entity, String.class).getBody();
	}
	
	/**
	 * This method retrieves the pokemon species inofrmation from the PokeAPI
	 * based on the pokemons name.
	 * @param pokemon name is fed into method as a string
	 * @return The JSON response body is returned as a string to be easily processed in the service PokemonGeneratorService class
	 */
	public String getPokemonSpeciesJsonString(String pokemon)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
	    return restTemplate.exchange(
	    		Constants.APIRootAddress + Constants.APIPokemonSpeciesAddress + pokemon + "/", HttpMethod.GET, entity, String.class).getBody();
	}
}
