package com.bgu.assignment3.actions;

import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElement;

import org.apache.log4j.Logger;

import com.bgu.assignment3.FancyStringBuilder;
import com.bgu.assignment3.SafeLock;
import com.bgu.assignment3.passives.Address;
import com.bgu.assignment3.passives.Management;
import com.bgu.assignment3.passives.Order;
import com.bgu.assignment3.passives.Order.Status;
import com.bgu.assignment3.passives.Statistics.StatisticsClass;

public class RunnableDeliveryPerson implements Runnable {
	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "speed")
	private double speed;

	private Vector<Order> deliveredOrders;
	private Address resturantAddress;
	private ArrayBlockingQueue<Order> deliveryQueue;
	private boolean shutDown;
	private SafeLock lock;

	public void run() {
		while (!shutDown) {
			try {

				Order toDeliver = deliveryQueue.take();
				// an order with id=-1 indicates its poisen order (should stop
				// the thread)
				if (toDeliver.getId() == -1 && shutDown)
					break;
				else if (toDeliver.getId() == -1)
					deliveryQueue.add(toDeliver);

				if (toDeliver.getId() != -1) {
					
					//poll current time (start)
					long start = System.currentTimeMillis();
					
					//add the order to delivered orders collection
					deliveredOrders.add(toDeliver);
					
					//calc the distance
					int distance = toDeliver.calcDistance(resturantAddress);
					
					Logger.getLogger(Management.class).info("Delivering " + toDeliver.toString());

					//sleeping, simulating the delivery
					Thread.sleep((long) (distance/speed));
					
					//poll current time (end)
					long end = System.currentTimeMillis();
					long deliverTime = end - start;

					Logger.getLogger(Management.class).info("Delivered " + toDeliver.toString() + "in "
									+ deliverTime);

					//add to statistics
					double reward = toDeliver.calculateReward(deliverTime);
					StatisticsClass.addDeliveredOrderToStatistics(toDeliver);
					StatisticsClass.addMoneyGained(reward);
					
					//set status to delivered
					toDeliver.setStatus(Status.DELIVERED);

					// notify managment that another order has been delivered
					lock.doNotify();
				}
			} catch (InterruptedException e) {

			}

		}
		Logger.getLogger(Management.class).info(toString() + " terminated");
	}

	public void init(ArrayBlockingQueue<Order> deliveryQueue,
			Address resturantAddress, SafeLock lock) {
		this.deliveryQueue = deliveryQueue;
		this.resturantAddress = resturantAddress;
		this.deliveredOrders = new Vector<Order>();
		this.lock = lock;
	}

	public void shutDown() {
		this.shutDown = true;
	}
	@Override
	public String toString() {
		
		FancyStringBuilder builder = new FancyStringBuilder();
		builder.append("DeliveryPerson")
		.append("name", name)
		.append("speed", speed);
		

		return builder.toString();
	}
}
