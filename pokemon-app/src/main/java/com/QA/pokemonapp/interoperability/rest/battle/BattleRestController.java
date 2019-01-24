package com.QA.pokemonapp.interoperability.rest.battle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.BattleManager;
import com.QA.pokemonapp.business.service.move.MoveInterface;
import com.QA.pokemonapp.business.service.player.PlayerService;

@RestController
@RequestMapping("/api/battle")
public class BattleRestController {

	@Autowired
	private BattleManager battleManager;
	
	@Autowired
	private MoveInterface moveService;
	
	@Autowired
	private PlayerService playerService;
	
	@PostMapping(value = "/test/{chosenMove}/{targetSelf}")
	@ResponseBody
	public int test(@RequestBody String payload, @PathVariable String chosenMove, @PathVariable boolean targetSelf) {
		
		return
		battleManager.takeATurn(
			battleManager.getPokemonFromResponseJson(payload, true),
			moveService.createMove(chosenMove),
			targetSelf,
			battleManager.getPokemonFromResponseJson(payload, false));
	}
	
	@PostMapping(value = "/turn/{chosenPokemon}/{chosenMove}/{targetSelf}")
	@ResponseBody
	public int takeTurn(@RequestBody String payload, @PathVariable String chosenPokemon, @PathVariable String chosenMove, @PathVariable boolean targetSelf) {
		
		return
				battleManager.takeATurn(
						playerService.getParty().get(
								playerService.getParty().indexOf(chosenPokemon)),
						moveService.createMove(chosenMove),
						targetSelf,
						battleManager.getPokemonFromResponseJson(payload, false));
	}
	
}
