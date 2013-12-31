package com.bgu.assignment3.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
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

import com.bgu.assignment3.FancyStringBuilder;
import com.bgu.assignment3.SafeLock;
import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.Order;
import com.bgu.assignment3.passives.Order.Status;
import com.bgu.assignment3.passives.Warehouse;

/**
 * A working chef, responsible for accepting orders from restaurant's managment, accept the order (if can)
 * and making it, delivering it back for proper delivery
 * 
 * @author Ran Cohen & Idan Nakav
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RunnableChef implements Runnable, Comparable<RunnableChef> {

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "efficiencyRating")
	private double efficiency;

	@XmlElement(name = "enduranceRating")
	private double endurance;
	private double pressure;
	private boolean shutDown;
	private ArrayList<Order> ordersToCook = new ArrayList<Order>();
	@XmlTransient
	private ArrayList<Future<Order>> ordersInProgress = new ArrayList<Future<Order>>();
	@XmlTransient
	private ExecutorService executor = Executors.newCachedThreadPool();
	private Semaphore semaphore;
	private Warehouse warehouse;
	private BlockingQueue<Order> readyOrders;
	private SafeLock lock;
	
	public double getEfficiency() {
		return efficiency;
	}
	
	public String getName() {
		return name;
	}


	public void run() {
		while (!shutDown || isStillWorking()) {

			try {
				// chef should wait until he accepts an order / finish an order
				semaphore.acquire();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			cookOrder();
			fetchOrder();
		}

		// shutdown our executor
		executor.shutdown();
		Logger.getLogger(Management.class).info(toString() + "is terminated");
	}

	/**
	 * Accepts order only if orderDifficulty < endurace - pressure
	 * 
	 * @param orderDifficulty
	 *            the order difficulty
	 * @return true if this chef accepts an order with given orderDifficulty
	 */
	public boolean acceptingOrder(double orderDifficulty) {

		// if shutdown do not accept any new order
		if (shutDown)
			return false;
		else {

		}
		Logger.getLogger(Management.class).info(
				"Chef " + getName() + " checking for approval "
						+ "OrderDiff = " + orderDifficulty + " endurae = "
						+ endurance + " pressure = " + pressure);

		if (orderDifficulty <= endurance - pressure) {
			Logger.getLogger(Management.class).info(
					this.getName() + " accepting order");
			return true;
		}
		Logger.getLogger(Management.class).info(
				this.getName() + " deinided order");
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

		order.setStatus(Status.IN_PROGRESS);
		increasePressure(order.getDifficulty());
		synchronized (ordersToCook) {
			this.ordersToCook.add(order);
		}

		this.warehouse = wh;
		semaphore.release();
	}

	private void cookOrder() {

		// sync block since the main thread could modify orderToCook at
		// acceptOrder
		synchronized (ordersToCook) {

			Order current = null;
			Iterator<Order> it = ordersToCook.iterator();
			while (it.hasNext()) {
				current = it.next();

				Logger.getLogger(Management.class).info(
						"Sending " + current.toString()
								+ " to CallableCookWholeOrder");

				CallableCookWholeOrder ccwo = new CallableCookWholeOrder(this,
						current, warehouse, semaphore);
				Future<Order> result = executor.submit(ccwo);

				ordersInProgress.add(result);

			}
			if (current != null)
				ordersToCook.remove(current);
		}

	}

	private void increasePressure(int difficulty) {

		this.pressure += difficulty;
		Logger.getLogger(Management.class).trace(
				this.getName() + "increased pressure to " + pressure);

	}

	public void fetchOrder() {

		Iterator<Future<Order>> it2 = ordersInProgress.iterator();
		while (it2.hasNext()) {
			Future<Order> current = it2.next();
			if (current != null && current.isDone()) {
				Order ready;
				try {

					ready = current.get();
					synchronized (readyOrders) {
						Logger.getLogger(Management.class).info(
								ready.getId()
										+ " is ready , notifying managment");
						readyOrders.put(ready);
						lock.doNotify();
					}

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
		Logger.getLogger(Management.class).trace(
				this.getName() + "decreased pressure to " + pressure);

	}

	public void init(BlockingQueue<Order> readyOrders, SafeLock lock) {
		this.readyOrders = readyOrders;
		this.semaphore = new Semaphore(0);
		this.lock = lock;
	}

	/**
	 * Requesting this chef to shutDown , if shutdown no new orders will be
	 * accepted chef still working until finishes his current orders
	 */
	public void shutDown() {
		this.shutDown = true;
		// our thread is in acquire mode we release him to fully shutdown
		semaphore.release();
	}

	@Override
	public String toString() {
		FancyStringBuilder builder = new FancyStringBuilder();
		builder.append("Chef").append("name", name)
				.append("pressure", pressure).append("endurance", endurance)
				.append("efficiency", efficiency);

		return builder.toString();
	}

	private boolean isStillWorking() {
		return (ordersToCook.size() > 0 || ordersInProgress.size() > 0);
	}
}
