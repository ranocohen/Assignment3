package com.bgu.assignment3.actions;

import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.bgu.assignment3.passives.Order;

@XmlAccessorType(XmlAccessType.FIELD)
public class RunnableChef implements Runnable , Comparable<RunnableChef> {
	
    private boolean fShouldStop; //in case the chef can't handle the order
    
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="efficiencyRating")
	private double efficiency;
	public double getEfficiency() {
		return efficiency;
	}
	@XmlElement(name="enduranceRating")
	private double endurance;
	private double pressure;
	
	@XmlTransient
	private Vector<Future<Order>> ordersInProgress;
	@XmlTransient
	private ExecutorService executor = Executors.newCachedThreadPool();


	private Semaphore semaphore;
	
	public void run() {
		//while isalive
		//iterate orders and run them 
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public int compareTo(RunnableChef o) {
		if(this.efficiency - o.efficiency < 0)
			return 1;
		if(this.efficiency - o.efficiency > 0)
			return -1;
		return 0;
	}
	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
		
	}
	
	 
}
