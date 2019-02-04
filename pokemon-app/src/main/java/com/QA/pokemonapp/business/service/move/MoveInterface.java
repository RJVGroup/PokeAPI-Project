package com.QA.pokemonapp.business.service.move;

import org.springframework.stereotype.Service;

import com.QA.pokemonapp.persistance.domain.Move;

@Service
public interface MoveInterface {

	Move createMove(String name);
}
