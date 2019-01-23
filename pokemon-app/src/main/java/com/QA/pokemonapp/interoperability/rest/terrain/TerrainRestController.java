package com.QA.pokemonapp.interoperability.rest.terrain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.business.service.terrain.TerrainInterface;
import com.QA.pokemonapp.persistance.domain.Terrain;

@RestController
@RequestMapping("/api/terrain")
public class TerrainRestController {

	@Autowired
	private TerrainInterface terrainGenerator;
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/generate/{levelOfPokemon}")
	public Terrain getTerrain(@PathVariable int levelOfPokemon) {
		return
			terrainGenerator.getTerrain(levelOfPokemon);
	}
	
	
}
