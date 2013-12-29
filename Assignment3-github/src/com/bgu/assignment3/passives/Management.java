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
		Logger.getLogger(Management.class).info("simulation started");
		Statistics.StatisticsClass.init();

		long startProg = System.currentTimeMillis();

		readyOrders = new ArrayBlockingQueue<Order>(orders.ordersCount());
		//calculate the difficulties
		orders.calcDifficulty(menu);
		
		//optimization attempts 
		orders.sortOrders();
		//staff.sortChefs();
		
		//start the chefs and deliveryPersons threads
		staff.executeChefs(readyOrders);
		staff.executeDeliveryPersons(readyOrders, address);
		
		/**
		 * here we send all orders to chefs , we do this as long not all orders are delivered
		 * we use the readyOrders(blockingQueue) to get notified when an order is ready/delivered 
		 */
		while (!orders.allDelivered()) {
			synchronized (readyOrders) {
				Thread t = new Thread(new Runnable() {
					
					public void run() {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						staff.shutDownChef(2);
						staff.shutDownChef(3);
					}
				});
				//t.start();
				if (!orders.deployOrder(staff, warehouse)) {
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
		}

		long endProg = System.currentTimeMillis();

		long TotalActualCookTime = endProg - startProg;

		Logger.getLogger(Management.class).info(
				"Program runtime:" + TotalActualCookTime);
		
		staff.shutDownChefs();
		staff.shutDownDeliveryPerson(readyOrders);
	}
	
}
