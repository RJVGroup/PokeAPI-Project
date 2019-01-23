package com.QA.pokemonapp.business.service.type;

import com.QA.pokemonapp.constantsandenums.ETypes;
import com.QA.pokemonapp.persistance.domain.TypeEffectivenessChart;

public interface TypeEffectivenessInterface {

	public TypeEffectivenessChart getTypeChart(String type);
	
	public TypeEffectivenessChart getTypeChart(ETypes type);
}
