package com.bgu.assignment3.actions;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

import org.apache.log4j.Logger;

import com.bgu.assignment3.passives.Order;
import com.bgu.assignment3.passives.OrderOfDish;
import com.bgu.assignment3.passives.Warehouse;

public class CallableCookWholeOrder implements Callable<Order> {
	private RunnableChef chef;
	private Warehouse warehouseRef;
	private CountDownLatch latch;
	private ExecutorService threadPool;
	private Order order;
	private Semaphore semaphore;

	public CallableCookWholeOrder(RunnableChef chef, Order order, Warehouse wh, Semaphore semaphore) {
		this.chef = chef;
		this.order = order;
		this.warehouseRef = wh;
		this.semaphore = semaphore;
		int dishesCount = order.calculateTotalDishes();
		
		threadPool = Executors.newFixedThreadPool(dishesCount);
		latch = new CountDownLatch(dishesCount);

	}

	public Order call() throws Exception {
		//TODO add chef's name
		//Logger.getLogger(CallableCookWholeOrder.class).trace("started cooking whole order");
		System.out.println("Starting to preper WHOLE order");
		for (OrderOfDish ood : order.getDishes()) {
			for(int j =0 ;j< ood.getQuantity(); j++)
			{
				RunnableCookOneDish rcod = new RunnableCookOneDish(chef,ood,
						warehouseRef, latch);
				System.out.println("Starting to preper "+ood.getDishName());
				threadPool.execute(rcod);	
			}
			
		}
		threadPool.shutdown();
		//wait for all threads to finish
		try {
			System.out.println("Waiting for latch");
			latch.await();
		} catch (InterruptedException E) {

		}
		System.out.println("Relaesing semaphore");
		semaphore.release();
	//	Logger.getLogger(CallableCookWholeOrder.class).trace("finished cooking whole order");		
		return order;
	}

}
