package com.bgu.assignment3.passives;

import java.util.concurrent.Semaphore;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.log4j.Logger;

import com.bgu.assignment3.FancyStringBuilder;

@XmlAccessorType(XmlAccessType.FIELD)
public class KitchenTool implements Comparable<KitchenTool> {

	public String getName() {
		return name;
	}

	@XmlElement(name = "name")
	private String name;
	@XmlElement(name = "quantity")
	private int quantity;

	private Semaphore semaphore;

	@Override
	public String toString() {
		FancyStringBuilder builder = new FancyStringBuilder();
		builder.append("name", name)
		.append("quantity",quantity);
		
		return builder.toString();
	}

	void afterUnmarshal(Unmarshaller u, Object parent) {
		if (parent instanceof Warehouse)
			semaphore = new Semaphore(quantity);
	}

	public void takeKitchenTool(int quantity) {
		try {
			/*
			 * Logger.getLogger(Management.class).info(Thread.currentThread().
			 * toString() + " trying to take "+quantity +" " + name +
			 * " , avaiable : "+semaphore.availablePermits());
			 */
			if (quantity > 0 && semaphore.availablePermits() == 0)
				Logger.getLogger(Management.class).info("waiting for " + name);
			semaphore.acquire(quantity);
			/*
			 * Logger.getLogger(Management.class).info(
			 * Thread.currentThread().toString() + " took "+quantity +" " +
			 * name);
			 */
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void returnKitchenTool(int quantity) {
		// Logger.getLogger(Management.class).info(
		// Thread.currentThread().toString() + " releasing "+quantity +" " +
		// name);
		if (semaphore.availablePermits() == 0)
			Logger.getLogger(Management.class).info("releasing " + name);
		semaphore.release(quantity);
	}

	public int getQuantity() {
		return this.quantity;
	}

	public int compareTo(KitchenTool o) {
		return this.name.compareTo(o.name);
	}

}