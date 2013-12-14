package com.bgu.assignment3;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WarehouseTest {

	Warehouse wareHouse;

	@Before
	public void setUp() throws Exception {
		this.wareHouse = new Warehouse();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void TestTakeIngredient() {
		assertEquals(true, )
	}

	@Test
	public void addKitchenTool() {
		String tool1 = "kt1";
		String tool2 = "kt2";
		int q1 = 5;
		int q2 = 4;
		
		wareHouse.addKitchenTool(tool1, q1);
		wareHouse.addKitchenTool(tool2, q2);
		
		
		assertEquals(q1, wareHouse.getIngredientQuantity(tool1));
		assertEquals(q2, wareHouse.getIngredientQuantity(tool2));

	}
	@Test
	public void returnKitchenTool() {
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
	
	@Test
	public void getKitchenToolQuantity() {
		
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
	@Test
	public void takeKitchenTool() {


	}
}
