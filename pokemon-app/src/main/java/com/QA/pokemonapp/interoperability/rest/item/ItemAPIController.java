package com.QA.pokemonapp.interoperability.rest.item;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.constantsandenums.EPokeball;
import com.QA.pokemonapp.constantsandenums.EPotion;

@Controller
public class ItemAPIController {

	@Autowired
	private RestTemplate restTemplate;
	
	public String getItem(EPokeball item)
	{
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		return restTemplate.exchange(
	    		item.getAPIAddress(), HttpMethod.GET, entity, String.class).getBody();
	}
	
	public String getItem(EPotion item)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity <String> entity = new HttpEntity<String>(headers);
		
		return restTemplate.exchange(
				item.getAPIAddress(), HttpMethod.GET, entity, String.class).getBody();
	}
	
	
}
