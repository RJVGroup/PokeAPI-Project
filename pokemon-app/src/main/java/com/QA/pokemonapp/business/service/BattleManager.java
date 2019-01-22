package com.QA.pokemonapp.business.service;

import java.util.Random;

import com.QA.pokemonapp.constantsandenums.EDamageClass;
import com.QA.pokemonapp.constantsandenums.EStatus;
import com.QA.pokemonapp.constantsandenums.TypeEffectivenessChecker;
import com.QA.pokemonapp.persistance.domain.Move;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.Item;

public class BattleManager {
	private Random rand = new Random();
	
	public int takeATurn(Pokemon playerMon, Move playerMove, boolean targetSelf, Pokemon enemyMon) {
		int result = 0; //0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
		if (playerMon.getSpeed() >= enemyMon.getSpeed()) {
			playerUseMove(playerMove, playerMon, enemyMon);
			enemyUseMove(enemyMon.getMoveList().get(rand.nextInt(3)), playerMon, enemyMon);
		} else {
			enemyUseMove(enemyMon.getMoveList().get(rand.nextInt(3)), playerMon, enemyMon);
			playerUseMove(playerMove, playerMon, enemyMon);
		}
		statusEndOfTurnEffects(playerMon, enemyMon);
		result = checkResult(playerMon, enemyMon);
		return result;
	}
	
	private void statusEndOfTurnEffects(Pokemon playerMon, Pokemon enemyMon) {
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

	private int checkResult(Pokemon playerMon, Pokemon enemyMon) {
		int result = 0;
		if (enemyMon.getStatus() == EStatus.FAINT) {
			result = 1;
		}
		if (playerMon.getStatus() == EStatus.FAINT && Player.getParty().stream().filter(x -> EStatus.FAINT == x.getStatus()).toArray().length() > 0) {
			result = 2;
		}
		return result;
	}

	public int takeATurn(Pokemon playerMon, Item playerItem, boolean targetSelf, Pokemon enemyMon) {
		int result = 0; //0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
		enemyUseMove(enemyMon.getMoveList().get(rand.nextInt(3)), playerMon, enemyMon);
		return result;
	}
	
	public int takeATurn(Pokemon playerMon, Pokemon enemyMon) {
		double chance = (rand.nextInt(4) + 8)/10;
		int result = playerMon.getSpeed() * chance >= enemyMon.getSpeed() ? 3 : 0; //0 = ongoing; 1=player victory; 2= enemy victory; 3= runaway
		return result;
	}
	
	private boolean didHit(Move used) {
		return rand.nextInt(100) < used.getMoveAccuracy();
	}
	
	private void playerUseMove(Move move, Pokemon player, Pokemon enemy) {
		if(didHit(move) && ((player.getStatus() != null && !player.getStatus().noAttackThisTurn()) || player.getStatus() == null)) {
			enemy.takeDamage(calculateDamage(move, player, enemy));
			if (enemy.getCurrentHP() <= 0) {
				enemy.setStatusCondition(EStatus.FAINT);
			}
		}
	}
	
	private void enemyUseMove(Move move, Pokemon player, Pokemon enemy) {
		if(didHit(move) && ((enemy.getStatus() != null && !enemy.getStatus().getDetails().noAttackThisTurn()) || enemy.getStatus() == null)) {
			player.takeDamage(calculateDamage(move, enemy, player));
			if (player.getCurrentHP() <= 0) {
				player.setStatusCondition(EStatus.FAINT);
			}
		}
	}
	
	private int calculateDamage(Move move, Pokemon user, Pokemon target) {
		int typeEffectivenessModifier = TypeEffectivenessChecker.returnDamageModifier(move.getMoveType(), target.getTypes());
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
