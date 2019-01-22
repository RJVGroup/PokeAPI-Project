package com.QA.pokemonapp.business.service.terrain;

import com.QA.pokemonapp.persistance.domain.Terrain;

public interface TerrainInterface {
	
	public Terrain getTerrain(int level);
	
	public void getTerrainJson(String terrain);
}
