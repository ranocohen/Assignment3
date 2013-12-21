package com.bgu.assignment3.passives;

import java.util.concurrent.Semaphore;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class KitchenTool  {
	
	@XmlElement(name="name")
	private String name;
	@XmlElement(name="quantity")
	private int quantity;

	private Semaphore semaphore;
	
	@Override
	public String toString() {
		return name + " : " + quantity +"\n";
	}
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		
		semaphore = new Semaphore(quantity);
		System.out.println("INITED SEMA via" + quantity);
	}
}