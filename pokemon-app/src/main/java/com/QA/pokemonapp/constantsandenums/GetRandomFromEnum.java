package com.QA.pokemonapp.constantsandenums;

import java.security.SecureRandom;

public class GetRandomFromEnum {
	private static final SecureRandom random = new SecureRandom();
	
	public static ETerrain generateTerrainType() {
		return randomEnum(ETerrain.class);
	}
	
	public static EPokeball generatePokeballType() {
		return randomEnum(EPokeball.class);
	}
	
	public static EPotion generatePotionType() {
		return randomEnum(EPotion.class);
	}
	
	public static EStatus generateStatusType() {
		return randomEnum(EStatus.class);
	}
	
	private static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
