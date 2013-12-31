package com.bgu.assignment3.passives;

import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.bgu.assignment3.SafeLock;
import com.bgu.assignment3.passives.Statistics.StatisticsClass;

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
	private SafeLock lock;
	private ArrayBlockingQueue<Integer> OrdersForDelivery;


	public Management() {

	}

	public void addOrders(Orders orders) {
		this.orders = orders;
	}

	public void addMenu(Menu menu) {
		this.menu = menu;
	}

	public void simulate() {
		lock = new SafeLock();
		Logger.getLogger(Management.class).info("simulation started");
		Statistics.StatisticsClass.init();

		long startProg = System.currentTimeMillis();

		readyOrders = new ArrayBlockingQueue<Order>(orders.ordersCount());
		//calculate the difficulties
		orders.calcualteDifficulty(menu);
		
		//optimization attempts 
		//orders.sortOrders();
		staff.sortChefs();
		
		//start the chefs and deliveryPersons threads
		staff.executeChefs(readyOrders, lock);
		staff.executeDeliveryPersons(readyOrders, address,lock);
		
		/**
		 * here we send all orders to chefs , we do this as long not all orders are delivered
		 * we use the readyOrders(blockingQueue) to get notified when an order is ready/delivered 
		 */
		while (!orders.allDelivered()) {
			
		
				if (!orders.deployOrder(staff, warehouse)) {
					Logger.getLogger(Management.class).info(
							"All chefs are busy , managment is waiting");
						lock.doWait();
				
						Logger.getLogger(Management.class).trace(
								"managment notificed");
				
			}
		}

		long endProg = System.currentTimeMillis();

		long TotalActualCookTime = endProg - startProg;
		

		Logger.getLogger(Management.class).info(
				"Program runtime:" + TotalActualCookTime);
		
		
		StatisticsClass stats = new StatisticsClass();
		System.out.println(stats.toString());
		
		staff.shutDownChefs();
		staff.shutDownDeliveryPerson(readyOrders);
	}
	
}
