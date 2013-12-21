package com.bgu.assignment3.actions;

import com.bgu.assignment3.passives.OrderOfDish;

public class RunnableCookOneDish implements Runnable {
	
	OrderOfDish orderOfDishToCook;

	public void run() {
		
	}
	
	public RunnableCookOneDish(OrderOfDish ood) {
		this.orderOfDishToCook = ood;
	}
	
	

}
