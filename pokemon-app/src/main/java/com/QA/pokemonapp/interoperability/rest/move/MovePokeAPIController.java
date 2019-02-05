package com.QA.pokemonapp.interoperability.rest.move;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.constantsandenums.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class MovePokeAPIController.
 * This class gets the json information for different moves from the PokeAPI.
 */
@Controller
public class MovePokeAPIController {

	/** The rest template. */
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * Gets the move json string.
	 * Sets the headers of the request to accept json data and adds in settings for different web browsers.
	 * The API address comes from constants file which contains elements to construct the PokeAPI address for diffreent API calls.
	 *
	 * @param move the move name
	 * @return the move json string
	 */
	public String getMoveJsonString(String move)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		return restTemplate.exchange(
				Constants.APIRootAddress + Constants.APIMoveAddress + move + "/", HttpMethod.GET, entity, String.class).getBody();	}
}
