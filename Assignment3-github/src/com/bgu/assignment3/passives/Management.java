package com.bgu.assignment3.passives;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.bgu.assignment3.actions.RunnableChef;

@XmlRootElement(name = "Restaurant")
public class Management {

	@XmlElement(name = "Repository")
	private Warehouse warehouse;

	@XmlElement(name = "Address")
	private Address address;

	@XmlElement(name = "Staff")
	private Staff staff;

	private Orders orders;
	private Menu menu;

	
	public Management() {
		
	}

	public void addOrders(Orders orders) {
		this.orders = orders;
	}

	public void addMenu(Menu menu) {
		this.menu = menu;
	}

	public void simulate() {
		// first we calculate the difficulties
		orders.calcDifficulty(menu);
		staff.sortChefs();
		staff.executeChefs();
		
		boolean shouldRun = true;
		Logger.getLogger(Management.class).info("Managment starting to look for chefs");
		int shouldWait = 0;
		while (shouldRun) {
			Order nextOrder = orders.getNextOrder();
			Logger.getLogger(Management.class).info("Looking for chef to cook "+nextOrder.getId());
			
			if(!orders.hasOrders())
			{
				shouldRun = false;
				continue;
			}
			RunnableChef approvingChef = staff.getApprovingChef(nextOrder);
			if(approvingChef != null) {
				cookDish(nextOrder , approvingChef);
				shouldWait++;
			}
			else
				shouldWait = 0;
			
			if(shouldWait >= staff.chefCount()) {
				//TODO wait somehow
			}
				
		}
	}

	private void cookDish(Order order, RunnableChef approvingChef) {
		System.out.println("COOK");
		approvingChef.acceptOrder(order, warehouse);
		orders.removeOrder(order);
	}

}
