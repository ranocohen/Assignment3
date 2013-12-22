package com.bgu.assignment3.actions;

import java.util.ArrayList;

import com.bgu.assignment3.passives.Ingredient;
import com.bgu.assignment3.passives.KitchenTool;
import com.bgu.assignment3.passives.OrderOfDish;
import com.bgu.assignment3.passives.Warehouse;

public class RunnableCookOneDish implements Runnable {

	private OrderOfDish orderOfDishToCook;
	private Warehouse warehouseRef;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<KitchenTool> kitchenTool;
	private RunnableChef chef;
	public void run() {
		
		getNeededIngredientsFromWarehouse();
		getNeededKitchenToolsFromWarehouse();
		
		long timeToSleep = Math.round(orderOfDishToCook.getDish().getCookTime() * chef.getEfficiency());
		try {
			Thread.sleep(timeToSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		returntNeededKitchenToolsToWarehouse();
		
	}

	public RunnableCookOneDish(OrderOfDish ood, Warehouse wh) {
		this.orderOfDishToCook = ood;
		this.warehouseRef = wh;
		this.ingredients = ood.getDish().getIngredients();
		this.kitchenTool = ood.getDish().getKitchenTools();
	}
	/**
	 * Consuming needed ingredients from {@link Warehouse}
	 */
	private void getNeededIngredientsFromWarehouse() {
		for (Ingredient current : ingredients) {
			warehouseRef.takeIngredient(current.getName(),
					current.getQuantity());
		}
	}
	/**
	 * Consuming needed kitchen tools from {@link Warehouse}
	 */
	private void getNeededKitchenToolsFromWarehouse() {
		for (KitchenTool current : kitchenTool) {
			warehouseRef.takeKitchenTool(current.getName(),
					current.getQuantity());
		}
	}
	/**
	 * Returning needed kitchen tools to {@link Warehouse}
	 */
	private void returntNeededKitchenToolsToWarehouse() {
		for (KitchenTool current : kitchenTool) {
			warehouseRef.returnKitchenTool(current.getName(),
					current.getQuantity());
		}
	}
}
