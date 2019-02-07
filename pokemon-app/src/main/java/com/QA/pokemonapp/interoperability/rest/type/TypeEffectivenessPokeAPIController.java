package com.QA.pokemonapp.interoperability.rest.type;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.constantsandenums.ETypes;

/**
 * The Class TypeEffectivenessPokeAPIController.
 * Gets the type effectiveness json from the PokeAPI.
 */
public class TypeEffectivenessPokeAPIController {
	
	/** The rest template. 
	 *  Allows spring to consume rest API's
	 * */
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Gets the type json string.
	 * Sets the headers of the request to accept json data and adds in settings for different web browsers.
	 * The API address comes from enum ETypes which contains a list of all the pokemon types and constructs an address based on the chosen type.
	 *
	 * @param type the type 
	 * @return the type json string
	 */
	public String getTypeJsonString(ETypes type)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		return restTemplate.exchange(
				type.getAPIAddress(), HttpMethod.GET, entity, String.class).getBody();	}

}
