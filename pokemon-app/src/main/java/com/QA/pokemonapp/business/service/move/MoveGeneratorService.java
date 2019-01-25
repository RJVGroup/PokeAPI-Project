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

@Service
public class MoveGeneratorService implements MoveInterface {

	@Autowired
	private MovePokeAPIController moveController;
	
	private Object moveJson;
	
	public MoveGeneratorService() {}
	
	@Cacheable("move")
	public Move createMove(String name) {

		getMoveJson(name);
		
		return
			new Move(getName(), getMovePower(), getMoveAccuracy(),
					getDamageClass(), getMoveType(), getMoveSecondaryEffect(),
					getMoveSecondaryChance(), getMaxHits(), getMinHits(), getTargetSelf());
	}

	private boolean getTargetSelf() {
		return JsonPath.read(moveJson, "$.target.name").toString().contains("user");
	}

	public void getMoveJson(String name)
	{
		String itemString = moveController.getMoveJsonString(name);
		
		moveJson = Configuration.defaultConfiguration().jsonProvider().parse(itemString);
	}
	
	public String getName() {
		return
			JsonPath.read(moveJson, "$.name");
	}
	
	public int getMovePower() {
		Integer power =
			JsonPath.read(moveJson, "$.power");
		
		if(power == null) {
			return 0;
		}
		
		return power;
	}
	
	public int getMoveAccuracy() {
		Integer accuracy = JsonPath.read(moveJson, "$.accuracy");
		return accuracy == null ? accuracy : 150;
			
	}
	
	public EDamageClass getDamageClass() {
		
		String damageType =
					JsonPath.read(moveJson, "$.damage_class.name");
		return
			EDamageClass.valueOf(damageType.toUpperCase());
	}
	
	public ETypes getMoveType() {
		
		String moveType = 
						JsonPath.read(moveJson, "$.type.name");
		return
			ETypes.valueOf(moveType.toUpperCase());
	}
	
	public EStatus getMoveSecondaryEffect() {
		String secondaryEffect =
				JsonPath.read(moveJson, "$.meta.ailment.name");	
		
		// Handles no status and non volitile
		try {
			return
				EStatus.valueOf(secondaryEffect.toUpperCase());
		} 
		catch (java.lang.IllegalArgumentException e) {
			return null;
		}
	}
	
	public int getMoveSecondaryChance() {
		return
			JsonPath.read(moveJson, "$.meta.ailment_chance");
	}
	
	public int getMaxHits() {
		Integer hits =
			JsonPath.read(moveJson, "$.meta.max_hits");
		
		if(hits == null) {
			return 0;
		}
		
		return hits;
	}
	
	public int getMinHits() {
		Integer hits =
				JsonPath.read(moveJson, "$.meta.min_hits");
			
			if(hits == null) {
				return 0;
			}
			
			return hits;
	}
	
	
}
