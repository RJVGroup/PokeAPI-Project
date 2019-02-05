package com.QA.pokemonapp.service.test.unit;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.QA.pokemonapp.business.service.item.ItemGeneratorService;
import com.QA.pokemonapp.interoperability.rest.item.ItemAPIController;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class ItemGeneratorServiceTest {

	@InjectMocks
	ItemGeneratorService itemGeneratorService;
	
	@Mock
	private ItemAPIController itemController;
	
	@Mock
	private Object itemJson;
	

	
	@Before
    public void init() throws FileNotFoundException, JSONException {
        MockitoAnnotations.initMocks(this);
	
        InputStream inputStream = new FileInputStream("C:\\Users\\bbtvi\\Documents\\#Work_LYF\\QA\\Pokemon\\pokemon-app\\src\\test\\java\\mockPotion.txt");
        JsonElement element = new JsonParser().parse(new InputStreamReader(inputStream));
        itemJson = Configuration.defaultConfiguration().jsonProvider().parse(element.getAsJsonObject().toString());
		
		itemGeneratorService.setItemJson(itemJson);
	}

	
	@Test
	public void getItemNameTest() {		
		String result = itemGeneratorService.getItemName();
		
		assertThat(result, is((String)JsonPath.read(itemJson, "$.name")));
	}
	
	@Test
	public void getItemIDTest() {
		
		int result = itemGeneratorService.getItemID();
		
		assertThat(result, is((int)JsonPath.read(itemJson, "$.id")));
	}
	
	@Test
	public void getItemDescriptionTest() {
		
		String result = itemGeneratorService.getItemDescription();
		
		assertThat(result, is((String)JsonPath.read(itemJson, "$.effect_entries.[0].effect")));
	}
	
	@Test
	public void getRestoreAmountTest() {
		
		int result = itemGeneratorService.getRestoreAmount();
		
		assertTrue(result>0);
	}
	
	@Test
	public void getDigitsFromEffectTest() {
		
		Double result = itemGeneratorService.getDigitsFromEffect();
		
		assertTrue(result>0);
	}
	
	@Test
	public void getCatchRateModifierTestElse() {
		Double result = itemGeneratorService.getCatchRateModifier();
		
		assertTrue(result<5.0);
		
	}
	
	@Test
	public void getCatchRateModifierTestIf() throws FileNotFoundException {	
		Double result = itemGeneratorService.getCatchRateModifier();
		
		assertTrue(result.toString().substring(1, 1).contains("."));	
	}
	

	@Test
	public void getItemPriceTest() {
		
		int result = itemGeneratorService.getItemPrice();
		
		assertThat(result, is((int)JsonPath.read(itemJson, "$.cost")));
	}
}
