package com.bgu.assignment3.actions;

import java.util.ArrayList;

import com.bgu.assignment3.passives.Ingredient;
import com.bgu.assignment3.passives.KitchenTool;
import com.bgu.assignment3.passives.OrderOfDish;
import com.bgu.assignment3.passives.Warehouse;

public class RunnableCookOneDish implements Runnable {
	
	OrderOfDish orderOfDishToCook;
	Warehouse warehouseRef;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<KitchenTool> kitchenTool;

	public void run() {
		
	}
	
	public RunnableCookOneDish(OrderOfDish ood, Warehouse wh) {
		this.orderOfDishToCook = ood;
		this.warehouseRef = wh;
		this.ingredients = ood.getDish().getIngredients();
		this.kitchenTool = ood.getDish().getKitchenTools();
	}
	
	public void getNeededIngredientsFromWarehouse() {
		for (int i = 0; i < ingredients.size() ; i++) {
			//warehouseRef.takeIngredient(ingredients.get(i)., quantity)
		}
	}
	
	
	
	

}
