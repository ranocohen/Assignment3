package com.bgu.assignment3.actions;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.bgu.assignment3.passives.Ingredient;
import com.bgu.assignment3.passives.KitchenTool;
import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.OrderOfDish;
import com.bgu.assignment3.passives.Statistics;
import com.bgu.assignment3.passives.Warehouse;

public class RunnableCookOneDish implements Runnable {

	private OrderOfDish orderOfDishToCook;
	private Warehouse warehouseRef;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<KitchenTool> kitchenTool;
	private RunnableChef chef;
	private CountDownLatch latch;

	public void run() {
		long startCook = System.currentTimeMillis();
		//Logger.getLogger(RunnableCookOneDish.class).trace("started cooking dish:" + orderOfDishToCook.getDishName());
		
		getNeededIngredientsFromWarehouse();
		getNeededKitchenToolsFromWarehouse();
		
		long timeToSleep = Math.round(orderOfDishToCook.getDish().getCookTime() * chef.getEfficiency());
		try {
		//Thread.sleep(timeToSleep);
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		returnNeededKitchenToolsToWarehouse();
		
		
		//stop measuring time before reporting to CallableCookWholeOrder latch
		long endCook = System.currentTimeMillis();
		long TotalActualCookTime = endCook - startCook;
		
		Logger.getLogger(Management.class).trace(orderOfDishToCook.getDishName() + 
				" cooked in " + TotalActualCookTime +" latch count "+latch.getCount());
		
		
		latch.countDown();	

		Logger.getLogger(RunnableCookOneDish.class).trace("finished cooking dish:" + orderOfDishToCook.getDishName());
		
	}

	public RunnableCookOneDish(RunnableChef chef , OrderOfDish ood, Warehouse wh , CountDownLatch latch) {
		this.chef = chef;
		this.orderOfDishToCook = ood;
		this.warehouseRef = wh;
		this.ingredients = ood.getDish().getIngredients();
		this.kitchenTool = ood.getDish().getKitchenTools();
		this.latch = latch;
	}
	/**
	 * Consuming needed ingredients from {@link Warehouse}
	 */
	private void getNeededIngredientsFromWarehouse() {
		for (Ingredient current : ingredients) {
			warehouseRef.takeIngredient(current.getName(),
					current.getQuantity());
		//Statistics.StatisticsClass.addIngredientToStatistic(current);
		}
	}

	private void getNeededKitchenToolsFromWarehouse() {
		for (KitchenTool current : kitchenTool) {
			warehouseRef.takeKitchenTool(current.getName(),
					current.getQuantity());
		}
	}
	/**
	 * Returning needed kitchen tools to {@link Warehouse}
	 */
	private void returnNeededKitchenToolsToWarehouse() {
		for (KitchenTool current : kitchenTool) {
			warehouseRef.returnKitchenTool(current.getName(),
					current.getQuantity());
		}
	}
}
