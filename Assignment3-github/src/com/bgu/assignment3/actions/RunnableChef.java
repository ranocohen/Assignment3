package com.bgu.assignment3.actions;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.log4j.Logger;

import com.bgu.assignment3.passives.Order;
import com.bgu.assignment3.passives.Warehouse;

@XmlAccessorType(XmlAccessType.FIELD)
public class RunnableChef implements Runnable, Comparable<RunnableChef> {

	private boolean fShouldStop; // in case the chef can't handle the order

	@XmlElement(name = "name")
	private String name;

	public String getName() {
		return name;
	}

	@XmlElement(name = "efficiencyRating")
	private double efficiency;

	public double getEfficiency() {
		return efficiency;
	}

	@XmlElement(name = "enduranceRating")
	private double endurance;
	private double pressure;

	@XmlTransient
	private Vector<Future<Order>> ordersInProgress = new Vector<Future<Order>>();
	@XmlTransient
	private ExecutorService executor = Executors.newCachedThreadPool();

	private Semaphore semaphore;

	private Order order;

	private Warehouse warehouse;

	public void run() {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(order != null) {
			System.out.println("Order recieved");		
			CallableCookWholeOrder ccwo = new CallableCookWholeOrder(this, order,
					warehouse,semaphore);
			Future<Order> result = executor.submit(ccwo);
			ordersInProgress.add(result);
			order = null;
		}
		else {
			System.out.println("Order is ready");		
		}
	
		/*Logger.getLogger(RunnableChef.class).trace(
				"chef" + getName() + " running");
		*/

		
		// Logger.getLogger(RunnableChef.class).trace("chef" + getName()
		// +" started working");
	}

	/**
	 * Accepts order only if orderDifficulty < endurace - pressure
	 * 
	 * @param orderDifficulty
	 *            the order difficulty
	 * @return true if this chef accepts an order with given orderDifficulty
	 */
	public boolean acceptingOrder(double orderDifficulty) {
		if (orderDifficulty < endurance - pressure)
			return true;

		return false;
	}

	public int compareTo(RunnableChef o) {
		if (this.efficiency - o.efficiency < 0)
			return 1;
		if (this.efficiency - o.efficiency > 0)
			return -1;
		return 0;
	}

	public void acceptOrder(Semaphore semaphore, Order order, Warehouse wh) {
		this.semaphore = semaphore;
		this.order = order;
		this.warehouse = wh;
		semaphore.release();
	}

}
