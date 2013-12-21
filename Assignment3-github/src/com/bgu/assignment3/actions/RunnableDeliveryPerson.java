package com.bgu.assignment3.actions;

import javax.xml.bind.annotation.XmlElement;

public class RunnableDeliveryPerson implements Runnable{
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="speed")
	private double speed;
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
