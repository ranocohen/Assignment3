package com.bgu.assignment3.actions;

import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElement;

import org.apache.log4j.Logger;

import com.bgu.assignment3.passives.Address;
import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.Order;
import com.bgu.assignment3.passives.Statistics;
import com.bgu.assignment3.passives.Order.Status;
import com.bgu.assignment3.passives.Statistics.StatisticsClass;

public class RunnableDeliveryPerson implements Runnable {
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "speed")
	private double speed;
	private boolean toRun;
	Address resturantAddress;
	private ArrayBlockingQueue<Order> deliveryQueue;

	public void run() {
		while (toRun) {
			try {
				Order toDeliver = deliveryQueue.take();

				int distance = toDeliver.calcDistance(resturantAddress);
				Logger.getLogger(Management.class).info(
						"Delivering " + toDeliver.toString());
				
				long start = System.currentTimeMillis();
				Thread.sleep(distance);
				long end = System.currentTimeMillis();
				long deliverTime = end - start;
				
				
				Logger.getLogger(Management.class).info(
						"Delivered " + toDeliver.toString() + "in "+deliverTime );
				
				double reward = toDeliver.calculateReward(deliverTime);
				StatisticsClass.addDeliveredOrderToStatistics(toDeliver);
				toDeliver.setStatus(Status.DELIVERED);

				// notify managment that another order has been delivered
				synchronized (deliveryQueue) {
					deliveryQueue.notifyAll();
				}
			} catch (InterruptedException e) {
				toRun = false;
			}

		}
	}

	public void init(ArrayBlockingQueue<Order> deliveryQueue,
			Address resturantAddress) {
		this.deliveryQueue = deliveryQueue;
		this.resturantAddress = resturantAddress;
		this.toRun = true;

	}

}
