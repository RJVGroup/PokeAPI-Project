package com.QA.pokemonapp.interoperability.rest.battle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.BattleManager;
import com.QA.pokemonapp.business.service.player.PlayerService;
import com.QA.pokemonapp.persistance.domain.Move;
import com.QA.pokemonapp.persistance.domain.Pokemon;
import com.QA.pokemonapp.persistance.domain.items.ItemPotion;

@RestController
@RequestMapping("/api/battle")
public class BattleRestController {

	@Autowired
	private BattleManager battleManager;
	
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping(value = "/turnM/{chosenPokemon}/{chosenMove}")
	public int takeTurnMove(@PathVariable int chosenPokemon, @PathVariable int chosenMove) {
		Pokemon a = playerService.getParty().get(chosenPokemon);
		Move b = a.getMoveList().get(chosenMove);
		battleManager.setEnemyMon();
		return
				battleManager.takeATurn(
						a,
						b,
						b.isTargetSelf());
	}
	
	@PostMapping(value = "/turnI/{chosenPokemon}/{chosenItem}")
	public int takeTurnItem(@PathVariable int chosenPokemon, @PathVariable int chosenItem) {
		Pokemon a = playerService.getParty().get(chosenPokemon);
		battleManager.setEnemyMon();
		return
				battleManager.takeATurn(
						a,
						playerService.getBag().get(chosenItem),
						playerService.getBag().get(chosenItem).getClass() == ItemPotion.class);
	}
	
	@PostMapping(value = "/turnR/{chosenPokemon}")
	public int takeTurnRun(@PathVariable int chosenPokemon) {
		battleManager.setEnemyMon();
		return
				battleManager.takeATurn(playerService.getParty().get(chosenPokemon));
	}
	
	@GetMapping(value = "/getStatus/{pokemon}")
	public Pokemon[] getMapping(@PathVariable int pokemon) {
		Pokemon[] a = {playerService.getParty().get(pokemon), battleManager.getEnemyMon()};
		return a;
	}
}
