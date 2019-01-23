package com.QA.pokemonapp.interoperability.rest.type;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.constantsandenums.ETypes;

public class TypeEffectivenessPokeAPIController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getMoveJsonString(ETypes type)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		return restTemplate.exchange(
				type.getAPIAddress(), HttpMethod.GET, entity, String.class).getBody();	}

}
