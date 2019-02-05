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
					, getDoesDoubleDamage(), getDoesHalfDamage(), getDoesNoDamage());
	}
	
	@Cacheable("typeEffectiveness")
	public TypeEffectivenessChart getTypeChart(ETypes type) {
		getTypeJson(type);
		
		return
				new TypeEffectivenessChart(type,
						getDoesDoubleDamage(), getDoesHalfDamage(), getDoesNoDamage());
	}
	
	public void getTypeJson(String type) {
		String typeString = typeController.getMoveJsonString(ETypes.valueOf(type));
		typeJson = Configuration.defaultConfiguration().jsonProvider().parse(typeString);
	}
	
	public void getTypeJson(ETypes type) {
		String typeString = typeController.getMoveJsonString(type);
		typeJson = Configuration.defaultConfiguration().jsonProvider().parse(typeString);
	}
	
	public List<ETypes> getDoesDoubleDamage() {
		return
			JsonPath.read(typeJson, "$.damage_relations.double_damage_to.[*].name");
	}
	
	public List<ETypes> getDoesHalfDamage() {
		return
				JsonPath.read(typeJson, "$.damage_relations.half_damage_to.[*].name");
	}
	
	public List<ETypes> getDoesNoDamage() {
		return
				JsonPath.read(typeJson, "$.damage_relations.no_damage_to.[*].name");
	}

}
