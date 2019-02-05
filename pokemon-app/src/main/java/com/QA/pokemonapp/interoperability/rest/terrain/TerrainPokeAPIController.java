package com.QA.pokemonapp.interoperability.rest.terrain;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.constantsandenums.ETerrain;

/**
 * The Class TerrainPokeAPIController.
 * Gets terrain information from the PokeAPI.
 */
@Controller
public class TerrainPokeAPIController {

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Gets the terrain json string.
	 * Sets the headers of the request to accept json data and adds in settings for different web browsers.
	 * The API address comes from enum ETerrain which contains a list of all the terrain types and constructs an address based on the chosen type.
	 *
	 * @param terrain the terrain
	 * @return the terrain json string
	 */
	public String getTerrainJsonString(ETerrain terrain)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		return restTemplate.exchange(
				terrain.getAPIAdress(), HttpMethod.GET, entity, String.class).getBody();	}
}

