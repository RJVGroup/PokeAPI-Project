package com.QA.pokemonapp.constantsandenums;

import java.util.List;

public class TypeEffectivenessChecker {
	public static int returnDamageModifier(ETypes attackingType, List<ETypes> defendingType) {
		int damageMultiplier = 1;
		for (ETypes type : defendingType) {
			if(APIManager.getTypeInfo(type).doubleDamage().contains(attackingType)) {
				damageMultiplier *= 2;
			} else if(APIManager.getTypeInfo(type).halfDamage().contains(attackingType)) {
				damageMultiplier /= 2;
			} else if(APIManager.getTypeInfo(type).noDamage().contains(attackingType)) {
				damageMultiplier *= 0;
			}
		}
		return damageMultiplier;
	}
}
