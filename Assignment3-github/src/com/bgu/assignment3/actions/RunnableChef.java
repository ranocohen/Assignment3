package com.bgu.assignment3.actions;

import java.util.Vector;
import java.util.concurrent.Future;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.bgu.assignment3.passives.Order;

@XmlAccessorType(XmlAccessType.FIELD)
public class RunnableChef implements Runnable {
	
    private boolean fShouldStop; //in case the chef can't handle the order
    
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="efficiencyRating")
	private double efficiency;
	@XmlElement(name="enduranceRating")
	private double endurance;
	private double pressure;
	@XmlTransient
	private Vector<Future<Order>> ordersInProgress;
	
	public void run() {
		
	}
	/**
	 * Accepts order only if orderDifficulty < endurace - pressure
	 * 
	 * @param orderDifficulty the order difficulty
	 * @return true if this chef accepts an order with given orderDifficulty
	 */
	public boolean acceptingOrder(double orderDifficulty) {
		if(orderDifficulty < endurance - pressure)
			return true;
		
		return false;
	}
	
	 
}
