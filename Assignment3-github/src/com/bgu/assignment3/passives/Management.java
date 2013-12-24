package com.bgu.assignment3.passives;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

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
	private ArrayBlockingQueue<Integer> OrdersForDelivery;

	private ExecutorService threadPool;
	
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
		
		threadPool = Executors.newFixedThreadPool(staff.chefCount());
		staff.executeChefs(threadPool);
		
		boolean shouldRun = true;
		Logger.getLogger(Management.class).fatal("Managment starting to look for chefs");
		while (shouldRun) {
			Order nextOrder = orders.getNextOrder();
			if(!orders.hasOrders())
			{
				shouldRun = false;
				continue;
			}
			RunnableChef approvingChef = staff.getApprovingChef(nextOrder);
			if(approvingChef != null)
				cookDish(nextOrder , approvingChef);
			
			
		}
		/*threadPool.shutdown();
		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.getLogger(Management.class).fatal("Managment is DONE!");*/

	}

	private void cookDish(Order order, RunnableChef approvingChef) {
		System.out.println("COOK");
		approvingChef.acceptOrder(order, warehouse);
		orders.removeOrder(order);
	}

}
