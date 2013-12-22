package com.bgu.assignment3.actions;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bgu.assignment3.passives.Order;
import com.bgu.assignment3.passives.OrderOfDish;
import com.bgu.assignment3.passives.Warehouse;

public class CallableCookWholeOrder implements Callable<Order> {
	private RunnableChef chef;
	private Warehouse warehouseRef;
	private CountDownLatch latch;
	private ExecutorService threadPool;
	private Order order;

	public CallableCookWholeOrder(RunnableChef chef, Order order, Warehouse wh) {
		this.chef = chef;
		this.order = order;
		this.warehouseRef = wh;

		int dishesCount = order.calculateTotalDishes();

		threadPool = Executors.newFixedThreadPool(dishesCount);
		latch = new CountDownLatch(dishesCount);

	}

	public Order call() throws Exception {
		for (OrderOfDish ood : order.getDishes()) {
			RunnableCookOneDish rcod = new RunnableCookOneDish(ood,
					warehouseRef, latch);
			threadPool.execute(rcod);
		}
		//wait for all threads to finish
		try {
			latch.await();
		} catch (InterruptedException E) {

		}
		return order;
	}

}
