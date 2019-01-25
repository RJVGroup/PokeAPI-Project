package com.QA.pokemonapp.business.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.QA.pokemonapp.business.service.item.ItemService;
import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.constantsandenums.TypeEffectivenessChecker;
import com.QA.pokemonapp.persistance.domain.EnemyPokemon;
import com.QA.pokemonapp.persistance.domain.Move;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.Item;
import com.QA.pokemonapp.persistance.domain.items.ItemPokeball;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class BattleManager {
	
	@Autowired
	PokemonGeneratorInterface pokemonGenerator;
	
	@Autowired
	PlayerPokemonService pokemonService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	TypeEffectivenessChecker typeEffectivenessChecker;
	
	@Autowired
	EnemyPokemon enemyPokemon;
	
	private Random rand = new Random();
	
	private Pokemon enemyMon = enemyPokemon.getEnemyMon();
	
	public BattleManager() {}
	
	public Pokemon getPokemonFromResponseJson(String jsonString, boolean playerPokemon) {
		Object responseJson = Configuration.defaultConfiguration().jsonProvider().parse(jsonString);
		
		ObjectMapper mapper = new ObjectMapper();
		
		if(playerPokemon) {
			return 
				mapper.convertValue(
				JsonPath.read(responseJson, "$.pokemon"), Pokemon.class);
		}
		else {
			return
					mapper.convertValue(
				JsonPath.read(responseJson, "$.enemy-pokemon"), Pokemon.class);
		}
	}
	
	public int takeATurn(Pokemon playerMon, Move playerMove, boolean targetSelf) {
		int result = 0; //0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
		int enemyListSize = enemyMon.getMoveList().size();
		if (playerMon.getSpeed() >= enemyMon.getSpeed()) {
			playerUseMove(playerMove, playerMon, enemyMon);
			enemyUseMove(enemyMon.getMoveList().get(rand.nextInt(enemyListSize)), playerMon, enemyMon);
		} else {
			enemyUseMove(enemyMon.getMoveList().get(rand.nextInt(enemyListSize)), playerMon, enemyMon);
			playerUseMove(playerMove, playerMon, enemyMon);
		}
		statusEndOfTurnEffects(playerMon);
		result = checkResult(playerMon);
		return result;
	}
	
	private void statusEndOfTurnEffects(Pokemon playerMon) {
		if (playerMon.getStatus() != null) {
			playerMon.getStatus().getDetails().endOfTurnEffect(playerMon);
			if (playerMon.getCurrentHP() <= 0) {
				playerMon.setStatusCondition(EStatus.FAINT);
			}
		}
		if (enemyMon.getStatus() != null) {
			enemyMon.getStatus().getDetails().endOfTurnEffect(enemyMon);
			if (enemyMon.getCurrentHP() <= 0) {
				enemyMon.setStatusCondition(EStatus.FAINT);
			}
		}
		
	}

	private int checkResult(Pokemon playerMon) {
		int result = 0;
		if (enemyMon.getStatus() == EStatus.FAINT) {
			result = 1;
		}
		if (playerMon.getStatus() == EStatus.FAINT && playerService.getParty().stream().filter(x -> EStatus.FAINT == x.getStatus()).toArray().length > 0) {
			result = 2;
		}
		return result;
	}

	public int takeATurn(Pokemon playerMon, Item playerItem, boolean targetSelf) {
		int result = 0; //0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
		if (targetSelf) {
			itemService.usePotion(playerMon, (ItemPotion) playerItem);
		} else {
			if (itemService.usePokeball(enemyPokemon, (ItemPokeball) playerItem)) {
				playerService.addToParty(enemyMon);
				return 1;
			}
		}
		enemyUseMove(enemyMon.getMoveList().get(rand.nextInt(3)), playerMon, enemyMon);
		statusEndOfTurnEffects(playerMon);
		result = checkResult(playerMon);
		return result;
	}
	
	public int takeATurn(Pokemon playerMon) {
		double chance = (rand.nextInt(4) + 8)/10;
		int result = playerMon.getSpeed() * chance >= enemyMon.getSpeed() ? 3 : 0; //0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
		if (result == 0) {
			enemyUseMove(enemyMon.getMoveList().get(rand.nextInt(3)), playerMon, enemyMon);
		}
		statusEndOfTurnEffects(playerMon);
		result = checkResult(playerMon);
		return result;
	}
	
	private boolean didHit(Move used) {
		return rand.nextInt(100) <= used.getMoveAccuracy();
	}
	
	private void playerUseMove(Move move, Pokemon player, Pokemon enemy) {
		if(didHit(move) && ((player.getStatus() != null && !player.getStatus().getDetails().noAttackThisTurn()) || player.getStatus() == null)) {
			pokemonService.takeDamage(enemy,calculateDamage(move, player, enemy));
			if (enemy.getCurrentHP() <= 0) {
				enemy.setStatusCondition(EStatus.FAINT);
			}
		}
	}
	
	private void enemyUseMove(Move move, Pokemon player, Pokemon enemy) {
		if(didHit(move) && ((enemy.getStatus() != null && !enemy.getStatus().getDetails().noAttackThisTurn()) || enemy.getStatus() == null)) {
			pokemonService.takeDamage(player, calculateDamage(move, enemy, player));
			if (player.getCurrentHP() <= 0) {
				player.setStatusCondition(EStatus.FAINT);
			}
		}
	}
	
	private int calculateDamage(Move move, Pokemon user, Pokemon target) {
		int typeEffectivenessModifier = typeEffectivenessChecker.returnDamageModifier(move.getMoveType(), target.getTypes());
		double sameTypeAttackBonus = user.getTypes().contains(move.getMoveType()) ? 1.5 : 1;
		double random = rand.nextInt(15)/100 + 0.85;
		int critical = rand.nextInt(100) >= 95 ? 2 : 1;
		int attackStat;
		int defenceStat;
		switch (move.getDamageClass()) {
		case PHYSICAL:
			attackStat = user.getAttack();
			defenceStat = target.getDefence();
			break;
		case SPECIAL:
			attackStat = user.getSpAttack();
			defenceStat = target.getSpDefence();
			break;
		default:
			attackStat = 0;
			defenceStat = 0;
			random = 0;
		}
		return (int) Math.round((((((2*user.getLevel())/5 + 2)*move.getMovePower()*(attackStat/defenceStat))/50)+2)*typeEffectivenessModifier*sameTypeAttackBonus*random*critical);
	}
}
