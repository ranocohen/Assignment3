package com.bgu.assignment3.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bgu.assignment3.Warehouse;

public class WarehouseTest {

	Warehouse wareHouse;

	@Before
	public void setUp() throws Exception {
		this.wareHouse = new Warehouse();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*ingredient testing*/
	
	//tests new ingredient addition
	@Test
	public void testAddIngredient() {
		
		String ingredient1 = "ing1";
		String ingredient2 = "ing2";
		int q1 = 5;
		int q2 = 4;
		
		wareHouse.addKitchenTool(ingredient1, q1);
		wareHouse.addKitchenTool(ingredient2, q2);
		
		
		assertEquals(q1, wareHouse.getIngredientQuantity(ingredient1));
		assertEquals(q2, wareHouse.getIngredientQuantity(ingredient2));
	}
	
	//tests ingredient being taken
	@Test
	public void testTakeIngredient() {
		String ingredient = "ing1";
		int ingredientsToTake = 2;
		int quantity = 4;

		
		wareHouse.addIngredient(ingredient, quantity);
		wareHouse.takeIngredient(ingredient, ingredientsToTake);
		
		int currentQuantity = wareHouse.getIngredientQuantity(ingredient);
		
		assertTrue( currentQuantity >= 0);
		assertTrue( currentQuantity + ingredientsToTake == quantity);
	}
	
	//tests ingredient quantity
	@Test
	public void testGetIngredientQuantity() {
		String ing1 = "ing1";
		String ing2 = "ing2";
		int q1 = 5;
		int q2 = 3;
		
		wareHouse.addIngredient(ing1, q1);
		wareHouse.addIngredient(ing2, q2);
		
		assertEquals(q1, wareHouse.getKitchenToolQuantity(ing1));
		assertEquals(q2, wareHouse.getKitchenToolQuantity(ing2));
	}

	/*kitchen tool testing*/
	
	//tests new ingredient addition
	@Test
	public void testAddKitchenTool() {
		String tool1 = "kt1";
		String tool2 = "kt2";
		int q1 = 5;
		int q2 = 4;
		
		wareHouse.addKitchenTool(tool1, q1);
		wareHouse.addKitchenTool(tool2, q2);
		
		
		assertEquals(q1, wareHouse.getIngredientQuantity(tool1));
		assertEquals(q2, wareHouse.getIngredientQuantity(tool2));
	}
	
	//tests kitchen tool being taken
	@Test
	public void testTakeKitchenTool() {
		String name = "kt1";
		int quantity = 12;
		int toTake = 4;
		wareHouse.addKitchenTool(name, quantity);
		wareHouse.takeKitchenTool(name, toTake);
		
		int currentQuantity = wareHouse.getKitchenToolQuantity(name);
		
		assertTrue( currentQuantity >= 0);
		assertTrue( currentQuantity + toTake == quantity);


	}
	 	
	//tests kitchen tool being returned
	@Test
	public void testReturnKitchenTool() {
		String name = "kt1";
		int quantity = 6;
		int toTake = 2;
		
		wareHouse.addKitchenTool(name, quantity);
		wareHouse.takeKitchenTool(name, toTake);		
		int prevQuantity = wareHouse.getKitchenToolQuantity(name);
		wareHouse.returnKitchenTool(name, toTake);
		
		assertEquals(true, wareHouse.getKitchenToolQuantity(name) >= 0);
		assertEquals(true, wareHouse.getKitchenToolQuantity(name) - toTake == prevQuantity);

	}
	
	//tests kitchen tool quantity
	@Test
	public void testGetKitchenToolQuantity() {
		
		String tool1 = "kt1";
		String tool2 = "kt2";
		String tool3 = "kt3";
		int q1 = 5;
		int q2 = 4;
		int q3 = 22;
		
		wareHouse.addKitchenTool(tool1, q1);
		wareHouse.addKitchenTool(tool2, q2);
		wareHouse.addKitchenTool(tool3, q3);
		
		assertEquals(q1, wareHouse.getKitchenToolQuantity(tool1));
		assertEquals(q2, wareHouse.getKitchenToolQuantity(tool2));
		assertEquals(q3, wareHouse.getKitchenToolQuantity(tool3));

	}
	
}
