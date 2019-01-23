package com.QA.pokemonapp.constantsandenums;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.type.TypeEffectivenessInterface;
import com.QA.pokemonapp.persistance.domain.TypeEffectivenessChart;

public class TypeEffectivenessChecker {
	
	@Autowired
	private TypeEffectivenessInterface typeService;
	
	public TypeEffectivenessChecker() {}
	
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
