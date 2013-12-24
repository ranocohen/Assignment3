package com.bgu.assignment3.actions;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.log4j.Logger;

import com.bgu.assignment3.passives.Management;
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
	private boolean shutDown;
	private List<Order> ordersToCook = new Vector<Order>();
	@XmlTransient
	private Vector<Future<Order>> ordersInProgress = new Vector<Future<Order>>();
	@XmlTransient
	private ExecutorService executor = Executors.newCachedThreadPool();

	private Semaphore semaphore;

	private Warehouse warehouse;

	public void run() {
		while (!shutDown) {
			System.out.println("Entered RunnableChef " + getName() + " with "
					+ semaphore.availablePermits());
			try {
				semaphore.acquire();
				System.out.println("After acquire");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cookOrder();
			fetchOrder();
		}
	}

	/*
	 * Logger.getLogger(RunnableChef.class).trace( "chef" + getName() +
	 * " running");
	 */

	// Logger.getLogger(RunnableChef.class).trace("chef" + getName()
	// +" started working");

	/**
	 * Accepts order only if orderDifficulty < endurace - pressure
	 * 
	 * @param orderDifficulty
	 *            the order difficulty
	 * @return true if this chef accepts an order with given orderDifficulty
	 */
	public boolean acceptingOrder(double orderDifficulty) {
		Logger.getLogger(Management.class).info("Chef "+getName() + " checking for approval " +
	"OrderDiff = " +orderDifficulty 
	+" endurae = " + endurance
	+" pressure = " + pressure);

		if (orderDifficulty <= endurance - pressure) {
			Logger.getLogger(Management.class).info(this.getName() + " accepting order");

			return true;
		}	
		Logger.getLogger(Management.class).info(this.getName() + " deinided order");
		return false;
	}

	public int compareTo(RunnableChef o) {
		if (this.efficiency - o.efficiency > 0)
			return 1;
		if (this.efficiency - o.efficiency < 0)
			return -1;
		return 0;
	}

	public void acceptOrder(Order order, Warehouse wh) {
		synchronized (ordersToCook) {
			this.ordersToCook.add(order);
		}

		
		this.warehouse = wh;
		System.out.println("Sending order from managment");
		semaphore.release();
	}

	private void cookOrder() {
		synchronized (ordersToCook) {

			Iterator<Order> it = ordersToCook.iterator();
			while (it.hasNext()) {
				Order current = it.next();
				System.out.println("Sending order " + current.getId()
						+ " to ccwd");
				CallableCookWholeOrder ccwo = new CallableCookWholeOrder(this,
						current, warehouse, semaphore);
				Future<Order> result = executor.submit(ccwo);
				increasePressure(current.getDifficulty());
				
				ordersInProgress.add(result);
				result = null;
				it.remove();
				// if (ordersToCook.size() == 0)
				// this.shutDown = true;
			}
		}
	}

	private void increasePressure(int difficulty) {
		this.pressure += difficulty;
		
	}

	public synchronized void fetchOrder() {
		System.out.println("FETCHING ORDER " + ordersInProgress.size() );
		Iterator<Future<Order>> it2 = ordersInProgress.iterator();
		while (it2.hasNext()) {
			Future<Order> current = it2.next();
			if (current != null && current.isDone()) {
				Order ready;
				try {
					
					ready = current.get();
					System.out.println(ready.getId() + " IS READY");
					decreasePressure(ready.getDifficulty());
					ready = null;
					it2.remove();
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	private void decreasePressure(int difficulty) {
		this.pressure -= difficulty;
		
	}

	public void init() {
		this.semaphore = new Semaphore(0);
	}
}
