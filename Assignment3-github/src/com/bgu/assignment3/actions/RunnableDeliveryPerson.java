package com.bgu.assignment3.actions;

import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElement;

public class RunnableDeliveryPerson implements Runnable{
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="speed")
	private double speed;
	
	
	String address;
	private ArrayBlockingQueue<Integer> deliveryQueue;
	
	public RunnableDeliveryPerson(ArrayBlockingQueue<Integer> delQueue,String addr) {
		this.deliveryQueue = delQueue;
		this.address = addr;
	}



	public void run() {
		
	}

}
