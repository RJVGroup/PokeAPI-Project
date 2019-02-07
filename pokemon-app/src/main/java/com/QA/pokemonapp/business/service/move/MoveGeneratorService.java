package com.QA.pokemonapp.business.service.move;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.QA.pokemonapp.constantsandenums.EDamageClass;
import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.constantsandenums.ETypes;
import com.QA.pokemonapp.interoperability.rest.move.MovePokeAPIController;
import com.QA.pokemonapp.persistance.domain.Move;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

/**
 * The Class MoveGeneratorService.
 * This class gets and processes information pertaining to the different moves which each pokemon has.
 */
@Service
public class MoveGeneratorService implements MoveInterface {

	@Autowired
	private MovePokeAPIController moveController;
	
	private Object moveJson;
	
	/**
	 * Instantiates a new move generator service.
	 * Needed for spring bean instantiation.
	 */
	public MoveGeneratorService() {}
	
	/**
	 * Creates a new move to be placed within a Pokemon or EnemyPokemon.
	 * 
	 */
	@Cacheable("move")
	public Move createMove(String name) {

		getMoveJson(name);
		
		return
			new Move(getName(), getMovePower(), getMoveAccuracy(),
					getDamageClass(), getMoveType(), getMoveSecondaryEffect(),
					getMoveSecondaryChance(), getMaxHits(), getMinHits(), getTargetSelf());
	}
	
	/**
	 * Gets the move information json from them PokeAPI.
	 *
	 * @param name the name of the move
	 * @return the move json
	 */
	public void getMoveJson(String name)
	{
		String itemString = moveController.getMoveJsonString(name);
		
		moveJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}

	/**
	 * Gets whether the move targets self.
	 *
	 * @return if the move targets target self
	 */
	public boolean getTargetSelf() {
		return JsonPath.read(moveJson, "$.target.name").toString().contains("user");
	}
	
	/**
	 * Gets the name from the move information json.
	 *
	 * @return the name
	 */
	public String getName() {
		return
			JsonPath.read(moveJson, "$.name");
	}
	
	/**
	 * Gets the move power the move information json.
	 *
	 * @return the move power
	 */
	public int getMovePower() {
		Integer power =
			JsonPath.read(moveJson, "$.power");
		
		if(power == null) {
			return 0;
		}
		
		return power;
	}
	
	/**
	 * Gets the move accuracy the move information json.
	 *
	 * @return the move accuracy
	 */
	public int getMoveAccuracy() {
		Integer accuracy = JsonPath.read(moveJson, "$.accuracy");
		return accuracy == null ? 150 : accuracy;
			
	}
	
	/**
	 * Gets the damage class the move information json.
	 *
	 * @return the damage class as a EDamageClass enum type
	 */
	public EDamageClass getDamageClass() {
		
		String damageType =
					JsonPath.read(moveJson, "$.damage_class.name");
		return
			EDamageClass.valueOf(damageType.toUpperCase());
	}
	
	/**
	 * Gets the move type the move information json.
	 *
	 * @return the move type as an EType enum type
	 */
	public ETypes getMoveType() {
		
		String moveType = 
						JsonPath.read(moveJson, "$.type.name");
		return
			ETypes.valueOf(moveType.toUpperCase());
	}
	
	/**
	 * Gets the move secondary effect the move information json.
	 * Try loop is used as not all status effects have been added to the program for simplicity
	 *
	 * @return the move secondary effect
	 */
	public EStatus getMoveSecondaryEffect() {
		String secondaryEffect =
				JsonPath.read(moveJson, "$.meta.ailment.name");	
		
		try {
			return
				EStatus.valueOf(secondaryEffect.toUpperCase());
		} 
		catch (java.lang.IllegalArgumentException e) {
			return null;
		}
	}
	
	/**
	 * Gets the move secondary chance the move information json.
	 *
	 * @return the move secondary chance
	 */
	public int getMoveSecondaryChance() {
		return
			JsonPath.read(moveJson, "$.meta.ailment_chance");
	}
	
	/**
	 * Gets the max hits the move information json.
	 *
	 * @return the max hits
	 */
	public int getMaxHits() {
		Integer hits =
			JsonPath.read(moveJson, "$.meta.max_hits");
		
		if(hits == null) {
			return 0;
		}
		
		return hits;
	}
	
	/**
	 * Gets the min hits the move information json.
	 *
	 * @return the min hits
	 */
	public int getMinHits() {
		Integer hits =
				JsonPath.read(moveJson, "$.meta.min_hits");
			
			if(hits == null) {
				return 0;
			}
			
			return hits;
	}
	
	
}
