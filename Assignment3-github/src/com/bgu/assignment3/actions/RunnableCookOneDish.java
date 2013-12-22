package com.bgu.assignment3.actions;

import com.bgu.assignment3.passives.OrderOfDish;
import com.bgu.assignment3.passives.Warehouse;

public class RunnableCookOneDish implements Runnable {
	
	OrderOfDish orderOfDishToCook;
	Warehouse warehouseRef;

	public void run() {
		
	}
	
	public RunnableCookOneDish(OrderOfDish ood, Warehouse wh) {
		this.orderOfDishToCook = ood;
	}
	
	
	
	

}
