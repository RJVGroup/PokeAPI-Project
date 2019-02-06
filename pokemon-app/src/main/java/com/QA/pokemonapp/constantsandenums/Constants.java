package com.QA.pokemonapp.constantsandenums;

/**
 * The Class Constants.
 * This class holds all the constants needed for interactions with the PokeAPI controller.
 */
public class Constants {
	
	/** The PokeAPI root address.
	 *  All of the PokeAPI requests stem from this base address
	 */
	public final static String APIRootAddress = "https://pokeapi.co/api/v2/";
	
	/** The PokeAPI Pokemon address sufix.
	 *  Used when accessing the api for information about specific pokemon
	 */
	public final static String APIPokemonAddress = "pokemon/";
	
	/** The PokeAPI Pokemon Species suffix. 
	 *  Used when accessing the api for information about specific pokemon species.
	 */
	public final static String APIPokemonSpeciesAddress = "pokemon-species/";
	
	/** The PokeAPI Item suffix.
	 *  Used when accessing the api for information about specific items.
	 */
	public final static String APIItemAddress = "item/";
	
	/** The Constant PokeAPI Type suffix.
	 *  Used when accessing the api for information about specific pokemon types
	 */
	public final static String APITypeAddress = "type/";
	
	/** The Constant PokeAPI Move suffix.
	 *  Used when accessing the api for information on specific pokemon moves.
	 */
	public final static String APIMoveAddress = "move/";
	
	/** The Constant PokeAPI Terrain suffix.
	 *  Used when accessing the api for information on specific terrain types.
	 *  Note only pal park areas were used as they held the simplest terrain types with largest variation of pokemon */
	public final static String APITerrainAddress = "pal-park-area/";
}
