package com.bgu.assignment3.actions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import com.bgu.assignment3.passives.Dish;

@XmlAccessorType(XmlAccessType.FIELD)
public class RunnableChef implements Runnable {
	
    private boolean fShouldStop; //in case the chef can't handle the order
    
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="efficiencyRating")
	private double efficiency;
	@XmlElement(name="enduranceRating")
	private double endurance;
	
	public RunnableChef() {
		
	}
	
	
	public void getOrderToCook(Dish dishToCook) {
		
	}

	
	public void run() {
		
	}

	
	 
}
