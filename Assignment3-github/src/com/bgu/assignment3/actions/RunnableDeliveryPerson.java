package com.bgu.assignment3.actions;

import java.util.concurrent.ArrayBlockingQueue;

import javax.xml.bind.annotation.XmlElement;

import com.bgu.assignment3.passives.Address;

public class RunnableDeliveryPerson implements Runnable{
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="speed")
	private double speed;
	
	private Address resturantAddress;
	String address;
	private ArrayBlockingQueue<Integer> deliveryQueue;
	
	public RunnableDeliveryPerson(Address resturantAddress ) {
		this.resturantAddress = resturantAddress;
	}
	
	public RunnableDeliveryPerson(ArrayBlockingQueue<Integer> delQueue,String addr) {
		this.deliveryQueue = delQueue;
		this.address = addr;
	}

	public void run() {
		
	}
	
}
