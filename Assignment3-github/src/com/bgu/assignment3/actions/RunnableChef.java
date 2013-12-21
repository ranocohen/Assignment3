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
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="efficiencyRating")
	private double efficiency;
	@XmlElement(name="enduranceRating")
	private double endurance;
	
	@XmlTransient
	private Vector<Future<Order>> ordersInProgress;
	
	public void run() {
		
	}

	
	 
}
