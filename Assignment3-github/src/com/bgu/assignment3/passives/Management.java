package com.bgu.assignment3.passives;

import java.util.concurrent.ArrayBlockingQueue;

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
	private ArrayBlockingQueue<Order> readyOrders;
	
	public Management() {
		
	}

	public void addOrders(Orders orders) {
		this.orders = orders;
	}

	public void addMenu(Menu menu) {
		this.menu = menu;
	}

	public void simulate() {
		readyOrders = new ArrayBlockingQueue<Order>(staff.deliveryCount());
		// first we calculate the difficulties
		orders.calcDifficulty(menu);
		staff.sortChefs();
		staff.executeChefs(readyOrders);
		
		boolean shouldRun = true;
		Logger.getLogger(Management.class).info("Managment starting to look for chefs");
		int shouldWait = 0;
		while (shouldRun) {
			if(!orders.hasOrders())
			{
				shouldRun = false;
				continue;
			}
			Order nextOrder = orders.getNextOrder();
			Logger.getLogger(Management.class).info("Looking for chef to cook "+nextOrder.getId());
			
	
			RunnableChef approvingChef = staff.getApprovingChef(nextOrder);
			if(approvingChef != null) {
				cookDish(nextOrder , approvingChef);
				shouldWait++;
			}
			else
				shouldWait = 0;
			
			if(shouldWait >= staff.chefCount()) {
				try {
					synchronized (readyOrders) {
						readyOrders.wait();
						if(readyOrders.size() > 0)
							Logger.getLogger(Management.class).info(readyOrders.take().getId() + " is ready for delivery");	
					}
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// if we got notified then 1 chef finished
				
			}
				
		}
	}

	private void cookDish(Order order, RunnableChef approvingChef) {
		System.out.println("COOK");
		approvingChef.acceptOrder(order, warehouse);
		orders.removeOrder(order);
	}

}
