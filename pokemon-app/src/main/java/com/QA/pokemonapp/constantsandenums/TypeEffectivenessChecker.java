package com.QA.pokemonapp.constantsandenums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.type.TypeEffectivenessInterface;
import com.QA.pokemonapp.persistance.domain.TypeEffectivenessChart;

/**
 * The Class TypeEffectivenessChecker.
 * Checks different pokemon types to see how effective a given move will be.
 * A damage multiplier is applied based on the effectiveness.
 */
public class TypeEffectivenessChecker {
	
	/** The type service. */
	@Autowired
	private TypeEffectivenessInterface typeService;
	
	/**
	 * Instantiates a new type effectiveness checker.
	 */
	public TypeEffectivenessChecker() {}
	
	/**
	 * Gets the damage modifier given the attacking and defending pokemon types.
	 *
	 * @param attackingType the attacking pokemon's type
	 * @param defendingType the defending pokemon's type
	 * @return the damage modifier
	 */
	public int returnDamageModifier(ETypes attackingType, List<ETypes> defendingType) {
		
		int damageMultiplier = 1;
		
		TypeEffectivenessChart attackType = typeService.getTypeChart(attackingType);
		
		for (ETypes type : defendingType) {
			if(attackType.doubleDamage().contains(type)) {
				damageMultiplier *= 2;
			} else if(attackType.halfDamage().contains(type)) {
				damageMultiplier /= 2;
			} else if(attackType.noDamage().contains(type)) {
				damageMultiplier *= 0;
			}
		}
		return damageMultiplier;
	}
}
