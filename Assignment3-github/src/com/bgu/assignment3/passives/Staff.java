package com.bgu.assignment3.passives;

import java.util.Collections;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.apache.log4j.Logger;

import com.bgu.assignment3.SafeLock;
import com.bgu.assignment3.actions.RunnableChef;
import com.bgu.assignment3.actions.RunnableDeliveryPerson;

@XmlAccessorType(XmlAccessType.FIELD)
public class Staff {

	@XmlElementWrapper(name="Chefs")
	@XmlElement(name = "Chef")
	private Vector<RunnableChef> chefs;
	
	@XmlElementWrapper(name="DeliveryPersonals")
	@XmlElement(name = "DeliveryPerson")
	private Vector<RunnableDeliveryPerson> deliveryPersons;
	
	/**
	 * Sorts the chefs by efficiency
	 */
	public void sortChefs() {
		Collections.sort(chefs);
	}
	
	public RunnableChef getApprovingChef(Order order) {
		for(RunnableChef chef : chefs) {
			if(chef.acceptingOrder(order.getDifficulty())){
				Logger.getLogger(Management.class).info("Found a chef too cook order " +order.getId()  );
				return chef;
			}
		}
		Logger.getLogger(Management.class).info("Could not find a chef right now to cook " +order.getId()  );

		//couldnt find approving chef , returning null
		return null;
	}
	public int chefCount() {
		return chefs.size();
	}
	public int deliveryCount() {
		return deliveryPersons.size();
	}
	public void executeChefs(BlockingQueue<Order> readyOrders, SafeLock lock) {
		for(RunnableChef chef : chefs) 
		{
			Logger.getLogger(Management.class).info("Chef "+ chef.getName() +  " has started to run");
			chef.init(readyOrders,lock);
			Thread t = new Thread(chef);
			t.start();
		}
			
		
	}
	public void executeDeliveryPersons(ArrayBlockingQueue<Order> deliveryQueue, Address resturantAddress,SafeLock lock) {
		for(RunnableDeliveryPerson dp : deliveryPersons) 
		{
			dp.init(deliveryQueue, resturantAddress,lock);
			Logger.getLogger(Management.class).info("DeliveryPerson "+ dp.toString() +  " has started to run");
			Thread t = new Thread(dp);
			t.start();
		}
			
		
	}
	public void shutDownChefs() {
		for(RunnableChef chef : chefs) 
			chef.shutDown();
	}
	public void shutDownDeliveryPerson( BlockingQueue<Order> bq ) {
		for(RunnableDeliveryPerson deliveryPerson : deliveryPersons) 
		{
			deliveryPerson.shutDown();
			Order poison = Order.posionOrder();
			try {
				bq.put(poison);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void shutDownChef(int i ) {
		chefs.get(i).shutDown();
	}
	public void shutDownDeliveryPerson(BlockingQueue<Order> bq,int i ) {
		deliveryPersons.get(i).shutDown();
	}
}
