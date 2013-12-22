package com.bgu.assignment3.passives;

import java.util.concurrent.Semaphore;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
@XmlAccessorType(XmlAccessType.FIELD)
public class Ingredient {
	
	
	public String getName() {
		return name;
	}

	@XmlElement(name="name")
	private String name;
	@XmlElement(name="quantity")
	private int quantity;
	
	
	private Semaphore semaphore;
	
	@Override
	public String toString() {
		return name + " : " + quantity + "\n";
	}
	
	public void CunsumeIngredient(int quantity) {
		try {
			semaphore.acquire(quantity);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void afterUnmarshal(Unmarshaller u, Object parent) {
		if(parent instanceof Warehouse)
			semaphore = new Semaphore(quantity);
		
	}
	public int getQuantity() {
		return quantity;
	}

}
