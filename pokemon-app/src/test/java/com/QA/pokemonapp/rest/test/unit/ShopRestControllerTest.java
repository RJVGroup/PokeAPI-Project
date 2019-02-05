package com.QA.pokemonapp.rest.test.unit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.QA.pokemonapp.business.service.item.ShopService;
import com.QA.pokemonapp.interoperability.rest.item.ShopRestController;
import com.QA.pokemonapp.persistance.domain.Player;
import com.QA.pokemonapp.persistance.domain.items.Item;

public class ShopRestControllerTest {

	@InjectMocks
	private ShopRestController shopRestController;
	
	@Mock
	private ShopService shopService;
	
	@Mock
	private Player player;
	
	private List<Item> shop;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
		Item item = new Item("item", 1, "description", 50);
		shop  = new ArrayList<Item>();
		shop.add(item);
	}
	
	@Test
	public void createShopTest() {
		when(shopService.createShop()).thenReturn(shop);
		
		List<Item> result = shopRestController.createShop();
		
		assertThat(result, is(shop));
	}
	
	@Test
	public void getInventoryTest() {
		when(shopService.getInventory()).thenReturn(shop);
		
		List<Item> result = shopRestController.getInventory();
		
		assertThat(result, is(shop));
	}
	
	@Test
	public void buyItemTest() {
		Player player = new Player(5000, null, null);
		
		when(shopService.buyItem(0, player)).thenReturn(false);
		
		boolean result = shopRestController.buyItem(0);
		
		assertThat(result, is(false));
	}
	
	@Test
	public void sellItemTest() {
		player = new Player(1000, null, new ArrayList<Item>(shop));
		int sellPrice = 25;
		
		when(shopService.sellItem(0, player)).thenReturn(sellPrice);
		
		int result = shopRestController.sellItem(0);
		
		assertThat(result, is(sellPrice));
	}
}
