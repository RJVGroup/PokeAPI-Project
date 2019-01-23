package com.QA.pokemonapp;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import com.QA.pokemonapp.business.service.BattleManager;
import com.QA.pokemonapp.business.service.ItemGeneratorInterface;
import com.QA.pokemonapp.business.service.ItemGeneratorService;
import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.business.service.terrain.TerrainGenerator;
import com.QA.pokemonapp.business.service.terrain.TerrainInterface;
import com.QA.pokemonapp.business.service.type.Player;
import com.QA.pokemonapp.constantsandenums.TypeEffectivenessChecker;
import com.QA.pokemonapp.interoperability.rest.type.TypeEffectivenessPokeAPIController;

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
	
	@Bean
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public BattleManager getBattleManager() {
		return new BattleManager();
	}

	@Bean
	public TerrainInterface getTerrainInterface() {
		return new TerrainGenerator();
	}
	
	@Bean
	public PlayerService getPlayerService() {
		return new PlayerService();
	}
	
	@Bean 
	public Player getPlayer() {
		return new Player();
	}
	
	@Bean
	public TypeEffectivenessPokeAPIController getTypeEffectivenessPokeAPIController() {
		return new TypeEffectivenessPokeAPIController();
	}
	
	@Bean
	public TypeEffectivenessChecker getTypeEffectivenessChecker() {
		return new TypeEffectivenessChecker();
	}
	
}
