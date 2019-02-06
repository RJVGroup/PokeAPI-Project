package com.QA.pokemonapp.constantsandenums;

import java.security.SecureRandom;

/**
 * The Class GetRandomFromEnum.
 * Generates a random enum from the different enum types within this program
 */
public class GetRandomFromEnum {
	
	private static final SecureRandom random = new SecureRandom();
	
	/**
	 * Generates random terrain type.
	 * Used when the players is moving.
	 *
	 * @return the random terrain
	 */
	public static ETerrain generateTerrainType() {
		return randomEnum(ETerrain.class);
	}
	
	/**
	 * Generates a random pokeball type.
	 * Used when items are randomly generated for the shop.
	 *
	 * @return the random pokeball
	 */
	public static EPokeball generatePokeballType() {
		return randomEnum(EPokeball.class);
	}
	
	/**
	 * Generates a random potion type.
	 * Used when items are randomly generated for the shop.
	 *
	 * @return the random potion
	 */
	public static EPotion generatePotionType() {
		return randomEnum(EPotion.class);
	}
	
	/**
	 * Generate status type.
	 *
	 * @return the random status
	 */
	public static EStatus generateStatusType() {
		return randomEnum(EStatus.class);
	}
	
	/**
	 * Generates a random enum from given enum type.
	 *
	 * @param <T> the generic type
	 * @param clazz the enum class
	 * @return the the random enum
	 */
	private static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
