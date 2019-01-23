package com.QA.pokemonapp.business.service.type;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.constantsandenums.ETypes;
import com.QA.pokemonapp.interoperability.rest.type.TypeEffectivenessPokeAPIController;
import com.QA.pokemonapp.persistance.domain.TypeEffectivenessChart;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

@Service
public class TypeEffectivenessService implements TypeEffectivenessInterface{
	
	@Autowired
	private TypeEffectivenessPokeAPIController typeController;
	
	private Object typeJson;
	
	public TypeEffectivenessService() {}
	
	@Cacheable("typeEffectiveness")
	public TypeEffectivenessChart getTypeChart(String type) {
		getTypeJson(type);
		
		return
			new TypeEffectivenessChart(
					ETypes.valueOf(type.toUpperCase())
					, getTakesDoubleDamage(), getTakesHalfDamage(), getTakesNoDamage());
	}
	
	@Cacheable("typeEffectiveness")
	public TypeEffectivenessChart getTypeChart(ETypes type) {
		getTypeJson(type);
		
		return
				new TypeEffectivenessChart(type,
						getTakesDoubleDamage(), getTakesHalfDamage(), getTakesNoDamage());
	}
	
	public void getTypeJson(String type) {
		String typeString = typeController.getMoveJsonString(ETypes.valueOf(type));
		typeJson = Configuration.defaultConfiguration().jsonProvider().parse(typeString);
	}
	
	public void getTypeJson(ETypes type) {
		String typeString = typeController.getMoveJsonString(type);
		typeJson = Configuration.defaultConfiguration().jsonProvider().parse(typeString);
	}
	
	public List<ETypes> getTakesDoubleDamage() {
		return
			JsonPath.read(typeJson, "$.damage_relations.double_damage_from.[*].name");
	}
	
	public List<ETypes> getTakesHalfDamage() {
		return
				JsonPath.read(typeJson, "$.damage_relations.half_damage_from.[*].name");
	}
	
	public List<ETypes> getTakesNoDamage() {
		return
				JsonPath.read(typeJson, "$.damage_relations.no_damage_from.[*].name");
	}

}
