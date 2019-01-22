package com.QA.pokemonapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.business.service.ItemGeneratorInterface;
import com.QA.pokemonapp.business.service.ItemGeneratorService;

@SpringBootApplication
@EnableCaching
public class PokemonAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonAppApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ItemGeneratorInterface getItemGeneratorInterface() {
		return new ItemGeneratorService();
	}

	
	
}
