package com.bgu.assignment3.actions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RunnableChef implements Runnable {
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="efficiencyRating")
	private double efficiency;
	@XmlElement(name="enduranceRating")
	private double endurance;
	
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
