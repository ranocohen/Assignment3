package com.bgu.assignment3.passives;


import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

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

	private ArrayBlockingQueue<Integer> OrdersForDelivery;

	
	
	private boolean allOrdersDelivered; 
	private int deliveredCount;
	public Management() {

	}

	public void addOrders(Orders orders) {
		this.orders = orders;
	}

	public void addMenu(Menu menu) {
		this.menu = menu;
	}

	public void simulate() {
		
		long startProg = System.currentTimeMillis();
		
		readyOrders = new ArrayBlockingQueue<Order>(staff.deliveryCount());
		// first we calculate the difficulties
		orders.calcDifficulty(menu);
		staff.sortChefs();
		staff.executeChefs(readyOrders);
		staff.executeDeliveryPersons(readyOrders, address);
		synchronized (readyOrders) {
		while (orders.hasOrders() && !allOrdersDelivered) {
			
			
			  if(!orders.deployOrder(staff, warehouse))
			  {
				  
					Logger.getLogger(Management.class).info(
							"All chefs are busy , managment is waiting");
					try {
							readyOrders.wait();
						
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }
		}
				if(readyOrders.size() > 0 ) {
					
					Iterator<Order> it = readyOrders.iterator();
					while(it.hasNext())
					{
					//	it.remove();

					}
				}
				
			
		}
							
	
		long endProg = System.currentTimeMillis();
		
		long TotalActualCookTime = endProg - startProg;
		
		Logger.getLogger(Management.class).info("Program runtime:" + TotalActualCookTime);;
	}


	

}
