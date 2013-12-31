package com.bgu.assignment3.actions;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.Order;
import com.bgu.assignment3.passives.OrderOfDish;
import com.bgu.assignment3.passives.Warehouse;

/**
 * Sub-cooking each dish listed in a given order
 * @author Ran Cohen & Idan Nakav
 *
 */
public class CallableCookWholeOrder implements Callable<Order> {
	private RunnableChef chef;
	private Warehouse warehouseRef;
	private CountDownLatch latch;
	private Order order;
	private Semaphore semaphore;
	private long orderExcpectedCookTime;
	private long actualCookTime;

	public CallableCookWholeOrder(RunnableChef chef, Order order, Warehouse wh,
			Semaphore semaphore) {
		this.chef = chef;
		this.order = order;
		this.warehouseRef = wh;
		this.semaphore = semaphore;
		int dishesCount = order.calculateTotalDishes();

		latch = new CountDownLatch(dishesCount);

	}

	/**
	 * start cooking each dish for given order
	 */
	public Order call() throws Exception {
		long cookStart = System.currentTimeMillis();
		
		Logger.getLogger(Management.class).trace("started cooking whole order");
		for (OrderOfDish ood : order.getDishes()) {
			for (int j = 0; j < ood.getQuantity(); j++) {
				orderExcpectedCookTime += ood.getDish().getCookTime()
						* ood.getQuantity();
				RunnableCookOneDish rcod = new RunnableCookOneDish(chef, ood,
						warehouseRef, latch);
				Thread t = new Thread(rcod);
				t.start();
			}

		}

		try {
			Logger.getLogger(Management.class).trace(" b4 latch ");
			latch.await();
			Logger.getLogger(Management.class).trace("latch finished");
		} catch (InterruptedException E) {

		}

		//measure time taken for cooking order
		long cookEnd = System.currentTimeMillis();

		actualCookTime = cookEnd - cookStart;
		order.setActualCookTime(actualCookTime);

		Logger.getLogger(Management.class).info(
				"finished cooking whole order in " + actualCookTime);
		semaphore.release();
		return order;
	}

	/**
	 * returns the pre-calculated cook time
	 * @return actual cook time per order
	 */
	public long getActualCookTime() {
		return actualCookTime;
	}

}
