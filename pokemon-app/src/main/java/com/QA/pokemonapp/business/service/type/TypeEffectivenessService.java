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

/**
 * The Class TypeEffectivenessService.
 * This class gets and processes information pertaining to the effectiveness of different pokemon types against each other.
 */
@Service
public class TypeEffectivenessService implements TypeEffectivenessInterface{
	
	/** The type controller. */
	@Autowired
	private TypeEffectivenessPokeAPIController typeController;
	
	/** The pokemon type json. */
	private Object typeJson;
	
	/**
	 * Instantiates a new type effectiveness service.
	 * Used by spring for bean Instantiation.
	 */
	public TypeEffectivenessService() {}
	
	/**
	 * Creates a new type effectiveness chart for a given pokemon's type.
	 * This method uses a string input.
	 * This method is overridden to allow an enum EType input.
	 * Note method is cacheable, allowable by requiring this method in this classes interface.
	 */
	@Cacheable("typeEffectiveness")
	public TypeEffectivenessChart getTypeChart(String type) {
		getTypeJson(type);
		
		return
			new TypeEffectivenessChart(
					ETypes.valueOf(type.toUpperCase())
					, getDoesDoubleDamage(), getDoesHalfDamage(), getDoesNoDamage());
	}
	
	/**
	 * Creates a new type effectiveness chart for a given pokemon's type.
	 * This method uses a an enum EType input.
	 * This method is overridden to allow a string input.
	 * Note method is cacheable, allowable by requiring this method in this classes interface.
	 */
	@Cacheable("typeEffectiveness")
	public TypeEffectivenessChart getTypeChart(ETypes type) {
		getTypeJson(type);
		
		return
				new TypeEffectivenessChart(type,
						getDoesDoubleDamage(), getDoesHalfDamage(), getDoesNoDamage());
	}
	
	/**
	 * Gets the pokemon type json.
	 * Uses the PokeAPI controller to retrieve the type json.
	 * This method uses a string input.
	 * This method is overridden to allow an enum EType input.
	 *
	 * @param type the type name
	 * @return the type information json
	 */
	public void getTypeJson(String type) {
		String typeString = typeController.getTypeJsonString(ETypes.valueOf(type));
		typeJson = Configuration.defaultConfiguration().jsonProvider().parse(typeString);
	}
	
	/**
	 * Gets the pokemon type json.
	 * This method uses a an enum EType input.
	 * This method is overridden to allow a string input.
	 *
	 * @param type the type EType
	 * @return the type information json
	 */
	public void getTypeJson(ETypes type) {
		String typeString = typeController.getTypeJsonString(type);
		typeJson = Configuration.defaultConfiguration().jsonProvider().parse(typeString);
	}
	
	/**
	 * Gets the list of types of which the specified type does double damage from the json.
	 *
	 * @return the list against which this type does double damage
	 */
	public List<ETypes> getDoesDoubleDamage() {
		return
			JsonPath.read(typeJson, "$.damage_relations.double_damage_to.[*].name");
	}
	
	/**
	 * Gets the list of types of which the specified type does half damage from the json.
	 *
	 * @return the list against which this type does half damage
	 */
	public List<ETypes> getDoesHalfDamage() {
		return
				JsonPath.read(typeJson, "$.damage_relations.half_damage_to.[*].name");
	}
	
	/**
	 * Gets the list of types of which the specified type does no damage from the json.
	 *
	 * @return the list against which this type does no damage
	 */
	public List<ETypes> getDoesNoDamage() {
		return
				JsonPath.read(typeJson, "$.damage_relations.no_damage_to.[*].name");
	}

}
