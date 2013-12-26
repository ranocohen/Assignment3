package com.bgu.assignment3.passives;

<<<<<<< HEAD
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
=======
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
>>>>>>> branch 'master' of https://github.com/ranocohen/Assignment3.git

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
<<<<<<< HEAD
	private ArrayBlockingQueue<Order> readyOrders;
=======
	private ArrayBlockingQueue<Integer> OrdersForDelivery;

>>>>>>> branch 'master' of https://github.com/ranocohen/Assignment3.git
	
	
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
		readyOrders = new ArrayBlockingQueue<Order>(staff.deliveryCount());
		// first we calculate the difficulties
		orders.calcDifficulty(menu);
		staff.sortChefs();
		staff.executeChefs(readyOrders);

		boolean shouldRun = true;
	
		
		while (orders.hasOrders() || !allOrdersDelivered) {
			
			
			  if(!orders.deployOrder(staff, warehouse))
			  {
				  
					Logger.getLogger(Management.class).info(
							"All chefs are busy , managment is waiting");
					try {
						synchronized (readyOrders) {
							readyOrders.wait();
						}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			  }
				if(readyOrders.size() > 0 ) {
					
					Iterator<Order> it = readyOrders.iterator();
					while(it.hasNext())
					{
						Logger.getLogger(Management.class).info(
							"Delvering " +	it.next().getId());
						it.remove();
						
					}
				}
				
			
		}
							
	
	}


	

}
