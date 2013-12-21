package com.bgu.assignment3.passives;

import java.util.Iterator;
import java.util.Vector;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "OrderList")
public class Orders {
	
	Iterator<Order> iterator;
	
	@XmlElementWrapper(name="Orders")
	@XmlElement(name = "Order")
	private Vector<Order> orders;

	public Orders() {
		iterator = orders.iterator();
	}
	
	

	public Order getNextOrder() {
		
		if(iterator.hasNext())
			return iterator.next();
		
		return null;
	}
	
}
