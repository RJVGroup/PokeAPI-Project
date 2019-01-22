package com.QA.pokemonapp.interoperability.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.QA.pokemonapp.business.service.ShopService;
import com.QA.pokemonapp.persistance.domain.Shop;

@RestController
@RequestMapping("/api/shop")
public class ShopRestcontroller {
	
	@Autowired
	private ShopService shopService;
	
	@GetMapping("/generate")
	public Shop createShop() {
		return
			shopService.createShop();
	}

}
